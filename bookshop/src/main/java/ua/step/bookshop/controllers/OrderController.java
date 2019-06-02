package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.bookshop.models.Basket;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.UserRepository;

/**
 * 
 * @author sergey TODO Осталось доделать только пагинацию
 *
 */

@Controller
public class OrderController extends SecurityContextHolder {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/order")
	private String getOrder(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByLogin(auth.getName()).orElse(new User());
		List<Book> books = new ArrayList<Book>();
		List<Basket> userBaskets = user.getBasket();
		for (int i = 0; i < userBaskets.size(); i++) {
			for (int j = 0; j < userBaskets.get(i).getBooks().size(); j++) {
				books.add(userBaskets.get(i).getBooks().get(j));
			}
		}
		int price = 0;
		for (int i = 0; i < books.size(); i++) {
			price += books.get(i).getPrice();
		}
		model.addAttribute("book", books);
		model.addAttribute("price", price);
		model.addAttribute("contentPage", "basket");
		model.addAttribute("contentPage", "order");
		return "index";
	}
}
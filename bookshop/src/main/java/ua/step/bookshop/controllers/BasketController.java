package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.bookshop.models.Basket;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.BasketRepository;
import ua.step.bookshop.repositories.UserRepository;

@Controller
public class BasketController extends SecurityContextHolder {
	@Autowired
	private BasketRepository basketRepository;
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = "/basket")
	public String getBasketPage(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userRepository.findByLogin(auth.getName()).orElse(new User());
		List<Book> books = new ArrayList<Book>();
		List<Basket> userBaskets = user.getBasket();
		for (int i = 0; i < userBaskets.size(); i++) {
			for (int j = 0; j < userBaskets.get(i).getBooks().size(); j++) {
				books.add(userBaskets.get(i).getBooks().get(j));
			}
		}
		model.addAttribute("books", books);
		model.addAttribute("contentPage", "basket");
		return "index";
	}

	@PostMapping(value = "/basket/add")
	public String addBookToBasket(@ModelAttribute("book") Book book, @ModelAttribute Basket basket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<User> user = new ArrayList<User>();
		user.add(userRepository.findByLogin(auth.getName()).orElse(new User()));
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		basket.setUsers(user);
		basket.setBooks(books);
		basketRepository.save(basket);
		List<Basket> baskets = new ArrayList<Basket>();
		baskets.add(basket);
		user.get(0).setBasket(baskets);
		userRepository.save(user.get(0));
		return "redirect:/";
	}
}
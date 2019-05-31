package ua.step.bookshop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.step.bookshop.repositories.BookRepository;

/**
 * 
 * @author sergey TODO Осталось доделать только пагинацию
 *
 */

@Controller
public class OrderController {
	@Autowired
	private BookRepository repo;

	@GetMapping("/order")
	private String getOrder(Model model) {
		// model.addAttribute("books", repo.findAll());
		model.addAttribute("contentPage", "order"); 
		return "index";
	}
}
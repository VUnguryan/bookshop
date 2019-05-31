package ua.step.bookshop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;

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
	private String getFavorites(Model model) {
		// model.addAttribute("books", repo.findAll());
		model.addAttribute("contentPage", "order");
		return "index";
	}
}
package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.repositories.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repo;

	@GetMapping("/books")
	private String getBooks(Model model) {
		model.addAttribute("books", repo.findByOrderByCreateDate());
		return "books";
	}

	@GetMapping("/hello")
	private String getHello(Model m) {
		Book b = new Book();
		b.getAdminId();

		return new String("helloWorld");
	}
}
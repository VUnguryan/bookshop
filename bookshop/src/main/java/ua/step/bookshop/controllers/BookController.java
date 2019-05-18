package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.bookshop.models.Book;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private GenreRepository repoG;
	@Autowired
	private PublisherRepository repoP;
	
	
	@GetMapping("/books")
	private String getBooks(Model model) {
		model.addAttribute("books", repo.findByOrderByCreateDate());
		return "books";
	}

	@GetMapping("/hello")
	private String getHello(Model m) {
		Book b = new Book();
		b.getUser().getId();

		return new String("helloWorld");
	}
	
	@GetMapping("/books/addBook")
	private String addBook(Model model) {
		model.addAttribute("publishers", repoP.findAll());
		return "addBook";
	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(Model m) {
		return "helloWorld";
	}
}
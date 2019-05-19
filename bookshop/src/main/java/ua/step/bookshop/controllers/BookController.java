package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ua.step.bookshop.models.Book;
import ua.step.bookshop.repositories.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repo;
	private final int BOOKSONPAGE = 9;

	@GetMapping("/books")
	private String getBooks(Model model) {
		return getBooks(model, 1);
	}

	@GetMapping( "/books/{page}")
	private String getPagedBooks(Model model, @PathVariable int page) {
		return getBooks(model, page);
	}

	String getBooks(Model model, int page) {
		List<Book> allBooks = repo.findAll();
		List<Book> books = new ArrayList<>();

		int pages = (int) Math.ceil((double) allBooks.size() / BOOKSONPAGE);

		for(int i = (page-1) * BOOKSONPAGE; i < (page) * BOOKSONPAGE && i < allBooks.size(); i ++) {
			books.add(allBooks.get(i));
		}

		model.addAttribute("curpage", page);
		model.addAttribute("pages", pages);
		model.addAttribute("books", books);

		return "books";
	}
}
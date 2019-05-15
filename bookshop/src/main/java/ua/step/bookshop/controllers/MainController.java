package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;

@Controller
public class MainController {

	@Autowired
	private BookRepository repo;

	@GetMapping("/")
	private String getIndex(Model model) {
		model.addAttribute("books", repo.findAll());
		return "index";
	}
	
}

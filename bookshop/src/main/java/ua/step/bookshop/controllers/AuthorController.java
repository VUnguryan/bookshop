package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.repositories.AuthorRepository;

@Controller
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("/authors")
	private String getAuthors(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		return "authors";
	}

	@GetMapping("/authors/addAuthor")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		return "addAuthor";
	}

	@PostMapping("/authors/addAuthor")
	private String addAuthor(@ModelAttribute Author author) {
		authorRepository.saveAndFlush(author);
		return "redirect:/authors";
	}
}
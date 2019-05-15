package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ua.step.bookshop.repositories.AuthorRepository;


@Controller
public class AuthorController {

	@Autowired
	private AuthorRepository auth;

	@GetMapping("/authors")
	public String getGeneres(Model model) {
		model.addAttribute("authors", auth.findAll());
		return "authors";
	}
}

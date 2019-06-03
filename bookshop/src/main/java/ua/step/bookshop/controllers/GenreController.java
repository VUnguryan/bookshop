package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.step.bookshop.repositories.GenreRepository;

@Controller
public class GenreController {
	@Autowired
	private GenreRepository repo;

	@GetMapping("/genres")
	public String getGenres(Model model) {
		model.addAttribute("genres", repo.findAll());	
		return "genres";
	}
}
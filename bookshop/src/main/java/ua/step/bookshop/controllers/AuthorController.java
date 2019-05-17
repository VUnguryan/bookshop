package ua.step.bookshop.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.bookshop.models.Author;
import ua.step.bookshop.repositories.AuthorRepository;

/**
 * 
 * @author sergey TODO Осталось доделать только пагинацию
 *
 */

@Controller
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("/authors")
	private String getAuthors(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		return "authors";
	}

	@GetMapping("/authors/add")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		return "addAuthor";
	}

	@PostMapping("/authors/add")
	private String addAuthor(@ModelAttribute Author author) {
		boolean isEmty = true;
		List<Author> authors = authorRepository.findAll();
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getName().equals(author.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			authorRepository.saveAndFlush(author);
			return "redirect:/authors";
		} else {
			return "addAuthor";
		}
	}
}
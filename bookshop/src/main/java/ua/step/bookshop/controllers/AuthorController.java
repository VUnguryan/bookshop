package ua.step.bookshop.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository repo;
	@Autowired
	private GenreRepository repoJ;
	@Autowired
	private PublisherRepository repoP;
/*	@Autowired
	private AuthorRepository repoA;*/

	@GetMapping("/authors")
	private String getAuthors(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("genres", repoJ.findAll());
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("contentPage", "authors");
		//return "authors";
		return "index";
	}

	@GetMapping("/authors/add")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("genres", repoJ.findAll());
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("addAuthor", authorRepository.findAll());
		model.addAttribute("contentPage", "addAuthor");
		//return "addAuthor";
		return "index";
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
		//	return "redirect:/index";
		} else {
		//	return "addAuthor";
			return "redirect:/authors";
			//return "authors";
		}
	}
}
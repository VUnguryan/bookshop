package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;

@Controller
public class AuthorController {

	@Autowired
	private GenreRepository genreRepo;
	@Autowired
	private PublisherRepository publisherRepo;
	@Autowired
	private AuthorRepository authorRepo;

	private static char LETTERPAGE = 'С';

	@PostMapping("/authors")
	private String setLETTERPAGE(Model model, HttpSession session, HttpServletRequest request) {

		String s = String.valueOf(request.getParameter("letterInPage"));
		AuthorController.LETTERPAGE = s.charAt(0);

		return getAuthorsInPage(model, LETTERPAGE);
	}

	@GetMapping("/authors")
	private String getIndex(Model model) {
		return getAuthorsInPage(model, LETTERPAGE);
	}

	@GetMapping("/authors/{page}")
	private String getPaginatedIndex(Model model, @PathVariable char page) {
		return getAuthorsInPage(model, page);
	}

	private String getAuthorsInPage(Model model, char page) {
		ArrayList<Author> allAuthors = (ArrayList<Author>) authorRepo.findAll();
		ArrayList<Author> authors = new ArrayList<>();

		for (Author author : allAuthors) {
			if (author.getName().charAt(0) == page) {
				authors.add(author);
			}
		}

		model.addAttribute("curpage", page);
		model.addAttribute("authors", authors);
		model.addAttribute("contentPage", "authors");
		return "authors";
	}

	@GetMapping("/authors/add")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		model.addAttribute("genres", genreRepo.findAll());
		model.addAttribute("publishers", publisherRepo.findAll());
		model.addAttribute("addAuthor", authorRepo.findAll());
		model.addAttribute("contentPage", "addAuthor");
		return "index";
	}

	@PostMapping("/authors/add")
	private String addAuthor(@ModelAttribute Author author) {
		boolean isEmty = true;
		List<Author> authors = authorRepo.findAll();
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getName().equals(author.getName())) {
				isEmty = false;
			}
		}

		if (isEmty) {
			authorRepo.saveAndFlush(author);
			return "redirect:/authors";
		} else {
			return "redirect:/authors/add";
		}
	}
}
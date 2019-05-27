package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.repositories.AuthorRepository;
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
	private GenreRepository repoJ;
	@Autowired
	private PublisherRepository repoP;
	@Autowired
	private AuthorRepository repoA;

	private static int AUTHORSONPAGE = 9;

	@GetMapping("/authors")
	private String getIndex(Model model) {
		return getAuthorsInPage(model, 1);
	}

	@GetMapping("authors/{page}")
	private String getPaginatedIndex(Model model, @PathVariable int page) {
		return getAuthorsInPage(model, page);
	}

	String getAuthorsInPage(Model model, int page) {
		ArrayList<Author> allAuthors = (ArrayList<Author>) repoA.findAll();
		ArrayList<Author> authors = new ArrayList<>();
		int pages = 20;
		char letter = 'C';
		
		switch (page) {
		case 1:
			letter = 'А';
			break;
		case 2:
			letter = 'Б';
			break;
		case 3:
			letter = 'В';
			break;
		case 4:
			letter = 'Г';
			break;
		case 5:
			letter = 'Д';
			break;
		case 6:
			letter = 'Е';
			break;
		case 7:
			letter = 'Ё';
			break;
		case 8:
			letter = 'Ж';
			break;
		case 9:
			letter = 'З';
			break;
		case 10:
			letter = 'Г';
			break;

		}
		/*for (int i = 0; i < authors.size(); i++) {*/

			for (Author author : allAuthors) {
				if (author.getName().charAt(0) == letter) {
					authors.add(author);
				}
			}
		/*}*/
		// int pages = (int) Math.ceil((double) allAuthors.size() / AUTHORSONPAGE);
		/*
		 * for(int i = (page-1) * AUTHORSONPAGE; i < (page) * AUTHORSONPAGE && i <
		 * allAuthors.size(); i ++) { authors.add(allAuthors.get(i)); }
		 */

		model.addAttribute("curpage", page);
		// model.addAttribute("pages", pages);
		model.addAttribute("authors", authors);
		//model.addAttribute("sort", "ASC");
		model.addAttribute("contentPage", "authors");
		return "authors";
	}
	// Метод для обычной цифровой пагинации
	/*
	 * 
	 * String getAuthorsInPage(Model model, int page) { 
	 * List<Author> allAuthors = repoA.findAll(); 
	 * List<Author> authors = new ArrayList<>();
	 * 
	 * int pages = (int) Math.ceil((double) allAuthors.size() / AUTHORSONPAGE);
	 * for(int i = (page-1) * AUTHORSONPAGE; i < (page) * AUTHORSONPAGE && i <
	 * allAuthors.size(); i ++) { authors.add(allAuthors.get(i)); }
	 * 
	 * model.addAttribute("curpage", page); model.addAttribute("pages", pages);
	 * model.addAttribute("authors", authors); model.addAttribute("sort", "ASC");
	 * model.addAttribute("contentPage", "authors"); return "authors"; }
	 */

	/*
	 * private ArrayList<Author> getByLetter(Model model, char firtsaLetter){
	 * ArrayList<Author> arr = new ArrayList<>(); 
	 * for (Author author : myAuthors) {
	 * if(author.getName().charAt(0) == firtsaLetter) 
	 * { arr.add(author);
	 * System.out.println(author.getName()); 
	 * } 
	 * } return arr; }
	 */

	@GetMapping("/authors/add")
	private String getAddAuthor(@ModelAttribute Author author, Model model) {
		model.addAttribute("genres", repoJ.findAll());
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("addAuthor", repoA.findAll());
		model.addAttribute("contentPage", "addAuthor");
		return "index";
	}

	@PostMapping("/authors/add")
	private String addAuthor(@ModelAttribute Author author) {
		boolean isEmty = true;
		List<Author> authors = repoA.findAll();
		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getName().equals(author.getName())) {
				isEmty = false;
			}
		}
		if (isEmty) {
			repoA.saveAndFlush(author);
			return "redirect:/authors";
		} else {
			return "redirect:/authors/add";
		}
	}

}
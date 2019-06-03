package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Genre;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;

@Controller
public class GenreController {
	@Autowired
	private GenreRepository repoG;
	private BookRepository repoB;

/*	@GetMapping("/genres")
	public String getGenres(Model model) {
		model.addAttribute("genres", repoG.findAll());
		return "genres";
	}*/

	@GetMapping("/sortByGenre/{id}")
	private String getGenres(@PathVariable("id") Short id, Model model) {
		List<Book> allBooks = repoB.findAll();
		List<Book> sortsdByGenres = new ArrayList<Book>();
		List<Genre> genresList;

		for (Book book : allBooks) {
			genresList = book.getGenreList();
			for (Genre genre : genresList) {
				Short sortByGenreName = genre.getId();
				if (sortByGenreName == id) {
					sortsdByGenres.add(book);
				}
			}
		}
		model.addAttribute("books", sortsdByGenres);
		model.addAttribute("contentPage", "genres");
		return "index";
		//return "redirect:/books/" + id;
	}
}
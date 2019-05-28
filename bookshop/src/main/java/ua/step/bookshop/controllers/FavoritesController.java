package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Favorites;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.FavoritRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;
import ua.step.bookshop.security.UserDetailsServiceImpl;

/**
 * 
 * @author sergey TODO Осталось доделать только пагинацию
 *
 */

@Controller
public class FavoritesController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private FavoritRepository favorites;

	@GetMapping("/favorites")
	private String getFavorites(Model model) {
		Short idUser = UserDetailsServiceImpl.idUser;
		List<Book> favoritesList = new ArrayList<Book>();
		List<Favorites> list = favorites.findAll();
		for (int i = 0; i < list.size(); i++) {
			if(idUser== list.get(i).getIdUser()) {
				favoritesList.add(repo.getOne(list.get(i).getIdBook()));
			}
		}
		//favoritesList.add(repo.getOne(1));
		model.addAttribute("favorites", favoritesList);
		model.addAttribute("contentPage", "favorites");
		return "index";
	}
}
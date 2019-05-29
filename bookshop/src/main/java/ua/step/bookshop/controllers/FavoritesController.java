package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Favourites;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.FavouriteRepository;
import ua.step.bookshop.security.UserDetailsServiceImpl;

@Controller
public class FavoritesController {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private FavouriteRepository favouriteRepo;

	@GetMapping("/favorites")
	private String getFavorites(Model model) {
		Short idUser = UserDetailsServiceImpl.idUser;
		List<Book> favoritesList = new ArrayList<Book>();
		List<Favourites> list = favouriteRepo.findAll();
		for (int i = 0; i < list.size(); i++) {
			if(idUser== list.get(i).getIdUser()) {
				favoritesList.add(bookRepo.getOne(list.get(i).getIdBook()));
			}
		}
		model.addAttribute("favorites", favoritesList);
		model.addAttribute("contentPage", "favorites");
		return "index";
	}
}
package ua.step.bookshop.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.BookDTO;
import ua.step.bookshop.models.Favorites;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.*;

@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private GenreRepository genreRepo;
	@Autowired
	private PublisherRepository publisherRepo;
	@Autowired
	private AuthorRepository authorRepo;
	@Autowired
	private FavouriteRepository favouriteRepo;
	@Autowired
	private UserRepository userRepo;

	private static int BOOKSONPAGE = 9;

	@GetMapping("books/{page}")
	private String getPagedBooks(Model model, @PathVariable int page) {
		return getBooks(model, page);
	}

	String getBooks(Model model, int page) {
		List<Book> allBooks = bookRepo.findByOrderByCreateDate();
		List<Book> books = new ArrayList<>();
		int pages = (int) Math.ceil((double) allBooks.size() / BOOKSONPAGE);

		for (int i = (page - 1) * BOOKSONPAGE; i < (page) * BOOKSONPAGE && i < allBooks.size(); i++) {
			books.add(allBooks.get(i));
		}
		model.addAttribute("curpage", page);
		model.addAttribute("pages", pages);
		model.addAttribute("books", books);

		return "books";
	}

	@GetMapping("/books/addBook")
	private String addBook(Model model) {
		model.addAttribute("publishers", publisherRepo.findAll());
		model.addAttribute("genres", genreRepo.findAll());
		model.addAttribute("authors", authorRepo.findAll());
		model.addAttribute("contentPage", "addBook");
		return "index";
	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(@ModelAttribute("book") Book book) {
		Short idUs = getAuthUserId(userRepo);
		book.setCreateDate(Calendar.getInstance().getTime());
		book.setUser(userRepo.getOne(idUs));
		bookRepo.saveAndFlush(book);
		return "redirect:/";
	}

	@GetMapping("/books/show/{id}")
	private String showBook(@PathVariable("id") Integer id, Model model, HttpSession session,
			HttpServletRequest request) {
		BookDTO bookDto = new BookDTO();
		bookDto.setId(bookRepo.getOne(id).getId());
		bookDto.setName(bookRepo.getOne(id).getName());
		bookDto.setBackground(bookRepo.getOne(id).getBackground());
		bookDto.setPrice("" + bookRepo.getOne(id).getPrice());
		bookDto.setRate(4.0);
		bookDto.setUser(bookRepo.getOne(id).getUser());
		bookDto.setPublisher(bookRepo.getOne(id).getPublisher());
		bookDto.setGenreList(bookRepo.getOne(id).getGenres());
		bookDto.setAuthorList(bookRepo.getOne(id).getAuthors());
		Short idUs = getAuthUserId(userRepo);
		bookRepo.getOne(id).setRate(2.0);
		List<Favorites> favoritesList = favouriteRepo.findAll();

		boolean flag = false;
		for (int i = 0; i < favoritesList.size(); i++) {

			if (idUs == favoritesList.get(i).getIdUser() && id == favoritesList.get(i).getIdBook()) {
				flag = true;
			}
		}
		model.addAttribute("flag", flag);
		model.addAttribute("bookInf", bookRepo.getOne(id));
		model.addAttribute("userId", idUs);
		model.addAttribute("contentPage", "showBook");
		return "index";
	}

	@PostMapping("/books/favourite")
	private String favoriteBook(@RequestParam("idbook") Integer id,
			@RequestParam(value = "check", required = false) String check) {
		Short idUs = getAuthUserId(userRepo);

		if (check != null) {
			Favorites favorites = new Favorites();
			favorites.setIdUser(idUs);
			favorites.setIdBook(id);
			favouriteRepo.save(favorites);
		} else {
			List<Favorites> favoritesList = favouriteRepo.findAll();
			for (int i = 0; i < favoritesList.size(); i++) {

				if (favoritesList.get(i).getIdUser() == idUs && favoritesList.get(i).getIdBook() == id) {
					favouriteRepo.delete(favoritesList.get(i));
				}
			}
		}

		return "redirect:/books/show/" + id;
	}

	@GetMapping("/books/editBook/{id}")
	private String editBook(@PathVariable("id") Integer id, Model model) {
		if (bookRepo.getOne(id).getPublisher() != null) {
			model.addAttribute("publisherChecked", bookRepo.getOne(id).getPublisher());
		} else {
			model.addAttribute("publisherChecked", publisherRepo.getOne((short) 1));
		}
		model.addAttribute("book", bookRepo.getOne(id));
		model.addAttribute("publishers", publisherRepo.findAll());
		model.addAttribute("genres", genreRepo.findAll());
		model.addAttribute("authors", authorRepo.findAll());
		model.addAttribute("genreChecked", bookRepo.getOne(id).getGenres());
		model.addAttribute("authorsChecked", bookRepo.getOne(id).getAuthors());
		model.addAttribute("contentPage", "editBook");
		return "index";

	}

	@PostMapping("/books/editBook")
	private String editBookSubmit(@ModelAttribute("book") Book book) {
		book.setCreateDate(Calendar.getInstance().getTime());
		bookRepo.save(book);
		return "redirect:/";
	}

	private Short getAuthUserId(UserRepository repo) {
		Short id = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		if (!name.equals("anonymousUser")) {
			Optional<User> user = repo.findByLogin(name);
			id = user.get().getId();
		}

		return id;
	}
}
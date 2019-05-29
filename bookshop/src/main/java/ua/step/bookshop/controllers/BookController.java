package ua.step.bookshop.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Favourites;
import ua.step.bookshop.repositories.*;

import ua.step.bookshop.security.UserDetailsServiceImpl;

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

	@GetMapping("/books/addBook")
	private String addBook(Model model) {
		model.addAttribute("publishers", publisherRepo.findAll());
		model.addAttribute("genres", genreRepo.findAll());
		model.addAttribute("authors", authorRepo.findAll());
		model.addAttribute("contentPage", "addBook");
		return "index";
	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute("book") Book book) {
		String name = null;
		Short idUs = UserDetailsServiceImpl.idUser;
		book.setCreateDate(Calendar.getInstance().getTime());
		book.setUser(userRepo.getOne(idUs));

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				name = file.getOriginalFilename();

				String rootPath = new File("").getAbsolutePath() + "\\src\\main\\webapp\\images";
				File uploadedFile = new File(rootPath + File.separator + name);
				book.setBackground(name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();

				bookRepo.saveAndFlush(book);
				return "redirect:/";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			book.setBackground("no_image.png");
			bookRepo.saveAndFlush(book);
			return "redirect:/";
		}
	}

	@GetMapping("/books/show/{id}")
	private String showBook(@PathVariable("id") Integer id, Model model) {
		Short idUs = UserDetailsServiceImpl.idUser;
		List<Favourites> favoritesList = favouriteRepo.findAll();
		boolean flag = false;
		for (int i = 0; i < favoritesList.size(); i++) {
			if (idUs == favoritesList.get(i).getIdUser() && id == favoritesList.get(i).getIdBook()){
				flag = true;
			}
		}

		model.addAttribute("flag", flag);
		model.addAttribute("bookInf", bookRepo.getOne(id));
		model.addAttribute("userId", idUs);
		model.addAttribute("contentPage", "showBook");
		return "index";
	}

	@PostMapping("/books/favorite")
	private String favoriteBook(@RequestParam("idbook") Integer id, @RequestParam(value = "check", required = false) String check) {
		Short idUs = UserDetailsServiceImpl.idUser;

		if (check != null) {
			Favourites favorites = new Favourites();
			favorites.setIdUser(idUs);
			favorites.setIdBook(id);
			favouriteRepo.save(favorites);
		} else {
			List<Favourites> favoritesList = favouriteRepo.findAll();
			for (int i = 0; i < favoritesList.size(); i++) {
				if (favoritesList.get(i).getIdUser() == idUs && 
						favoritesList.get(i).getIdBook() == id) {
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
		model.addAttribute("genreChecked", bookRepo.getOne(id).getGenreList());
		model.addAttribute("authorsChecked", bookRepo.getOne(id).getAuthorList());
		model.addAttribute("contentPage", "editBook");
		return "index";
	}

	@PostMapping("/books/editBook")
	private String editBookSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute("book") Book book) {
		String name = null;
		book.setCreateDate(Calendar.getInstance().getTime());
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				name = file.getOriginalFilename();
				String rootPath = new File("").getAbsolutePath() + "\\src\\main\\webapp\\images";
				File uploadedFile = new File(rootPath + File.separator + name);
				book.setBackground(name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();

				bookRepo.save(book);
				return "redirect:/";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			int idBook = book.getId();
			book.setBackground(bookRepo.getOne(idBook).getBackground());
			bookRepo.save(book);
			return "redirect:/";
		}
	}
}
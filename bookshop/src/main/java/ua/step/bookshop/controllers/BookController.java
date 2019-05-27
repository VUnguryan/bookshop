package ua.step.bookshop.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Favorites;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.*;
import ua.step.bookshop.security.UserDetailsServiceImpl;


@Controller
public class BookController {
	@Autowired
	private BookRepository repo;
	@Autowired
	private GenreRepository repoG;
	@Autowired
	private PublisherRepository repoP;
	@Autowired
	private AuthorRepository repoA;
	@Autowired
	private FavoritRepository repoF;
	@Autowired
	private UserRepository repoU;

	private static int BOOKSONPAGE = 9;


	@GetMapping("books/{page}")
	private String getPagedBooks(Model model, @PathVariable int page) {
		return getBooks(model, page);
	}

	String getBooks(Model model, int page) {
		List<Book> allBooks = repo.findAll();
		List<Book> books = new ArrayList<>();
		int pages = (int) Math.ceil((double) allBooks.size() / BOOKSONPAGE);

		for(int i = (page-1) * BOOKSONPAGE; i < (page) * BOOKSONPAGE && i < allBooks.size(); i ++) {
			books.add(allBooks.get(i));
		}
		model.addAttribute("curpage", page);
		model.addAttribute("pages", pages);
		model.addAttribute("books", books);


		return "books";
	}

	@GetMapping("/books/addBook")
	private String addBook(Model model) {
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("genres", repoG.findAll());
		model.addAttribute("authors", repoA.findAll());
		model.addAttribute("contentPage", "addBook");
		return "index";
		//return "addBook";
	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute("book") Book book) {
		String name = null;
		Short idUs = UserDetailsServiceImpl.idUser;
		book.setCreateDate(Calendar.getInstance().getTime());
		book.setUser(repoU.getOne(idUs));
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				name = file.getOriginalFilename();

				String rootPath = new File("").getAbsolutePath()+"\\src\\main\\webapp\\images";
				File uploadedFile = new File(rootPath+ File.separator+ name);
				book.setBackground(name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();

				repo.saveAndFlush(book);
				return "redirect:/";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			book.setBackground("no_image.png");
			repo.saveAndFlush(book);
			return "redirect:/";
		}
	}

	@GetMapping("/books/show/{id}")
	private String showBook(@PathVariable("id") Integer id, Model model) {
		Short idUs = UserDetailsServiceImpl.idUser;
		List<Favorites> favoritesList = repoF.findAll();
		boolean flag =false;
		for (int i =0; i<favoritesList.size(); i++) {
			if(idUs == favoritesList.get(i).getIdUser() && id == favoritesList.get(i).getIdBook())
				flag =true;
		}
		model.addAttribute("flag", flag);
		model.addAttribute("bookInf", repo.getOne(id));
		model.addAttribute("userId", idUs);
		model.addAttribute("contentPage", "showBook");
		return "index";
		//return "showBook";
	}

	@PostMapping("/books/favorite")
	private String favoriteBook(@RequestParam("idbook") Integer id, @RequestParam(value = "check", required = false) String check)
	{
		Short idUs = UserDetailsServiceImpl.idUser;
		if(check != null){
			Favorites favorites = new Favorites();
			favorites.setIdUser(idUs);
			favorites.setIdBook(id);
			repoF.save(favorites);
		}else{
			List<Favorites> favoritesList = repoF.findAll();
			for (int i = 0; i<favoritesList.size(); i++){
				if(favoritesList.get(i).getIdUser() == idUs && favoritesList.get(i).getIdBook() == id){
					repoF.delete(favoritesList.get(i));
				}
			}
		}
		return "redirect:/books/show/"+id;
	}
	
	@GetMapping("/books/editBook/{id}")
	private String editBook(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("book", repo.getOne(id));
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("genres", repoG.findAll());
		model.addAttribute("authors", repoA.findAll());
		model.addAttribute("genreChecked", repo.getOne(id).getGenreList());
		model.addAttribute("authorsChecked", repo.getOne(id).getAuthorList());
		model.addAttribute("publisherChecked", repo.getOne(id).getPublisher());
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

				repo.save(book);
				return "redirect:/";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			int idBook = book.getId();
			book.setBackground(repo.getOne(idBook).getBackground());
			repo.save(book);
			return "redirect:/";
		}
	}
}
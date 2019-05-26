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
import ua.step.bookshop.repositories.*;
import ua.step.bookshop.security.WebSecurityConfig;

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

	private static int BOOKSONPAGE = 9;
	private Object id;

	//тут был метод на view books он безполезен и потому был удален что бы не путал

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
		return "addBook";
	}

	@PostMapping("/books/addBook")
	private String addBookSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute("book") Book book) {
		String name = null;
		book.setCreateDate(Calendar.getInstance().getTime());
		//book.setUser();
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
		List<Favorites> favoritesList = repoF.findAll();
		boolean flag =false;
		for (int i =0; i<favoritesList.size(); i++) {
			if(1 == favoritesList.get(i).getIdUser() && id == favoritesList.get(i).getIdBook())
				flag =true;
		}
		model.addAttribute("flag", flag);
		model.addAttribute("bookInf", repo.getOne(id));

		return "showBook";
	}

	@PostMapping("/books/favorite")
	private String favoriteBook(@RequestParam("idbook") Integer id, @RequestParam(value = "check", required = false) String check)
	{
		if(check != null){
			Favorites favorites = new Favorites();
			favorites.setIdUser((short) 1);
			favorites.setIdBook(id);
			repoF.save(favorites);
		}else{
			List<Favorites> favoritesList = repoF.findAll();
			for (int i = 0; i<favoritesList.size(); i++){
				if(favoritesList.get(i).getIdUser() == 1 && favoritesList.get(i).getIdBook() == id){
					repoF.delete(favoritesList.get(i));
				}
			}
		}
		return "redirect:/books/show/"+id;
	}
}
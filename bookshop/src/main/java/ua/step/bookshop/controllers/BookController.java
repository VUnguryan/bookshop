package ua.step.bookshop.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;

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

	private static int BOOKSONPAGE = 9;

	//тут был метод на view books он пезполезен и потому был удален что бы не путал

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
}
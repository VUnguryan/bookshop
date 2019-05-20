package ua.step.bookshop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.step.bookshop.models.Author;
import ua.step.bookshop.models.Book;
import ua.step.bookshop.models.Genre;
import ua.step.bookshop.models.Publisher;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.BookRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

	@GetMapping("/books")
	private String getBooks(Model model) {
		model.addAttribute("books", repo.findAll());
		return "books";
	}

	@GetMapping("/hello")
	private String getHello(Model m) {
		Book b = new Book();
		b.getUser().getId();

		return new String("helloWorld");
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


				repo.save(book);
				return "redirect:/";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			book.setBackground("no_image.png");
			repo.save(book);
			return "redirect:/";
		}

	}
}

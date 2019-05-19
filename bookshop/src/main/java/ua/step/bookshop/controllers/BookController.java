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
import java.util.Arrays;
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
	private String addBookSubmit(@RequestParam("file") MultipartFile file, @ModelAttribute("book") Book book,
								 @ModelAttribute("publishers") Short idP, @ModelAttribute("genres") Short idG,
								 @ModelAttribute("authors") Integer idA) {

		String name = null;
		Book newBook = book;
		Publisher publisher = repoP.getOne(idP);
		Genre genre = repoG.getOne(idG);
		newBook.setPublisher(publisher);
		newBook.setGenreList(Arrays.asList(genre));
		Author author = repoA.getOne(idA);
		newBook.setAuthorList(Arrays.asList(author));

		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				name = file.getOriginalFilename();

				String rootPath = new File("").getAbsolutePath()+"\\src\\main\\webapp\\images";
				File uploadedFile = new File(rootPath+ File.separator+ name);
				newBook.setBackground(name);
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
				stream.write(bytes);
				stream.flush();
				stream.close();


				repo.save(newBook);
				//book.setBackground(name);
				//repo.save(book);
				//return "redirect:/books/addBook";
				return "redirect:/";

			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}

	}
}

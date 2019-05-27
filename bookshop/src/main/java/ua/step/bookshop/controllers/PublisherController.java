package ua.step.bookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.bookshop.models.Author;
import ua.step.bookshop.models.Publisher;
import ua.step.bookshop.repositories.AuthorRepository;
import ua.step.bookshop.repositories.GenreRepository;
import ua.step.bookshop.repositories.PublisherRepository;


/**
 * 
 * @author bartalev
 *
 */

@Controller
public class PublisherController {
	
    @Autowired
    private PublisherRepository repo;
    @Autowired
	private GenreRepository repoJ;
	@Autowired
	private PublisherRepository repoP;
	@Autowired
	private AuthorRepository repoA;

    @GetMapping("/publishers")
    public String getController(Model model) {
    	model.addAttribute("genres", repoJ.findAll());
		model.addAttribute("publishers", repoP.findAll());
		model.addAttribute("authors", repoA.findAll());
		model.addAttribute("contentPage", "publishers");
		return "index";
    }

    @GetMapping("/publishers/add")
    private String getAddPublisher(@ModelAttribute Publisher publisher, Model model) {
    	model.addAttribute("genres", repoJ.findAll());
		model.addAttribute("authors", repoA.findAll());
		model.addAttribute("addPublisher", repoP.findAll());
		model.addAttribute("contentPage", "addPublisher");
		return "index";
    }

    @PostMapping("/publishers/add")
    private String addPublisher(@ModelAttribute Publisher publisher) {
        boolean isEmty = true;
        List<Publisher> publishers = repo.findAll();
        for (int i = 0; i < publishers.size(); i++) {
            if (publishers.get(i).getName().equals(publisher.getName())) {
                isEmty = false;
            }
        }
        if (isEmty) {
            repo.saveAndFlush(publisher);
            return "redirect:http://localhost:8080/publishers";
        } else {
            return "redirect:/publishers/add";
        }
    }
}
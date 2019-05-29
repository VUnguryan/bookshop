package ua.step.bookshop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ua.step.bookshop.models.Role;
import ua.step.bookshop.models.User;
import ua.step.bookshop.repositories.RoleRepository;
import ua.step.bookshop.repositories.UserRepository;

@Controller
public class RegistrationController {
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private UserRepository userRepo;

	@GetMapping(value = "/registration")
	public String getRegistrationPage() {
		return "registration";
	}

	@PostMapping(value = "/registration")
	private String addUser(@ModelAttribute User user) {
		boolean isEmpty = true;

		List<User> users = userRepo.findAll();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getLogin().equals(user.getLogin())) {
				isEmpty = false;
				break;
			}
		}
		if (isEmpty) {
			List<Role> userRole = roleRepo.findAll();
			for (int i = 0; i < userRole.size(); i++) {
				if (!userRole.get(i).getRole().equals("user")) {
					userRole.remove(i);
				}
			}
			user.setRoles(userRole);
			userRepo.saveAndFlush(user);
			return "redirect:/login";
		}
		return "registration";
	}
}
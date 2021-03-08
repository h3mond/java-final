package com.example.onlinevote.controllers;

import java.util.Optional;

import com.example.onlinevote.dto.UserDTO;
import com.example.onlinevote.models.Choice;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.repositories.ChoiceRepository;
import com.example.onlinevote.repositories.RoleRepository;
import com.example.onlinevote.repositories.UserRepository;
import com.example.onlinevote.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final GroupRepository groupRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@Autowired
	private final RoleRepository roleRepository;


	@Autowired
	private final ChoiceRepository resultRepository;


	@GetMapping
	public String users(Model model){
		model.addAttribute("users", userRepository.findAll());
		return "users/index";
	}


	@PostMapping("/delete")
	public String delete(Model model, @RequestParam int id){
		Choice result = new Choice();
		result.setUser(User.builder().id(id).build());
		resultRepository.delete(result);
		userRepository.deleteById(id);
		return "redirect:/users";
	}


	@GetMapping("/edit")
	public String editUser(Model model, @RequestParam(required = false, name = "id") int id){
		Optional<User> user = userRepository.findById(id);
		if(user == null){
			return "redirect:/users";
		}
		model.addAttribute("groups", groupRepository.findAll());
		model.addAttribute("roles", roleRepository.findAll());
		model.addAttribute("user", user.get());
		return "users/edit";
	}


	@PostMapping("/edit")
	public String editAction(@ModelAttribute("user") UserDTO user){
		User pUser = User.builder()
			.id(user.getId())
			.name(user.getName())
			.surname(user.getSurname())
			.username(user.getUsername())
			.password(passwordEncoder.encode(user.getPassword()))
			.role(roleRepository.getRoleByName(user.getRole()))
			.group(groupRepository.getGroupByName(user.getGroup()))
			.interests(user.getInterests())
			.build();

		userService.update(pUser);

		return "redirect:/users";
	}
}

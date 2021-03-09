package com.example.onlinevote.controllers.auth;

import com.example.onlinevote.dto.UserDTO;
import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.repositories.RoleRepository;
import com.example.onlinevote.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegisterController {
	@Autowired
	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private final RoleRepository roleRepository;

	@Autowired
	private final GroupRepository groupRepository;

	@Autowired
	private final UserService userService;


	@GetMapping("/register")
	public String signUpPage(Model model) {
		Iterable<Group> groupList = groupRepository.findAll();
		model.addAttribute("groups", groupList);
		return "auth/register";
	}


	@PostMapping("/register")
	public String signUp(@ModelAttribute("user") UserDTO user, Model model) {
		User pUser = User.builder()
			.name(user.getName())
			.surname(user.getSurname())
			.username(user.getUsername())
			.password(passwordEncoder.encode(user.getPassword()))
			.interests(user.getInterests())
			.group(groupRepository.getGroupByName(user.getGroup()))
			.role(roleRepository.getRoleByName("USER"))
			.build();

		userService.save(pUser);

		return "redirect:/login";
	}
}

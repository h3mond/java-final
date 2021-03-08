package com.example.onlinevote.controllers.auth;

import java.security.Principal;

import com.example.onlinevote.dto.UserDTO;
import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
	@Autowired
	private final UserService userService;

	@Autowired
	private final GroupRepository groupRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	@GetMapping
	public String ProfilePage(Model model, Principal principal) {
		User me = userService.getUserByUsername(principal.getName());
		model.addAttribute("me", me);
		return "auth/profile/profile";
	}

	@GetMapping("edit")
	public String editProfilePage(Model model, Principal principal) {
		User user= userService.getUserByUsername(principal.getName());
		Iterable<Group> groupList= groupRepository.findAll();

		model.addAttribute("me", user);
		model.addAttribute("groups", groupList);

		return "auth/profile/edit";
	}

  @PostMapping("edit")
  public String postProfilePage(@ModelAttribute("user") UserDTO user, Principal principal){
		User pUser = userService.getUserByUsername(principal.getName());

		pUser.setName(user.getName());
		pUser.setSurname(user.getSurname());
		if(user.getPassword() != null && !user.getPassword().isEmpty()){
			pUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		pUser.setInterests(user.getInterests());
		pUser.setGroup(groupRepository.getGroupByName(user.getGroup()));

		userService.update(pUser);

		return "redirect:/profile";
	}
}

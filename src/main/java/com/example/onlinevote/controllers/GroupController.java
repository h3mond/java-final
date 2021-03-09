package com.example.onlinevote.controllers;

import java.util.Optional;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.repositories.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
	@Autowired
	private final GroupRepository groupRepository;


	@GetMapping
	public String groups(Model model){
		model.addAttribute("groups", groupRepository.findAll());
		return "group/index";
	}

	@GetMapping("/add")
	public String addGroupPage(Model model) {
		model.addAttribute("group", new Group());
		return "group/add";
	}


	@PostMapping("/add")
	public String addGroup(@ModelAttribute Group group) {
		if(group.getId() != 0){
			Optional<Group> g = groupRepository.findById(group.getId());
			g.get().setName(group.getName());
		} else {
			groupRepository.save(group);
		}
		return "redirect:/group";
	}


	@PostMapping("/delete")
	public String delete(@RequestParam int id){
		groupRepository.deleteById(id);
		return "redirect:/group";
	}


	@GetMapping("/edit")
	public String editGroupPage(@RequestParam(required = false, name = "id") int id, Model model) {
		Optional<Group> group = groupRepository.findById(id);
		if(group == null){
			return "redirect:/group";
		}
		model.addAttribute("group", group.get());

		return "group/edit";
	}


	@PostMapping("/edit")
	public String edit(@ModelAttribute Group group){
		Optional<Group> g = groupRepository.findById(group.getId());
		if(g != null && g.isPresent()){
			g.get().setName(group.getName());
			groupRepository.save(g.get());
		}
		return "redirect:/group";
	}
}

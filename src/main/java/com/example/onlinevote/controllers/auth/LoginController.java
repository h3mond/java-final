package com.example.onlinevote.controllers.auth;

import com.example.onlinevote.hadoop.WordCount;
import com.example.onlinevote.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {
	@Autowired
	private final QuestionRepository questionRepository;

	@GetMapping("/login")
	public String LoginPage() {
		Thread thread = new Thread(new WordCount(questionRepository));
		thread.start();
		return "auth/login";
	}
}

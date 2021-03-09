package com.example.onlinevote.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

	@GetMapping("/login")
	public String LoginPage() {
		return "auth/login";
	}

}

package com.example.onlinevote.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.repositories.GroupRepository;
import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.repositories.QuizRepository;
import com.example.onlinevote.repositories.ScoreRepository;
import com.example.onlinevote.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	private final QuizRepository quizRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final QuestionRepository questionRepository;

	@Autowired
	private final GroupRepository groupRepository;

	@Autowired
	private final ScoreRepository scoreRepository;


	@GetMapping("/add")
	public String addQuiz(Model model) {
		model.addAttribute("quiz", new Quiz());
		model.addAttribute("questions", new HashSet<Question>());

		return "quiz/add";
	}


	@PostMapping("/add")
	public String saveQuiz(Principal principal, @ModelAttribute Quiz quiz) {
		quiz.setAuthor(userService.getUserByUsername(principal.getName()));
		quizRepository.save(quiz);

		return "redirect:/";
	}


	@PostMapping("/delete")
	public String delete(@RequestParam int id) {
		quizRepository.deleteById(id);

		return "redirect:/";
	}


	@GetMapping("/edit")
	public String quizEdit(@RequestParam Quiz quiz, Model model) {
		model.addAttribute("quiz", quiz);

		return "quiz/edit";
	}


	@GetMapping("/details/{quiz}")
	public String quizDetails(@PathVariable Quiz quiz, Model model) {
		List<Question> questions = questionRepository.findByQuizId(quiz.getId());
		if(questions == null || questions.isEmpty()){
			questions = new ArrayList<>();
		}
		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questions);

		return "quiz/details";
	}


	@GetMapping("/statistics/{quiz}")
	public String getStatistics(@PathVariable(name = "quiz") Quiz quiz, Model model) {
		model.addAttribute("scores", scoreRepository.getAllByQuiz(quiz));
		model.addAttribute("groups", groupRepository.findAll());

		return "quiz/statistics";
	}
}

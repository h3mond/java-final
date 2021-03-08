package com.example.onlinevote.controllers;

import java.util.List;

import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.repositories.QuestionRepository;

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
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	private final QuestionRepository questionRepository;


	@PostMapping("/add")
	public String questionAdd(Model model, @RequestParam Quiz quiz, @ModelAttribute Question question){
		question.setQuiz(quiz);
		questionRepository.save(question);

		int id = quiz.getId();

		model.addAttribute("quiz", quiz);
		model.addAttribute("questions", questionRepository.findByQuizId(id));

		return "redirect:/quiz/details/" + id;
	}


	@PostMapping("/remove/{question}")
	public String removeQuestion(@PathVariable Question question) {
		questionRepository.delete(question);
		return "redirect:/quiz/details/" + question.getQuiz().getId();
	}


	@GetMapping("/edit/{question}")
	public String editQuestion(Model model, @PathVariable Question question) {
		model.addAttribute("question", question);
		return "question/edit";
	}


	@PostMapping("/edit")
	public String editQuestionPost(@ModelAttribute Question question) {
		Question q = questionRepository.save(question);
		return "redirect:/quiz/details/" + q.getQuiz().getId();
	}
}

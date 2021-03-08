package com.example.onlinevote.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.example.onlinevote.dto.ChoiceDTO;
import com.example.onlinevote.dto.QuestionDTO;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Choice;
import com.example.onlinevote.models.Score;
import com.example.onlinevote.models.User;
import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.repositories.QuizRepository;
import com.example.onlinevote.repositories.ChoiceRepository;
import com.example.onlinevote.repositories.ScoreRepository;
import com.example.onlinevote.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {
	@Autowired
	private final QuizRepository quizRepository;

	@Autowired
	private final ScoreRepository scoreRepository;

	@Autowired
	private final ChoiceRepository choiceRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final QuestionRepository questionRepository;


	@GetMapping
	public String index(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
		Iterable<Quiz> quizzes;
		if (filter != null && !filter.isEmpty()) {
			quizzes = quizRepository.findByTag(filter);
		} else {
			quizzes = quizRepository.findAll();
		}
		model.addAttribute("quizzes", quizzes);

		return "index";
	}


	@GetMapping("/score/{score}")
	public String getScore(@PathVariable(name = "score") int score, Model model) {
		Optional<Score> scoreObj = scoreRepository.findById(score);
		if (scoreObj.isPresent()) {
			model.addAttribute("scoreObj", scoreObj.get());
			return "score-page";
		}

		return "error-page";
	}


	@GetMapping("/pass/{quiz}")
	public String passQuiz(@PathVariable(name = "quiz") Quiz quiz, Principal principal, Model model) {
		User user = userService.getUserByUsername(principal.getName());
		Score score = scoreRepository.getUserScoreByQuiz(user.getId(), quiz.getId());
		if (score != null) {
			return "redirect:/score/" + score.getId();
		}

		model.addAttribute("quiz", quiz);
		model.addAttribute("questionDTO", new QuestionDTO());

		return "quiz/pass";
	}


	@PostMapping("/pass/{quiz}")
	public String passQuizPost(@PathVariable(name = "quiz") Quiz quiz,
			Principal principal, Model model, 
			HttpServletRequest httpServletRequest) {
		User user = userService.getUserByUsername(principal.getName());

		// List<Choice> choices = new ArrayList<>();
		List<Choice> choices = new ArrayList<>();

		for(Question q : quiz.getQuestions()){
			ChoiceDTO c = new ChoiceDTO(q.getId(), httpServletRequest.getParameter(String.valueOf(q.getId())));
			Choice r = choiceRepository.save(new Choice( user, questionRepository.getById(c.getQuestionId()), c.getAnswerId()));
			choices.add(r);
		}

		// quiz.getQuestions().forEach(e -> choices.add(new Choice(e.getId(), httpServletRequest.getParameter(String.valueOf(e.getId())))));
		// choices.forEach(e -> resultRepository.save(new Result(user, questionRepository.getById(e.getQuestionId()), e.getAnswerId())));

		// List<Result> resultList = resultRepository.getByQuestionQuizAndUser(quiz, user);

		Score score = new Score();
		score.setQuiz(quiz);
		score.setUser(user);

		for(Choice result : choices){
			if(result.isCorrect()){
				score.addScore();
			}
		}
		
		Score s = scoreRepository.save(score);

		return "redirect:/score/" + s.getId();

	}
}

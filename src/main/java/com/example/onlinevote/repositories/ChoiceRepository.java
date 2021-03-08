package com.example.onlinevote.repositories;

import com.example.onlinevote.models.Group;
import com.example.onlinevote.models.Question;
import com.example.onlinevote.models.Quiz;
import com.example.onlinevote.models.Choice;
import com.example.onlinevote.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChoiceRepository extends CrudRepository<Choice, Integer> {
	List<Choice> getAllByQuestionQuiz(Quiz quiz);

	List<Choice> getAllByQuestionQuizAndUserGroup(Quiz quiz, Group user_group);

	List<Choice> getByQuestionQuizAndUser(Quiz question_quiz, User user);

	List<Choice> getAllByQuestion(Question question);
}

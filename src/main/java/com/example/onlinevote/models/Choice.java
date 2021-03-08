package com.example.onlinevote.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Choice {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade=CascadeType.REMOVE, fetch=FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;

	private Integer answer;

	public Choice(User user, Question question, Integer answer) {
		this.user = user;
		this.question = question;
		this.answer = answer;
		isCorrect();
	}

	public boolean isCorrect() {
		return this.question.getAnswerNumber().equals(this.answer);
	}
}

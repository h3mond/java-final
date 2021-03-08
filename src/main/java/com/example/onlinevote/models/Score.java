package com.example.onlinevote.models;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	private int score;

	public Score(User user, Quiz quiz, int score) {
		this.user = user;
		this.quiz = quiz;
		this.score = score;
	}

	public Score() {
		score = 0;
	}

	public void addScore() {
		this.score++;
	}
}

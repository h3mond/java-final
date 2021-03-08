package com.example.onlinevote.models;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Setter
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Length(max = 5000, message = "Too long description!")
	private String text;

	@ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "quiz_id", nullable = true)
	private Quiz quiz;

	@ElementCollection
	@CollectionTable(name = "option")
	@MapKeyColumn(name = "number")
	@Column(name = "description")
	private Map<Integer, String> options;

	private Integer answerNumber;

	public Question(String text, Quiz quiz) {
		this.text = text;
		this.quiz = quiz;
		this.options = new HashMap<>();
	}

	public Question() {
	}
}

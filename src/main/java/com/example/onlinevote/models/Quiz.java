package com.example.onlinevote.models;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@javax.validation.constraints.NotBlank(message = "Name of Quiz can''t be empty!")
	private String name;

	@Length(max = 2048, message = "Too long description!")
	private String text;

	private String tag;

	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User author;

	@OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
	private Set<Question> questions;

	public Quiz(String name, String text, String tag, User author) {
		this.name = name;
		this.text = text;
		this.tag = tag;
		this.author = author;
		this.questions = new HashSet<>();
	}

	public Quiz() {
	}

	public void addQuestion(Question question) {
		this.questions.add(question);
	}

	public void removeQuestion(Question question) {
		this.questions.remove(question);
	}
}

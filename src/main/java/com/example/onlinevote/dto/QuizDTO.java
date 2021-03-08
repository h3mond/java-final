package com.example.onlinevote.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDTO {
	private int id;
	private String name;

	public QuizDTO(int id, String name) {
		setId(id);
		setName(name);
	}
}

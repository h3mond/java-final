package com.example.onlinevote.dto;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
	private int id;
	private String text;
	private Integer answerNumber;
	private Map<Integer, String> options;

	public QuestionDTO(int id, String text, Map<Integer, String> options) {
		this.id = id;
		this.text = text;
		this.options = options;
	}

	public QuestionDTO() {
	}
}

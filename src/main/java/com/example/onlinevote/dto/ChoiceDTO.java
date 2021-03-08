package com.example.onlinevote.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChoiceDTO {
	private int questionId;
	private int answerId;

	public ChoiceDTO(int id, String parameter) {
		this.questionId = id;
		this.answerId = Integer.parseInt(parameter);
	}
}

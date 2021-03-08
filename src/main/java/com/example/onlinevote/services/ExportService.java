package com.example.onlinevote.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.example.onlinevote.models.Question;
import com.example.onlinevote.repositories.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ExportService implements Runnable {
	@Autowired
	private final QuestionRepository questionRepository;

	public void run(){
		try {
			Iterable<Question> questions = questionRepository.findAll();

			File fout = new File("questions.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			questions.forEach(q -> {
				try {
					osw.write(q.getText() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			osw.close();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}

package com.example.onlinevote.hadoop;

import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.services.ExportService;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class WordCount implements Runnable {
	@Autowired
	private final QuestionRepository questionRepository;

	@Override
	public void run() {
		Thread thread = new Thread(new ExportService(questionRepository));
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.println("DONE");
		}
	}
}

package com.example.onlinevote.hadoop;

import java.io.IOException;

import com.example.onlinevote.repositories.QuestionRepository;
import com.example.onlinevote.services.ExportService;

import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WordCount implements Runnable {
	@Autowired
	private final QuestionRepository questionRepository;


	public static class Map extends Mapper<Object, Text, Text, IntWritable> {
		private static final IntWritable ONE = new IntWritable(1);
		private Text word = new Text();

		@Override
		protected void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			StringTokenizer tokenizer = new StringTokenizer(value.toString());
			while (tokenizer.hasMoreTokens()) {
				String token = tokenizer.nextToken();
				word.set(token);
				context.write(word, ONE);
			}
		}
	}


	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
		private IntWritable count = new IntWritable();

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {

			int sum = 0;
			for (IntWritable value : values) {
				sum += value.get();
			}
			count.set(sum);
			context.write(key, count);
		}
	}


	@Override
	public void run() {
		Thread thread = new Thread(new ExportService(questionRepository));
		thread.start();

		try {
			thread.join();

			Configuration conf = new Configuration();
			Job job = new Job(conf, "wordcount");
			job.setJarByClass(WordCount.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			job.setMapperClass(Map.class);
			job.setReducerClass(Reduce.class);

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);

			FileInputFormat.setInputPaths(job, new Path("/home/bekarys/l/online-vote/questions.txt"));
			FileOutputFormat.setOutputPath(job, new Path("/home/bekarys/l/online-vote/count.txt"));

			boolean success = job.waitForCompletion(true);
			if(success){
				System.out.println("DONE");
			} else {
				System.out.println("FAILED");
			}
		} catch (InterruptedException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			thread.interrupt();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

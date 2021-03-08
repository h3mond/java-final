package com.example.onlinevote.hadoop;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class ResultExecutor implements Runnable {

	@Override
	public void run() {
		// Job job = Job.getInstance();
		// job.setJarByClass(ResultExecutor.class);
		// job.setMapperClass(ResultMapper.class);
		// job.setReducerClass(ResultReducer.class);
		// job.setOutputKeyClass(IntWritable.class);
		// job.setOutputValueClass(Text.class);
		// FileInputFormat.addInputPaths(job, "questions.txt");
		// //provide output folder path below
		// FileOutputFormat.setOutputPath(job, new Path(""));

		// System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}

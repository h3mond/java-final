package com.example.onlinevote.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ResultMapper extends Mapper<Object, Text, IntWritable, Text> {
	public void map(Object key, Text text, Context context){
	}
}

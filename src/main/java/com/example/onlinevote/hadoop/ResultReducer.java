package com.example.onlinevote.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ResultReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
}

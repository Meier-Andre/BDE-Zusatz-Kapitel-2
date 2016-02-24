package de.fhms.am.ueb2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MRCount {
	private final static IntWritable one  = new IntWritable(1); 


	public static void main(String[] args) throws Exception {
		
		if (args.length != 2){
			System.err.println("Usage: CarCounter <input> <output>");
			System.exit(2);
		}
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);
		job.setJarByClass(MRCount.class);
		job.setMapperClass(CounterMapping.class);
		job.setReducerClass(CounterReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

	public static class CounterMapping extends Mapper<Object, Text, Text, IntWritable> {

		private Text carname = new Text();

		public void map(Object key, Text value,  Context context) throws IOException, InterruptedException{

			String name = value.toString();
			carname.set(name);
			context.write(carname, one);

		}
	}

	public static class CounterReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

		private IntWritable total = new IntWritable();

		@Override
		protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int sum = 0;

			for (IntWritable val : values){
				sum += val.get();
			}
			total.set(sum);
			context.write(key, total);
		}
	}
}

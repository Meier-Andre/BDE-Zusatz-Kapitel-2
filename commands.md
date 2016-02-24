Nach Erstellung einer jar aus der Klasse AutomarkenGen.class erhalten wir eine Datei namens carbrands.txt, welche 150000 mal Automarken enthält, repliziert aus der in der Klasse definierten String Array.

``` [cloudera@quickstart ~]$ hadoop jar carbrands.jar carbrands.txt
[cloudera@quickstart ~]$ hdfs dfs -cat carbrands.txt | less
```
Durch den MapReduce Job soll nun die Häufigkeit der verschiedenen Automarken gezählt werden. Zunaechst wird die Fehlermeldung gecheckt, dann wird der korrekte Aufruf angegeben. 

``` 
[cloudera@quickstart ~]$ hadoop jar am_ueb2.jar carbrands.txt
Usage: CarCounter <input> <output>
[cloudera@quickstart ~]$ hadoop jar am_ueb2.jar carbrands.txt carbrands/
16/02/24 09:43:36 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
16/02/24 09:43:37 WARN mapreduce.JobResourceUploader: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
16/02/24 09:43:38 INFO input.FileInputFormat: Total input paths to process : 1
16/02/24 09:43:38 INFO mapreduce.JobSubmitter: number of splits:1
16/02/24 09:43:38 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1456326488702_0019
16/02/24 09:43:39 INFO impl.YarnClientImpl: Submitted application application_1456326488702_0019
16/02/24 09:43:39 INFO mapreduce.Job: The url to track the job: http://quickstart.cloudera:8088/proxy/application_1456326488702_0019/
16/02/24 09:43:39 INFO mapreduce.Job: Running job: job_1456326488702_0019
16/02/24 09:43:52 INFO mapreduce.Job: Job job_1456326488702_0019 running in uber mode : false
16/02/24 09:43:52 INFO mapreduce.Job:  map 0% reduce 0%
16/02/24 09:44:05 INFO mapreduce.Job:  map 100% reduce 0%
16/02/24 09:44:17 INFO mapreduce.Job:  map 100% reduce 100%
16/02/24 09:44:18 INFO mapreduce.Job: Job job_1456326488702_0019 completed successfully
16/02/24 09:44:18 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=1906674
		FILE: Number of bytes written=4036879
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=1006786
		HDFS: Number of bytes written=127
		HDFS: Number of read operations=6
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters 
		Launched map tasks=1
		Launched reduce tasks=1
		Data-local map tasks=1
		Total time spent by all maps in occupied slots (ms)=9945
		Total time spent by all reduces in occupied slots (ms)=9876
		Total time spent by all map tasks (ms)=9945
		Total time spent by all reduce tasks (ms)=9876
		Total vcore-seconds taken by all map tasks=9945
		Total vcore-seconds taken by all reduce tasks=9876
		Total megabyte-seconds taken by all map tasks=10183680
		Total megabyte-seconds taken by all reduce tasks=10113024
	Map-Reduce Framework
		Map input records=150001
		Map output records=150001
		Map output bytes=1606666
		Map output materialized bytes=1906674
		Input split bytes=124
		Combine input records=0
		Combine output records=0
		Reduce input groups=10
		Reduce shuffle bytes=1906674
		Reduce input records=150001
		Reduce output records=10
		Spilled Records=300002
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=145
		CPU time spent (ms)=3730
		Physical memory (bytes) snapshot=410554368
		Virtual memory (bytes) snapshot=3008634880
		Total committed heap usage (bytes)=351342592
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters 
		Bytes Read=1006662
	File Output Format Counters 
		Bytes Written=127
``` 

Betrachtet man nun das Output, erkennt man die erfolgreiche Durchführung:

``` 
[cloudera@quickstart ~]$ hdfs dfs -cat carbrands/part-r-00000
Audi	14849
BMW	14804
Bugatti	14895
Ferrari	14930
Fiat	15189
Lada	15020
Lamborghini	15256
Mercedes	14890
Porsche	15194
VW	14974
``` 


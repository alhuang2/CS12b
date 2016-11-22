// Alston Huang
// 1471706
// CMPS12b PA4
// Simulates process of given jobs with range of 1 - jobs-1 processors
// Simulation.java

import java.io.*;
import java.util.Scanner;

public class Simulation{

	public static void main(String[] args) throws IOException {
		Scanner sc = null;
		PrintWriter report = null;
		PrintWriter trace = null;
		int numJobs, time=0;
		Job j;
		Queue jobStorage = new Queue();
		Queue storageQueue = new Queue();
		Queue finishedStorage = new Queue();
		Queue[] pq = null;

		//check command line argument and file required.
		try{
			if( args.length != 1 ){
				System.out.println("Usage: Simulation inputfile");
				System.exit(1);
			}

			sc = new Scanner( new File(args[0]) );
			report = new PrintWriter( new FileWriter(args[0] + ".rpt") );
			trace = new PrintWriter( new FileWriter(args[0] + ".trc") );
		}
		catch(FileNotFoundException e){
			System.out.println("Caught Exception " + e);
			System.exit(1);
		}

		numJobs = Integer.parseInt( sc.nextLine() );

		//create storage of job info
		while( sc.hasNextLine() ){
			j = getJob(sc);
			jobStorage.enqueue(j);
		}

		//heading for .trc and .rpt files
		trace.println("Trace file: " + args[0] + ".trc");
		trace.println(numJobs + " Jobs:");
		trace.println(jobStorage + "\n");
		report.println("Report file: " + args[0] + ".rpt");
		report.println(numJobs + " Jobs:");
		report.println(jobStorage);
		report.println("******************************************************");

		//run simulation with n processors ranging from 1 - numJobs-1
		for( int n=1; n<=numJobs-1; n++){
			int totalWait = 0, maxWait = 0;
			double avgWait = 0.0;

			for( int i=1; i<jobStorage.length()+1; i++ ){
				j = (Job) jobStorage.dequeue();
				storageQueue.enqueue(j);
				jobStorage.enqueue(j);
			} 

			pq = new Queue[n+2];
			for(int i = 1; i < n+1; i++ ){
				pq[i] = new Queue();
			}

			trace.println("******************************************************");
			trace.println(n + " processors");
			trace.println("******************************************************");
			trace.println("time = " + time );
			trace.println("0: " + storageQueue + finishedStorage);
			for( int i=1; i<n+1; i++ ){
				trace.println(i + ": " + pq[i] );
			}

			//while unprocsesed jobs remain...
			while( finishedStorage.length() != numJobs ){
				int temp = -1, 
				index = 1, 
				tempTime = 100, 
				length = -1, 
				lengthF = -1;
				Job peeks = null;

				if( !storageQueue.isEmpty() ){
					peeks = (Job)storageQueue.peek();
					tempTime = peeks.getArrival();
					index = 0;	
				}

				//grabs time of finished jobs
				for(int i=1; i<n+1; i++){
					if( pq[i].length() != 0 ){
						peeks = (Job) pq[i].peek();
						temp = peeks.getFinish();
					}
					if( temp < tempTime && temp != -1 ){
						tempTime = temp;
						index = i;
					}
					time = tempTime;
				}

				if( index == 0 ){
					int tempIndex = 1;
					lengthF = pq[tempIndex].length();
					for( int i=1; i<n+1; i++ ){
						length = pq[i].length();
						if( length < lengthF ){
							lengthF = length;
							tempIndex = i;
						}
					}
					peeks = (Job) storageQueue.dequeue();
					pq[tempIndex].enqueue(peeks);
					if( pq[tempIndex].length() == 1 ){
						peeks = (Job) pq[tempIndex].peek();
						peeks.computeFinishTime(time);
					}
				}
				else{
					peeks = (Job) pq[index].dequeue();
					finishedStorage.enqueue(peeks);
					int tempWait = peeks.getWaitTime();
					if( tempWait > maxWait )
						maxWait = tempWait;
					totalWait = totalWait + tempWait;
					if( pq[index].length() >=1 ){
						peeks = (Job) pq[index].peek();
						peeks.computeFinishTime(time);
					}
				}

				trace.println();
				trace.println("time = " + time );
				trace.println("0: " + storageQueue + finishedStorage );
				for( int i=1; i<n+1; i++ ){
					trace.println(i + ": " + pq[i] );
				}

			}
			avgWait = (double) totalWait/numJobs;
			trace.println();
			report.println(n + " processors: totalWait = " + totalWait 
				+ ", maxWait = " + maxWait + 
				", averageWait= " + avgWait);
			time = 0;
			finishedStorage.dequeueAll();
		}

		trace.close();
		sc.close();
		report.close();
	}

	public static Job getJob(Scanner in){
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
}


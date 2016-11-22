// Alston Huang
// 1471706
// CMPS12b PA4
// Simulates process of given jobs with range of 1 - jobs-1 processors
// Simulation.java

import java.io.*;
import java.util.Scanner;

public class Simulation{
	public static void main(String[] args) throws IOException{

		if( args.length != 1 ){
			System.err.println("Usage: Simulation inputfile");
			System.exit(1);
		}

		//variables
		Scanner sc = new Scanner(new File( args[0]) );
		PrintWriter report = new PrintWriter( new FileWriter(args[0] + ".rpt") );
		PrintWriter trace = new PrintWriter( new FileWriter(args[0] + ".trc") );
		int totalWait, maxWait;
		double avgWait;
		Queue storage = new Queue();

		//get number of jobs
		String numJobs = sc.nextLine();
		int m = Integer.parseInt(numJobs);

    	while(sc.hasNext()){
        	storage.enqueue((Job)getJob(sc));
    	}


		//heading for files
		trace.println("Trace file: " + args[0] + ".trc");
		trace.println(m + " Jobs:");
		trace.println(storage + "\n");
		report.println("Report file: " + args[0] + ".rpt");
		report.println(numJobs + " Jobs:");
		report.println(storage);
		report.println("******************************************************");

		//main loop. create a range of processors from 1-m-1
		for( int n=1; n<m; n++ ){
			int time = 0;

			Queue[] pq = new Queue[n+1];
			for( int i=0; i<=n; i++ )
				pq[i] = new Queue();

			//copy storage into array
			for( int i=0; i<m; i++ ){
				Job j = (Job) storage.dequeue();
				j.resetFinishTime();
				pq[0].enqueue(j);
				storage.enqueue(j);

			}

			trace.println("******************************************************");
			//singular vs plural
			trace.println( n == 1? n + " processor:" : n + " processors" );
			trace.println("******************************************************");

			while( pq[0].isEmpty() || pq[0].length() != m || ((Job) pq[0].peek()).getFinish() == -1 ){

				int[] t = new int[pq.length];

				if( time == 0 ){
					trace.println("time=0");
					for(int i=0; i<=n; i++)
						trace.println(i + ": " + pq[i]);
				}
				trace.println();

				//calculates the next time
				if( time==0 && !pq[0].isEmpty() ){
					time = ( (Job) pq[0].peek() ).getArrival();
				}
				else if( !pq[0].isEmpty() ){
					Job k = (Job) pq[0].peek();
					if( k.getFinish() == -1 )
						t[0] = k.getArrival();
				}

				for( int i=1; i<pq.length; i++ ){
					if( !pq[i].isEmpty() )
						t[i] = ( (Job) pq[i].peek() ).getFinish();
				}
				sort(t);

				for( int i=0; i<t.length; i++ ){
					// if( t[i] == 0 ) 
					// 	continue;
					if( t[i] != 0 ){
						time = t[i];
						break;
					}
				}//

				//store back job if finishtime is calculated and calculate finishtime of
				//any upcoming jobs
				for( int i=1; i<=n; i++ ){
					if( !pq[i].isEmpty() && ( (Job)pq[i].peek() ).getFinish() == time ){
						pq[0].enqueue( pq[i].dequeue() );
						if( pq[i].length()>0 && ( (Job)pq[i].peek() ).getFinish() == -1 ){
							( (Job)pq[i].peek() ).computeFinishTime(time);
						}
					}
				}//

				//put jobs in processors
				if( pq.length-1==1 && !pq[0].isEmpty() && ( (Job)pq[0].peek() ).getArrival() == time )
					pq[1].enqueue(pq[0].dequeue());
				else{
					if( !pq[0].isEmpty() && ( (Job)pq[0].peek() ).getArrival() == time ){
						int[] length = new int[pq.length-1];
						for( int i=0; i<length.length; i++ )
							length[i] = pq[i+1].length();
						int index = 0;
						int min = length[0];
						for( int i=1; i<length.length; i++ ){
							if( length[i] < min ){
								min = length[i];
								index = i;
							}
						}
						pq[index+1].enqueue(pq[0].dequeue());
					}
				}
				for( int i=1; i<pq.length; i++ ){
					if( !pq[i].isEmpty() && ( (Job)pq[i].peek() ).getFinish() == -1 )
						((Job)pq[i].peek()).computeFinishTime(time);
				}//

				//computing is done. print out job process
				trace.println("time=" + time);
				for( int i=0; i<=n; i++ ){
					trace.println(i + ": " + pq[i] );
				}//

			}
			//calculate avg & total time
			totalWait = 0;
			int[] allTimes = new int[pq[0].length()];
			for( int i=0; i<allTimes.length; i++ ){
				allTimes[i] = ( (Job)pq[0].peek() ).getWaitTime();
				totalWait = totalWait + allTimes[i];
				pq[0].enqueue( pq[0].dequeue() );
			}
			sort(allTimes);
			maxWait = allTimes[allTimes.length-1];
			avgWait = (double)totalWait/(double)allTimes.length;

			//print to report file
			report.println(n==1? n + " processor: " : n + " processors: " );
			report.println("totalWait=" + totalWait + ", maxWait=" + maxWait + ", averageWait=" + avgWait );
		}

		trace.close();
		report.close();
		sc.close();

	}

	public static Job getJob(Scanner in){
    	String[] s = in.nextLine().split(" ");
    	int a = Integer.parseInt(s[0]);
    	int d = Integer.parseInt(s[1]);
     	return new Job(a,d);
   }
   public static void sort(int[] time){
      for(int k = time.length-1; k>0;k--){
         for(int j = 1; j<=k; j++){
            if(time[j]<time[j-1]){
               int temp = time[j];
               time[j] = time[j-1];
               time[j-1] = temp;
            }
         }
      }
   }


}
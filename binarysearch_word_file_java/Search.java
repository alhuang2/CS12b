/*
Name: Alston Huang
ID: 1471706
Class: CMPS012b
Role: Determines if words entered in command line is found in a txt file as well as
determines the location of the number
File name: Search.java
*/

import java.util.Scanner;
import java.io.*;

class Search{
	public static void main(String[] args) throws IOException {

		int lineCount = 0, position = 0, line;

		if(args.length < 2){
			System.err.println("Error! Usage: Search file target1 [target2 target3]");
			System.exit(1);
		}

		Scanner sc = new Scanner( new File(args[0]) ); 
		Scanner in = new Scanner( new File(args[0]) );

		//grab # of lines as well # of words since each word is in a new line
		while( sc.hasNextLine() ){  				
			sc.nextLine();						
			lineCount++;
		}
		sc.close();

		//assign each word from txt file to a string array "words"
		String[] words = new String[lineCount];		
		while( in.hasNextLine() ){ 					
			words[position] = in.nextLine(); 	
			position++;
		}
		in.close();
		
		//sort everything to prepare for binarySearch
		mergeSort(words, 0, words.length-1);

		//finds target and line number
		for(int i=1; i<args.length; i++){			
			Scanner in2 = new Scanner( new File(args[0]) );		//reset scanner for every iteration of i
			int target = binarySearch(words, 0, words.length, args[i]);		
			if(target == -1){
				System.out.println(args[i] + " was not found");
			}
			else
				//if binarySearch returned a index in which the word was found,
				//iterate that word throughout the txt file and compare them to find the line number.
				for(int k=0; k<lineCount; k++){		
					if( words[target].compareTo(in2.nextLine()) == 0){
						line = k+1;
						System.out.println(words[target] + " was found at line " + line);
					}
				}
			}
		}

		public static int binarySearch(String[] str, int p, int r, String target){
			int q;
			if(p >= r){
				return -1;
			}
			else{
				q = (p+r)/2;
				if(target.compareTo(str[q]) == 0  ) 
					return q;
				else if(target.compareTo(str[q]) < 0 )
					return binarySearch(str, p, q, target );
				else 
					return binarySearch(str, q+1, r, target);

			}

		}

		public static void mergeSort(String[] A, int p, int r){
			int q;
			if(p < r){
				q = (p+r)/2;
				mergeSort(A, p, q);
				mergeSort(A, q+1, r);
				merge(A, p, q, r);
			}
		}

		public static void merge(String[] A, int p, int q, int r){
			int n1 = q-p+1;
			int n2 = r-q;
			String[] L = new String[n1];
			String[] R = new String[n2];
			int i, j, k;

			for(i=0; i<n1; i++){
				L[i] = A[p+i];
			}
			for(j=0; j<n2; j++){ 
				R[j] = A[q+j+1];
			}

			i = 0; j = 0;
			for(k=p; k<=r; k++){
				if( i<n1 && j<n2 ){
					if( L[i].compareTo(R[j])<0 ){
						A[k] = L[i];
						i++;
					}
					else{
						A[k] = R[j];
						j++;
					}
				}
				else if( i<n1 ){
					A[k] = L[i];
					i++;
				}
         		else{ // j<n2
         			A[k] = R[j];
         			j++;
         		}
         	}
         }
     }
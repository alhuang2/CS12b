import java.io.*;
import java.util.Scanner;


class FileReverse{
	public static void main(String[] args) throws IOException{
		if(args.length < 2){
			System.out.println("Usage: FileReverse <input file> <output file>");
			System.exit(1);
		}

		Scanner in = new Scanner(new File(args[0]));
		PrintWriter out = new PrintWriter(new FileWriter(args[1]));

		while( in.hasNextLine() ){
			String line = in.nextLine().trim() + " ";
			String[] token = line.split("\\s+");
			for(int i=0; i<token.length; i++){
				out.println(stringReverse(token[i], token[i].length()-1));
			}
		}

		in.close();
		out.close();
	}

	public static String stringReverse(String s, int n){
		if(n<0) return "";
		else{
			return s.charAt(n) + stringReverse(s, n-1); 
		}
	}
}
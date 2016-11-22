//Kevin Lee
//12b
//Search.java
//Seaches for target words
import java.util.Scanner;
import java.io.*;
class Search{
    public static void main(String[] args) throws IOException{
        //determine the number of lines in the input file
        Scanner in = new Scanner(new File(args[0]));
        int lineCount=0;
        while(in.hasNextLine()){
            in.nextLine();
            lineCount++;
        }
        //allocate a String array of length n and scan the file again storing each n word in the array    
        in = new Scanner(new File(args[0]));
        int numberOfWords=0;
        String[] strList = new String[lineCount];
        while(in.hasNextLine()){
            strList[numberOfWords]=in.nextLine();
            numberOfWords++;
        }
        //array with targets
        String[] target = new String[args.length-1];
        for(int i=0;i<args.length-1;i++){
            target[i]=args[i+1];
        }
        //find the line   
        int[] targetLine = new int[lineCount];
        for(int i=0;i<lineCount;i++){
            targetLine[i]=i+1;
        }
        //calls mergesport and helpers later
        mergeSort(strList,targetLine,0,lineCount-1);
        for(int i=0;i<target.length;i++){
            printLocation(strList,targetLine,target[i]);
        }
        in.close();
    }

    static void mergeSort(String[] word, int[] targetLine, int p, int r){
        int q;
        if(p<r){
            q = (p+r)/2;
            mergeSort(word, targetLine, p, q);
            mergeSort(word, targetLine, q+1, r);
            merge(word,targetLine, p, q, r);
        }
    }

    static void merge(String[] word, int[] targetLine, int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        String[] left = new String[n1];
        String[] right = new String[n2];
        int[] a = new int[n1];
        int[] b = new int[n2];
        int i, j, k;
        for(i=0;i<n1;i++){
            left[i]=word[p+i];
            a[i]=targetLine[p+i];
        }
        for(j=0;j<n2;j++){
            right[j] = word[q+j+1];
            b[j] = targetLine[q+j+1];
        }
        i=0;
        j=0;
        for(k=p; k<=r; k++){
            if(i<n1 && j<n2){
                if( (left[i].compareTo(right[j]))<0){
                    targetLine[k]=a[i];
                    word[k]=left[i];
                    i++;
                }else if( (left[i].compareTo(right[j]))>0){
                    word[k]=right[j];
                    targetLine[k]=b[j];
                    j++;
                }
            }else if( i<n1){
                word[k] = left[i];
                targetLine[k]=a[i];
                i++;
            }else{
                word[k] = right[j];
                targetLine[k]=b[j];
                j++;
            }
        }
    }

    public static int binarySearch(String[] word, int p, int r, String target){
        int q;
        if(p>r){
            return -1;
        }else{
            q = (p+r)/2;
            if((target.compareTo(word[q]))==0){
                return q;
            }else if((target.compareTo(word[q]))<0){
                return binarySearch(word, p, q-1, target);
            }else{
                return binarySearch(word, q+1, r, target);
            }
        }
    }

    public static void printLocation(String[] words, int[] targetLine, String target){
        int line = binarySearch(words,0,words.length-1,target);
        if(line==-1){
            System.out.println(target+" not found.");
        }else{
            System.out.println(words[line]+ " found on line "+ targetLine[line]+".");
        }
    }

}


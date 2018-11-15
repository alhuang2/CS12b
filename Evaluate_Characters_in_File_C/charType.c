/*
Name: Alston Huang
ID: 1471706
Class: CMPS012m
Assignment: lab4
Role: Categorizes each element in a string to either alphabetic, numeric, punctuation, or whitespace
File name: charType.c
*/

#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w){ 
	int i=0, j=0, k=0, l=0, m=0;;
	//j,k,l,m holds the index for a,d,p,w respectively
	while(s[i] != '\0' && i<MAX_STRING_LENGTH){
		if( isalpha(s[i]) )
			//if it is alphabetical, set a[j] = s[i] then increment j
			//same for digit and punctuation
			a[j++] = s[i];
		else if( isdigit(s[i]) )
			d[k++] = s[i];
		else if( ispunct(s[i]) )
			p[l++] = s[i];
		else
			//if it isnt alpha, digit, or punct, then it's a whitespace
			w[m++] = s[i];
		i++;
	}
	a[j] = '\0';
	d[k] = '\0';
	p[l] = '\0';
	w[m] = '\0';
}

int main(int argc, char* argv[]){
	FILE* in;
	FILE* out;
	char* line;
	char* alpha;
	char* digit;
	char* puncts;
	char* whites;

	//only accepts 3 arguments in commandline
	if( argc!=3){
		printf("Usage: %s input-file output-file\n", argv[0]);
		exit(EXIT_FAILURE);
	}

	//open file and set to in.
	if( (in=fopen(argv[1], "r"))==NULL ){
		printf("Unable to read from file %s\n", argv[1]);
		exit(EXIT_FAILURE);
	}

	//open file and set to out.
	if( (out=fopen(argv[2], "w"))==NULL ){
		printf("Unable to read from file %s\n", argv[2]);
		exit(EXIT_FAILURE);
	}

	//allocate memory for each stringholder on heap
	line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	puncts = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	whites = calloc(MAX_STRING_LENGTH+1, sizeof(char));
	//double check if it was allocated
	assert( line!=NULL && alpha!=NULL && digit!=NULL && puncts!=NULL && whites!=NULL);

	int linenumber=0;
	while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
		//organize everything and assign to proper stringholder
		extract_chars(line, alpha, digit, puncts, whites); 

		//create variables to hold string lengths
		int alphalen = (int) strlen(alpha);
		int digitlen = (int) strlen(digit);
		int punctslen = (int) strlen(puncts);
		int whiteslen = (int) strlen(whites);		
		linenumber++;

		fprintf(out, "%s %d %s\n", "line ", linenumber, "contains:");
		//prints out length of string, evaluates length to determine singular or plural, and prints the string.
		fprintf(out, "%d %s %s\n", alphalen, (alphalen==1 ? "alphabetic character:": "alphabetic characters:") , alpha);
		fprintf(out, "%d %s %s\n", digitlen, (digitlen==1 ? "numeric character:" : "numeric characters:"), digit);
		fprintf(out, "%d %s %s\n", punctslen, (punctslen==1 ? "punctuation character:" : "punctuation characters:"), puncts);
		fprintf(out, "%d %s %s\n", whiteslen, (whiteslen==1 ? "whitespace character:" : "whitespace characters:"), whites);

	}

	free(line);
	free(alpha);
	free(digit);
	free(puncts);
	free(whites);

	fclose(in);
	fclose(out);
}


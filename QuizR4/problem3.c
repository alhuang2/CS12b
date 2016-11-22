#include<stdlib.h>
#include<stdio.h>
#include<string.h>

int search(char* S, char c);

int main(void){
	char* string = "abcdefghijklmnopqrstuvwxyz";
	char b = 't';
	printf("%c is at %d\n", b, search(string, b));
}

int search(char* S, char c){
	int count = 0;
	while( S[count] != '\0'){
		if(S[count] == c)
			return count+1;
		count++;
	}
	return -1;
} 
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int getValue(int a, int b, int n);
void displayOctal(int n);

int main(void){
	printf("%d", getValue(3, 13, 5));
}

int getValue(int a, int b, int n){
	int x, c;
	printf("arrive: a = %d b = %d\n", a, b);
	c = (a+b)/2;
	if( c*c <= n ){
		x =c;
	}
	else{
		x = getValue(a, c-1, n);
	}

	printf("depart: a = %d b = %d\n", a, b);
	return x;
}

void displayOctal(int n){
	if(n>0){
		if(n/8 > 0){
			displayOctal(n/8);
		}
		printf("%d\n", n%8);
	}
}
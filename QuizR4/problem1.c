#include<stdio.h>
#include<stdlib.h>
int time;
int a(int x){
	int i;
	i = x*x;
	time++;
	return(i);
}
int c(int z){
	int k;
	k = a(z)+b(z);
	time++;
	return(k);
}
int b(int y){
	int j;
	j = y + a(y); // functions are evaluated from left to right
	time++;
	return(j);
}
int main(void){
	int q, r;
	time = 0;
	q = b(5);
	r = c(2);
	printf("q=%d, r=%d, time=%d\n", q, r, time);
	return(EXIT_SUCCESS);
}

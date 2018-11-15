#include<stdlib.h>
#include<stdio.h>

int main(void){
	int x = 10;
	int *p = &x;
	int *t;
	t = &x;
	printf("%d %d", *p, *t);
	p = x;
	printf( "%d", p);
}
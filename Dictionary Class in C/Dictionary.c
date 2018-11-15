// //Alston Huang
// //1471706
// //CMPS012b
// //Structure and functions for DictionaryClient
// //Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

//private types

//NodeObj
typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

//Node
typedef NodeObj* Node;

//newNode()
//constructor of the Node type
Node newNode(char* k, char* v){
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return(N);
}

//freeNode();
//destructor for the Node type
void freeNode(Node* pN){
	if( pN != NULL && *pN != NULL ){
		free(*pN);
		*pN = NULL;
	}
}

//DictionaryObj
typedef struct DictionaryObj{
	Node head;
	Node tail;
	int numItems;
} DictionaryObj;

//constructor for Dictioary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D != NULL );
	D->head = NULL;
	D->tail = NULL;
	D->numItems = 0;
	return(D);
}

//destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if( pD != NULL && *pD != NULL){
		if( !isEmpty(*pD) )
			makeEmpty(*pD);
		free(*pD);
		*pD = NULL;
	}
}

//Private Function

//findKey()
Node findKey(Node N, char* k){

	if( N->next == NULL && N->key == k )
		return N;
	
	while( N != NULL ){
		if( N->next->key != k )
			N = N->next;
		else 
			return N;
	}
	return NULL;
}


//ADT Operations

//isEmpty() function
// 1 = true, 0 = false
int isEmpty(Dictionary D){
	if( D == NULL ){
		fprintf(stderr, "Dictionary Error: calling isEmpty() on null Dictionary reference\n");
		exit(EXIT_FAILURE);
	}

	//returns false
	if( D->numItems > 0 )
		return 0;

	//only evaluated if the if statement above fails.
	return 1;
}

//size() returns size of dictionary
int size(Dictionary D){
	return D->numItems;
}


//returns the value v such that (k, v) is in D, or returns NULL if not
char* lookup(Dictionary D, char* k){
	Node N = D->head;

	if( N == NULL ){
		fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary\n");
		exit(EXIT_FAILURE);
	}

	while( N != NULL ){
		if( strcmp(N->key, k) == 0 ){
			return N->value;
		}
		N = N->next;
	}
	
	return NULL;
}

//insert()
//pre condition: lookup(D, k) == NULL
void insert(Dictionary D, char* k, char* v){

	Node temp = newNode(k, v);

	//special case for empty dictionary
	if( D->numItems == 0 ){
		D->head = D->tail = temp;
	}

	else if( lookup(D, k) != NULL ){
		fprintf(stderr, "Dictionary Error: calling insert() on Duplicate Key\n");
		exit(EXIT_FAILURE);
	}

	else{
		D->tail->next = temp;
		D->tail = temp;
	}

	D->numItems++;

}

void delete(Dictionary D, char* k){
	if( lookup(D, k) == NULL ){
		fprintf(stderr, "Dictionary Error: calling delete() on Non-Existent Key\n");
		exit(EXIT_FAILURE);
	}

	Node N = D->head;

	//special case one element in dictionary
	if( D->numItems <= 1 ){
		D->head = NULL;
	}

	//special case if first node in dictionary contains the key
	else if( strcmp(N->key, k) == 0 ){
		D->head = N->next;
	}

	else{
		while( strcmp(N->next->key, k) != 0 ){
			N = N->next;
		}
		N->next = N->next->next;
	}
	
	D->numItems--;
}


void makeEmpty(Dictionary D){
	D->head = NULL;
	D->tail = NULL;
	D->numItems = 0;
}

void printDictionary(FILE* out, Dictionary D){

	if( D == NULL ){
		fprintf(stderr, "Dictionary Error: calling printDictionary() on NULL Dictionary\n");
	}

	for( Node N = D->head; N != NULL; N = N->next ){
		fprintf(out, "%s %s \n", N->key, N->value );
	}
}

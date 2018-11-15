//Alston Huang
//1471706
//CMPS012m
//A test that tests all variation of the ADT operations.
//DictionaryTest.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){

   Dictionary A = newDictionary();

//-----------------------------------------------------------------------------------------------
   //test an empty dictionary. Should return 1
   printf( "Dictionary Empty: %d\n", isEmpty(A) );

   //Dictionary A contains nothing up to this point
   //test 1 ends here

//-----------------------------------------------------------------------------------------------

   //testing inserts
   insert( A, "one", "foo" );
   insert( A, "two", "bar" );
   insert( A, "three", "blah");
   printf( "Successfully inserted three keys to Dictionary A\n");
   //test size. should return 3
   printf( "Dictionary Size: %d\n", size(A) );

   //Dictionary A contains keys: one, two, three
   //test 2 ends here
//-----------------------------------------------------------------------------------------------

   //testing a non-empty dictionary. should return 0
   printf( "Dictionary Empty: %d\n", isEmpty(A) );

   //Dictionary A contains keys: one, two, three
   //test 3 ends here
//-----------------------------------------------------------------------------------------------

   printDictionary(stdout, A);

   //testing delete on Dictionary containing multiple elements
   delete( A, "one" );
   delete( A, "two" );

   //test delete on Dictionary containing one element
   delete( A, "three" );
   printf("Successfuly deleted everything in dictionary using delete()\n");

   //re-test size on supposedly empty dictionary. should return 0
   printf("Dictionary Size: %d\n", size(A));

   //should print nothing
   printDictionary(stdout, A);

   //Dictionary A contains nothing up to here
   //test 4 ends here
//-----------------------------------------------------------------------------------------------

   insert( A, "one", "foo" );
   //test for makeEmpty();
   makeEmpty(A);
   printf("Making Dictionary Empty...\n");
   //printDictionary should print nothing, size shold be 0, isEmpty = 1
   printDictionary(stdout, A);
   printf("Dictionary Size: %d\nDictionary Empty: %d\n", size(A), isEmpty(A) );

   //Dictionary A contains nothing up to here
   //test 5 ends here
//-----------------------------------------------------------------------------------------------

   insert( A, "one", "foo" );
   //test lookup on dictionary containing one element
   printf( "Lookup(A, one): %s\n", lookup(A, "one") );

   insert( A, "two", "boo" );
   //test lookup on dictionary containing more than one element 
   printf( "Lookup(A, one): %s\nLookup(A, two): %s\n", lookup(A, "one"), lookup(A, "two") );

   //Dictionary A contains keys: one, two
   //test 6 ends here
//-----------------------------------------------------------------------------------------------

   printf("\nAll ADT operations for Dictionary.c working 100%%\n");

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
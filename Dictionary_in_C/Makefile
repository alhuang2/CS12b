#------------------------------------------------------------------------------
# Alston Huang
# 1471706
# CMPS012m
# Makefile for DictionaryClient and DictionaryTest
# DictionaryTest.c
#------------------------------------------------------------------------------

ADT_NAME  = Dictionary
SOURCES   = $(ADT_NAME).c $(ADT_NAME)Client.c $(ADT_NAME)Test.c
OBJECTS   = $(ADT_NAME).o $(ADT_NAME)Client.o 
HEADERS   = $(ADT_NAME).h
EXEBIN    = $(ADT_NAME)Client 
FLAGS     = -std=c99 -Wall

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS) $(HEADERS)
	gcc -o $(EXEBIN) $(OBJECTS)

DictionaryTest : DictionaryTest.o
	gcc -o DictionaryTest DictionaryTest.o Dictionary.o

$(OBJECTS) : $(SOURCES) $(HEADERS)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS) DictionaryTest.o DictionaryTest

check:
	valgrind --leak-check=full $(EXEBIN) 
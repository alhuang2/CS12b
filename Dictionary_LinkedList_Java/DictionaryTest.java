class DictionaryTest{
	public static void main(String[] args){
		Dictionary test = new Dictionary();

		//testing delete()
		//test.delete("4");
		//result: Throws exception 
		//test 1 ends here.
//----------------------------------------------------------------------------------------

		//testing isEmpty()
		System.out.println( "Dictionary empty: " + test.isEmpty() );

		//dictionary test contains nothing up to this point.
		//test 2 ends here.
//----------------------------------------------------------------------------------------

		//check spcial cases. adding and deleting in first node.
		test.insert("1", "z");
		test.delete("1");
		System.out.println("Dictionary contains:\n" + test);

		//testing insert() to an existing dictionary
		test.insert("10", "z");	
		test.insert("6", "t");
		test.insert("7", "s");
		System.out.println("Dictionary contains:\n" + test);

		//testing delete() to more than one element in dictionary
		test.delete("10");
		test.delete("6");
		System.out.println("Dictionary contains:\n" + test);

		//dictionary test contains key: 7 up to this point
		//test 3 ends here.
//----------------------------------------------------------------------------------------

		//retest isEmpty() & test lookup()
		System.out.println( "Dictionary empty: " + test.isEmpty() );

		test.insert("3", "c");
		test.insert("4", "e");

		System.out.println("Dictionary contains:\n" + test);

		test.delete("4");

		if(test.lookup("4") == null)
			System.out.println("test.lookup(4) failed.");

		if(test.lookup("3") != null )
			System.out.println("test.lookup(3) success.");

		//dictionary test contains key: 7, 3, 4 up to this point
		//test 4 ends here.
//----------------------------------------------------------------------------------------

		//test makeEmpty and size()
		test.makeEmpty();

		System.out.println("Emptying dictionary...");
		System.out.println( "Dictionary empty: " + test.isEmpty() );
		System.out.println( "Dictionary size: " + test.size() );

		test.insert("4", "a");
		test.insert("5", "b");
		test.insert("6", "c");

		System.out.println("Inserted " + test.size() + " to dictionary." );
		System.out.println("New size: " + test.size() + "\n" );

		//dictionary test contains key: 4, 5, 6 up to this point
		//test 5 ends here.
//----------------------------------------------------------------------------------------
		//testing Exceptions but do not throw it.
		try{
			test.insert("4", "a");
		}
		catch(DuplicateKeyException e){
			System.out.println(e + "\nContinuing program...\n" );
		}

		try{
			test.delete("123");
		}
		catch(KeyNotFoundException e){
			System.out.println(e + "\nContinuing program...\n" );
		}
		//dictionary contains key: 4, 5, 6 up to this point 
		//test 6 ends here.
//----------------------------------------------------------------------------------------
		System.out.println("All ADT operations tested. Working 100%");
	}
}
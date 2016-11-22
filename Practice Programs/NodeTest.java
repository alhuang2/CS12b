//file: NodeTest.java

public class NodeTest{
	public static void main(String[] args){
		Node H = new Node(9);
		H.next = new Node(7);
		H.next.next = new Node(5);

		//part (a) refers to this point in the code

		for(Node N=H; N != null; N=N.next)
			System.out.print(N.item+" ");
		System.out.println();
		//part (b) refers to this point in the code
		// It will print out:9 7 5
		// 
		//end 

		//part (c) refers to this point in the code
		//your code goes here

		Node temp = H.next.next;
		H.next.next = new Node(4);
		H.next.next.next = temp;

		//System.out.println(H.next.next.item); tests for part (c)

		printForward(H);
		printBackward(H);
	}


	// 2a. Write a recursive function called printForward() that prints out
	// the items from head to tail.
	static void printForward(Node H){
		if( H == null ){
			System.out.println();
		}
		else{
			System.out.print(H.item + " ");
			printForward(H.next);
		}
	}

	// 2b. Write a recursive function called printBackward() that prints out
	// the items from tail to head.
	static void printBackward(Node H){
		if( H == null ){
			System.out.println();
		}
		else{
			printBackward(H.next);
			System.out.print(H.item + " ");
		}
	}
}
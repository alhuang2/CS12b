//Alston Huang
//1471706
//CMPS012b
//
//Queue.java

public class Queue implements QueueInterface{
	private class Node{

		//fields fosr Node
		Object item;
		Node next;

		//constructor for Node
		Node(Object item){
			this.item = item;
			next = null;
		}
	}

	//fields for Queue
	private Node head;
	private int numItems;

	//constructor for Queue
	public Queue(){
		head = null;
		numItems = 0;
	}


	//ADT operations

	//isEmpty()
	public boolean isEmpty(){
		return numItems == 0;
	}

	public int length(){
		return numItems;
	}

	//enqueue()
	public void enqueue(Object newItem){
		if( head == null ){
			head = new Node(newItem); 
		}
		else{
			Node N = head;
			while( N.next != null ){
				N = N.next;
			} 
			N.next = new Node(newItem);
		}
		numItems++;
	}


	//dequeue()
	public Object dequeue() throws QueueEmptyException{
		if( head == null ){
			throw new QueueEmptyException(
				"dequeue() error: Cannot dequeue an empty queue.");
		}
		else{
			Node N = head;
			head = N.next;
			numItems--;
			return N.item;
		}
	}

	//peek()
	public Object peek() throws QueueEmptyException{
		if( head == null ){
			throw new QueueEmptyException(
				"peek() error: Cannot peek an empty queue.");
		}
		else{
			return head.item;
		}
	}

	//dequeueAll()
	public void dequeueAll() throws QueueEmptyException{
		if( head == null ){
			throw new QueueEmptyException(
				"dequeueAll() error: Cannot dequeueAll an empty queue.");
		}
		else{
			head = null;
			numItems = 0;
		}
	}

	//toString()
	public String toString(){
		String s = "";
		Node N = head;
		while( N != null ){
			s = s + N.item + " ";
			N = N.next;
		}
		return s;
	}
}
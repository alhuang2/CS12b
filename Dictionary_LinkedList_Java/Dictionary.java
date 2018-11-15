public class Dictionary implements DictionaryInterface{

	//private inner Node class
	private class Node{
		String value;
		String key;
		Node next;

		Node(String k, String v){
			key = k;
			value = v;
			next = null;
		}
	}

	//Fields for Dictionary class
	private Node head;
	private int numItems;

	//constructor for Dictionary class
	public Dictionary(){
		head = null;
		numItems = 0;
	}

	//private Helper function
	private Node findKey(String k){
		Node N = head;
		while( N != null ){
			if( N.key != k )
				N = N.next;
			else
				return N;
		}
		return null;
	}

	//ADT operations

	//isEmpty()
	public boolean isEmpty(){
		return (numItems == 0);
	}

	//size();
	public int size(){
		return numItems;
	}

	//lookup(String key);
	public String lookup(String k){
		Node N = head;
		while( N != null ){
			if( N.key.equals(k) )
				return N.value;
			else
				N = N.next;
		}
		return null;
	}

	//insert(String k, String v) inserts a new node at the end
	//pre-condition: Dictionary does not currently contain argument key
	public void insert(String k, String v) throws DuplicateKeyException{
		Node N = head;
		Node temp = new Node(k, v);

		//determine if there isn't already a key in this node
		if( lookup(k) != null ){
			throw new DuplicateKeyException("Cannot insert duplicate keys.");
		}

		//make special case for empty dictionary
		if( head == null ){
			head = temp;
		}

		else{
			//run to the node before the last one.
			while( N != null ){
				if( N.next == null )
					break;
				N = N.next;
			}

			N.next = temp;
		}

		numItems++;
	}

	//delete(String key)
	//pre-conditon: Dictionary must contain the argument key
	public void delete(String k) throws KeyNotFoundException{
		Node N = head;

		//check if such key exists, if not throw an exception
		if( lookup(k) == null )
			throw new KeyNotFoundException("Cannot delete non-existent key.");

		else {
			//special case for one node. deletes only if there is one node
			if( numItems <= 1 ){
				head = head.next;
				N.next = null;
			}
			else if( N.key.equals(k) ){
				head = N.next;
			}
			//else, run to the node before key node.
			else{
				while( !N.next.key.equals(k) )
					N = N.next;
				N.next = N.next.next;
			}
		}
		numItems--;
	}

	//makeEmpty()
	public void makeEmpty(){
		head = null;
		numItems = 0;
	}

	//toString()
	public String toString(){
		String string = "";
		Node N = head;

		while( N != null ){
			string = string + N.key + " " + N.value + "\n";
			N = N.next;
		}
		return string;
	}
}
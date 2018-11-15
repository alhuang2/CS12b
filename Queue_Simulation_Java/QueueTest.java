public class QueueTest{
	public static void main(String[] arg){
		Queue test = new Queue();

		test.enqueue(5);
		test.enqueue(6);
		test.dequeue();
		System.out.println("test contains: " + test);

		test.dequeue();
		System.out.println("test contains: " + test);

		//should report true
		System.out.println("test empty: " + test.isEmpty() );
		test.enqueue(1);

		//should report false
		System.out.println("Added 1 to test. Retesting test Empty: " + test.isEmpty() );
		
		//should report 1
		System.out.println("Size/Length of test: " + test.length() );

		System.out.println("Enqueueing more...");
		test.enqueue(2);
		test.enqueue(3);

		//should report 1
		System.out.println("Peek of test: " + test.peek() );

		//test should print nothing
		test.dequeueAll();
		System.out.println("Dequeueing All. test contains: "  + test );
	}
}
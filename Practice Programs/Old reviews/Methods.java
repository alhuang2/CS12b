public class Methods{
	public static void main(String[] args){
		IntegerList list = new IntegerList();

		list.add(1, 1);
		list.add(2, 2);
		list.add(3, 3);
		list.add(4, 4);
		list.add(5, 5);
		list.add(6, 6);
		list.add(7, 7);
		list.add(8, 8);
		list.add(9, 9);
		list.add(10, 10);
		list.add(11, 11);
		list.add(12, 12); 
		list.add(13, 13);

		//swap(list, 2, 4);

		// for(int i=1; i < list.size()+1; i++){
		// 	System.out.println(list.get(i));
		// }

		//System.out.println(list.toString());

		// int t = search(list, 5);
		// System.out.println(t);

		// reverse(list, 1, list.size());
		// for(int i=1; i<=list.size(); i++){
		// 	System.out.println(list.get(i));
		// }

		System.out.println(max(list));
	}

	static void swap(IntegerList L, int i, int j){
		int x = L.get(i);
		int y = L.get(j);

		L.remove(j);
		L.add(j, x);

		L.remove(i);
		L.add(i, y);

	}

	static int search(IntegerList L, int x){
		for(int i = 1; i <= L.size(); i++){
			int itemNumber = L.get(i);
			if(itemNumber == x)
				return i;
		}

		return 0;
	}

	static void reverse(IntegerList L, int i, int length){
		if(i > length)
			return;
		else {
			swap(L, i, length);
			reverse(L, i+1, length-1);
		}
	}

	static int max(IntegerList L){
		int max = L.get(1);
		for(int i=1; i < L.size(); i++ ){
			if(max > L.get(i+1))
				max = L.get(i);
			else
				max = L.get(i+1);
		}
		return max;
	}


}
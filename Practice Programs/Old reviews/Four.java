public class Four extends IntegerList{
	public static void main(String[] args){
		// Four four = new Four();
		Four list = new Four();

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

		System.out.println(list.tooString());
	}

	public String tooString(){

      String str = "";
      for(int i = 1; i <= size(); i++){
         if( i == 1 )
            str = str + get(1) + " ";
         else if(i % 11 == 0 )
            str = str + "\n" + get(i) + " ";
         else 
            str = str + get(i) + " ";
      }
      return str;
   }

}
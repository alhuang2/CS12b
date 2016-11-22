class SumOfNonNeg{
	public static void main(String[] args){
		//System.out.println(sum(5,10));
		System.out.println(sum1(5,10));
		/*int[] A = {3,4,5};
		System.out.println(A.length);*/
	}

	static int sum(int n, int m){
		if(m<=n) return n;
		else if(n>m) return 0;
		else return m+sum(n, m-1);
	}

	static int sum1(int n, int m){
		if()
	}
}
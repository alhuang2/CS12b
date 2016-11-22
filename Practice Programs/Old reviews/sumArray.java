class sumArray{
	public static void main(String[] args){
		int[] B = {3,4,5};
		System.out.println(sum(B, 2));
	}

	static int sum(int[] A, int n){
		if(n == 0) return A[0];
		else return A[n] + sum(A, n-1);
	}
}
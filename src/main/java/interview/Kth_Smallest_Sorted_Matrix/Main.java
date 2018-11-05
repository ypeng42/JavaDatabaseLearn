package interview.Kth_Smallest_Sorted_Matrix;

public class Main {
	public static void main(String[] args) {
		int[][] input = {{1,3,5,7,9},{2,4,6,8,10},{11,13,15,17,19},{12,14,16,18,20},{21,22,23,24,25}};

		FailedSolution s = new FailedSolution();
		System.out.println(s.kthSmallest(input, 24));
	}
}

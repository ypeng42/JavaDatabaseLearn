package interview.minSizeSubarray;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] i = {2, 3, 2, 4, 5};
		s.minSubArrayLen(7, i);

		NlogNSolution n = new NlogNSolution();
//		System.out.println(n.minSubArrayLen(7, new int[]{2,3,1,2,4,3}));
		System.out.println(n.minSubArrayLen(6, new int[]{10, 2, 3}));
		System.out.println(n.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
	}
}

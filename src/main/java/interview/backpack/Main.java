package interview.backpack;

public class Main {
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] i = {2, 3, 4, 5};
		int[] v = {50, 1, 20, 8};
//		System.out.println(s.backPack(6, i));
		System.out.println(s.backPackIII(i, v, 10));
	}
}

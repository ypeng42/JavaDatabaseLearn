package interview.permutation;

public class Main {
	public static void main(String[] args) {
//		Solution s = new Solution();
//		int[] input = {1,2,3};
//		List<List<Integer>> rst = s.permute(input);
//		System.out.println(rst);

		SolutionLevelTwo s2 = new SolutionLevelTwo();
		s2.permuteUnique(new int[]{1, 1, 2});
	}
}

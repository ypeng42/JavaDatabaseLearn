package interview.BSTLongestConsecutive;

import interview.TreeHelper;
import interview.TreeNode;

public class Main {
	public static void main(String[] args) {
		AnotherSolution s = new AnotherSolution();

		TreeNode root = TreeHelper.makeTree(new int[]{1,2,3,4,5});

		System.out.println(s.longestConsecutive(root));

		int[][] a = {};
		System.out.println(a.length);
	}
}

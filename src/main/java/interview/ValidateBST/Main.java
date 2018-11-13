package interview.ValidateBST;

import interview.TreeNode;

public class Main {
	public static void main(String[] args) {
		TreeNode node = new TreeNode(123);
		Solution s = new Solution();
		System.out.println(s.isValidBST(node));
	}
}

package interview.ValidateBST;

import interview.TreeNode;

public class Solution {
	public boolean isValidBST(TreeNode root) {
		return helper(root, null, null);
	}

	private boolean helper(TreeNode node, Integer min, Integer max) {
		if (node == null) return true;

		if ((max != null && node.val >= max) || (min != null && node.val <= min)) return false;

		return helper(node.right, node.val, max) && helper(node.left, min, node.val);
	}
}

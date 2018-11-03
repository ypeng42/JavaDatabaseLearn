package interview.DiameterBST;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 *
 */
class FailedSolution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

//	public int diameterOfBinaryTree(TreeNode root) {
//
//	}
//
//	private int[] getMaxLen(TreeNode node) {
//		int left = 0;
//		int right = 0;
//		int[] info = new int[2];
//
//		if (node.left != null) {
//			left = 1 + getMaxLen(node.left);
//		}
//
//		if (node.right != null) {
//			right = 1 + getMaxLen(node.right);
//		}
//
//		info[0] = left + right;
//		info[1] = Math.max(left, right);
//
//		return info;
//	}
}

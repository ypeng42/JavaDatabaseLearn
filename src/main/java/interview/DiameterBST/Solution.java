package interview.DiameterBST;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}

	public int diameterOfBinaryTree(TreeNode root) {
		return diameter(root);
	}

	private int diameter(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int leftHeight = height(node.left);
		int rightHeight = height(node.right);

		/**
		 *      1
		 *     / \
		 *    2   3
		 *       / \
		 *      4   5
		 *     /     \
		 *    6       7
		 *   /         \
		 *  8           9
		 *
		 * from 8 to 9 is the longest.
		 * longest d is always 2 nodes on the lowest level of the left & right side of a tree.
		 * that tree is either root or subtree of root, that'why we are comparing 3 things at the end.
		 */
		int leftDiameter = diameter(node.left);
		int rightDiameter = diameter(node.right);

		return Math.max(leftHeight + rightHeight, Math.max(leftDiameter, rightDiameter));
	}

	/**
	 * 7
	 *  \
	 *   8
	 *
	 * has a height of 2 since there are 2 nodes
	 */
	private int height(TreeNode node) {
		if (node == null) return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}
}

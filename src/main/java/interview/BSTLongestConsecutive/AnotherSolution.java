package interview.BSTLongestConsecutive;

import interview.TreeNode;

/**
 * In top down recursion, it's not necessary to pass a tracking object.
 * It's personal taste, but this code is faster.
 */
public class AnotherSolution {


	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;

		return search(root, 1);
	}

	public int search(TreeNode currNode, int currLen) {
		int left = currLen, right = currLen;

		if (currNode.left != null) {
			if (currNode.val == currNode.left.val - 1) {
				left = search(currNode.left, currLen + 1);
			} else {
				left = search(currNode.left, 1);
			}
		}

		if (currNode.right != null) {
			if (currNode.val == currNode.right.val - 1) {
				right = search(currNode.right, currLen + 1);
			} else {
				right = search(currNode.right, 1);
			}
		}

		return Math.max(currLen, Math.max(left, right));
	}
}

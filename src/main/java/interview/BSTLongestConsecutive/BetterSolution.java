package interview.BSTLongestConsecutive;

import interview.TreeNode;

/**
 * in C++, address of int can be passed around, which can
 * enable top down recursion instead of bottom up.
 *
 * In Java, need a trick to do this. Sometimes it's
 * hard to think about top down recursion, but it will make
 * code much more cleaner, in this case, since we don't need to
 * track "cont" anymore.
 *
 * As a human, I track top to bottom, bottom to up WILL be more messy.
 *
 */
public class BetterSolution {
	class Result {
		int rst;
		public Result(int rst) {
			this.rst = rst;
		}
	}

	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;
		Result rst = new Result(1);
		search(root, 1, rst);

		return rst.rst;
	}

	public void search(TreeNode currNode, int currLen, Result rst) {
		rst.rst = Math.max(currLen, rst.rst);

		if (currNode.left != null) {
			if (currNode.val == currNode.left.val - 1) {
				search(currNode.left, currLen + 1, rst);
			} else {
				search(currNode.left, 1, rst);
			}
		}

		if (currNode.right != null) {
			if (currNode.val == currNode.right.val - 1) {
				search(currNode.right, currLen + 1, rst);
			} else {
				search(currNode.right, 1, rst);
			}
		}
	}
}

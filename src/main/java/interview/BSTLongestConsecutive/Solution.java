package interview.BSTLongestConsecutive;

import interview.TreeNode;

class Solution {
	public int longestConsecutive(TreeNode root) {
		if (root == null) return 0;

		return findMaxStrik(root).max + 1;
	}

	class Pair {
		int max;
		boolean cont;
		public Pair(int max, boolean cont) {
			this.max = max;
			this.cont = cont;
		}
	}

	private Pair findMaxStrik(TreeNode node) {
		if (node == null) {
			return new Pair(0, true);
		}

		Pair leftMax = findMaxStrik(node.left);
		Pair rightMax = findMaxStrik(node.right);

		if (node.left != null) {
			if (node.val - node.left.val == -1) {
				if (leftMax.cont) {
					leftMax.max = leftMax.max + 1;
				} else {
					if (leftMax.max <= 1) {
						leftMax = new Pair(1, true);
					}
				}
			} else {
				leftMax.cont = false;
			}
		}

		if (rightMax.cont && node.right != null) {
			if (node.val - node.right.val == -1) {
				if (rightMax.cont) {
					rightMax.max = rightMax.max + 1;
				} else {
					if (rightMax.max <= 1) {
						rightMax = new Pair(1, true);
					}
				}
			} else {
				rightMax.cont = false;
			}
		}

		return (leftMax.max > rightMax.max) ? leftMax : rightMax;
	}

	// my thought about this part miss the fact that a single node should not be counted as dis-cont
	// the root null check is unnecessary, should inline this during first try
	private boolean isConsecutive(TreeNode a, TreeNode b) {
		if (a == null || b == null) {
			return true;
		}

		return a.val - b.val == -1;
	}
}

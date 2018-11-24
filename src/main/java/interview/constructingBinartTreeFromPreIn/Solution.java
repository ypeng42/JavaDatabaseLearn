package interview.constructingBinartTreeFromPreIn;

import interview.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
	/*
		from just a pre-order array we can't construct the tree since root, left, right.
		we know the root but not the size of the left/right tree
		by giving in-order array, we can get the subtree size from the array
	 */
	Map<Integer, Integer> map = new HashMap<>();
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || inorder == null || preorder.length != inorder.length) {
			return null;
		}
		for (int i = 0; i < inorder.length; i++) {
			map.put(inorder[i], i);
		}
		return dfs(preorder, 0, preorder.length - 1, 0);
	}

	public TreeNode dfs(int[] preorder, int preStart, int preEnd, int inStart) {
		if (preStart > preEnd) {
			return null;
		}

		TreeNode root = new TreeNode(preorder[preStart]);
		int mid = map.get(preorder[preStart]);

		if (mid < 0) {
			return null;
		}

		int leftTreeSize = mid - inStart; // inorder is left root right

		// for left subtree, determine the end by using size of left subtree
		// inStart is the first element in array of the subtree, it's used to determine left tree size
		root.left = dfs(preorder, preStart + 1, preStart + leftTreeSize, inStart);

		// right tree: preStart + leftTreeSize + 1 is the root of the right tree
		root.right = dfs(preorder, preStart + leftTreeSize + 1, preEnd, mid + 1);

		return root;
	}
}

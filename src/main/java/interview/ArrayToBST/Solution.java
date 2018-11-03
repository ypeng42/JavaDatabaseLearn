package interview.ArrayToBST;


public class Solution {
	public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  	}

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null) {
			return null;
		}

		return buildSubTree(nums, 0, nums.length - 1);
	}

	private TreeNode buildSubTree(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}

		if (start == end) {
			return new TreeNode(nums[start]);
		}

		int midIndex = (start + end) /2;

		TreeNode result = new TreeNode(nums[midIndex]);
		result.left = buildSubTree(nums, start, midIndex - 1);
		result.right = buildSubTree(nums, midIndex + 1, end);

		return result;
	}
}

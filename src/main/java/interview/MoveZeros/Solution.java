package interview.MoveZeros;

public class Solution {
	public void moveZeroes(int[] nums) {
		if (nums == null || nums.length == 0) return;

		int nextNonZeroPos = 0;

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				nums[nextNonZeroPos] = nums[i];
				nextNonZeroPos++;
			}
		}

		for (int i = nextNonZeroPos; i < nums.length; i++) {
			nums[i] = 0;
		}
	}
}

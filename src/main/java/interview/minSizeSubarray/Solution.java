package interview.minSizeSubarray;

public class Solution {
	public int minSubArrayLen(int s, int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int start, end, sum;
		start = end = sum = 0;
		int min = Integer.MAX_VALUE;
		while (end < nums.length) {
			while (sum < s && end < nums.length) {
				sum += nums[end];
				end++;
			}
			// move start and log any possible min
			while (sum >= s && start >= 0) {
				min = Math.min(min, end - start);
				sum -= nums[start];
				start++;
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
}

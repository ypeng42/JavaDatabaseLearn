package interview.MaxSizeSubArrSumToK;

import java.util.HashMap;
import java.util.Map;

class Solution {
	/**
	 * running sum ex:
	 *
	 * k = 3
	 * arr:  3, 2, 1, -1, 5, -2
	 * sum:  3, 5, 6, 5, 10, 8
	 *          |------------|
	 *
	 *          8 - 5 is the sum of the sub array [1, -1, 5, -2]
	 */
	public int maxSubArrayLen(int[] nums, int k) {
		Map<Integer, Integer> pos = new HashMap<>();
		int max = 0;
		int sum = 0;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];

			/*
			 * this check is necessary. ex. for -1, 1, 5, -2, 3. target 3
			 * Without the check, we will return 2 -> [5, -2]
			 * this algorithm doesn't recognize the first 0 is before we start iterating
			 *
			 * Another way to do the check is do map.put(0, -1)
			 */
			if (sum == k) {
				max = i + 1;
			}

			// the rest is for subarray not start from beginning
			int diff = sum - k;

			if (pos.containsKey(diff)) {
				max = Math.max(max, i - pos.get(diff));
			}

			// no need to update if it already exists
			// ex. 3, -1, 1, -1, 1
			// index 0 definitely has longer length than index 4
			pos.putIfAbsent(sum, i);
		}

		return max;
	}
}
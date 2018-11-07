package interview.LongestIncreasingSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * The key to understand is
 * 10 7 2 5 8
 *
 * list is 2 5 8
 *
 * the next item is 3, it will replace 5, the strike length keeps the same,
 * but by replacing 5, we lower the threshold for the strike to continue.
 * (instead of > 5, > 3 is enough)
 */
class Solution {
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (list.size() == 0 || nums[i] > list.get(list.size() - 1)) {
				list.add(nums[i]);
			} else {
				int index = binarySearch(list, nums[i]);
				list.set(index, nums[i]);
			}
		}
		return list.size();
	}

	public int binarySearch(List<Integer> list, int target) {
		int start = 0;
		int end = list.size() - 1;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (list.get(mid) == target) {
				return mid;
			} else if (list.get(mid) < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return list.get(start) >= target ? start : end;
	}
}

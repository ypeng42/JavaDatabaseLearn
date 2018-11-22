package interview.minSizeSubarray;

public class NlogNSolution {
	public int minSubArrayLen(int s, int[] nums) {
		int[] sums = new int[nums.length];
		int sum = 0;
		Integer minLen = null;

		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			sums[i] = sum;
		}

		for (int j = nums.length - 1; j >= 0; j--) {
			if (sums[j] - s < 0) continue;

			int left = binarySearch(sums,sums[j] - s) - 1;

			if (minLen == null) {
				minLen = j - left;
			} else {
				minLen = Math.min(minLen, j - left);
			}
		}

		return minLen == null ? 0 : minLen;
	}

	/**
	 * return the index of the position I will insert target to arr
	 */
	public int binarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] == target) { //let's be crystal clear here what to do for equal
				return mid + 1;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}
}

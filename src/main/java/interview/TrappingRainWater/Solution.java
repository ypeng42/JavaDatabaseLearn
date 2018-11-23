package interview.TrappingRainWater;

class Solution {
	public int trap(int[] height) {
		if (height == null || height.length == 0) return 0;

		int n = height.length;

		int[] leftMax = new int[n];
		int[] rightMax = new int[n];

		rightMax[n - 1] = height[n - 1];
		leftMax[0] = height[0];

		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(height[i], rightMax[i + 1]);
		}

		for (int j = 1; j < n; j++) {
			leftMax[j] = Math.max(height[j], leftMax[j - 1]);
		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			int bottom = Math.min(leftMax[i], rightMax[i]);
			sum += height[i] < bottom  ? bottom - height[i] : 0;
		}

		return sum;
	}
}
package interview.LongestConsecutiveSequence;

import java.util.HashMap;
import java.util.Map;

class Solution {
	public int longestConsecutive(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		Map<Integer, Integer> visited = new HashMap<>();
		UnionFind union = new UnionFind(nums.length);

		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			if (visited.containsKey(num)) {
				continue;
			}

			visited.putIfAbsent(num, i);

			if (visited.containsKey(num + 1)) {
				union.union(i, visited.get(num + 1));
			}

			if (visited.containsKey(num - 1)) {
				union.union(i, visited.get(num - 1));
			}
		}

		return union.maxSize();
	}

	class UnionFind {
		int[] parent;
		int[] size;

		public UnionFind(int n) {
			parent = new int[n];
			size = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				size[i] = 1;
			}
		}

		public void union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);

			if (rootA != rootB) {
				parent[rootA] = rootB;
				size[rootB] += size[rootA];
			}
		}

		public int find(int x) {
			if (x == parent[x]) return x;

			int root = find(parent[x]);
			parent[x] = root;

			return root;
		}

		public int maxSize() {
			int max = 0;
			for (int i = 0; i < size.length; i++) {
				max = Math.max(max, size[i]);
			}

			return max;
		}
	}
}
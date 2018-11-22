package interview.IntersactionTwoArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1 == null || nums2 == null) {
			return new int[0];
		}
		final List<Integer> result = new ArrayList<>();
		final Map<Integer, Integer> map = new HashMap<>();
		// fill nums1 in to map
		for (int num : nums1) {
			if (!map.containsKey(num)) {
				map.put(num, 0);
			}
			map.put(num, map.get(num) + 1);
		}
		// check nums2 against the map
		for (int num : nums2) {
			if (map.containsKey(num) && map.get(num) > 0) {
				result.add(num);
				map.put(num, map.get(num) - 1);
			}
		}
		int[] rst = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			rst[i] = result.get(i);
		}
		return rst;
	}
}

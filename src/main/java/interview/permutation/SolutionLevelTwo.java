package interview.permutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class SolutionLevelTwo {
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return rst;
		}

		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int j = nums[i];
			if (map.containsKey(j)) {
				map.put(j, map.get(j) + 1);
			} else {
				map.put(j, 1);
			}
		}

		helper(rst, new ArrayList<>(), map, nums.length);
		return rst;
	}

	// track remaining 1 variable (use 0 implicitly as target), track target len & curr len -> 2 variables
	private void helper(List<List<Integer>> rst, List<Integer> list, HashMap<Integer, Integer> map, int remaining) {
		if (remaining == 0) {
			rst.add(new ArrayList<>(list));
			return;
		}

		Set<Integer> keys = map.keySet();

		for (Integer key : keys) {
			int count = map.get(key);
			if (count > 0) {
				map.put(key, count - 1);
				list.add(key);

				helper(rst, list, map, remaining - 1);

				list.remove(list.size() - 1);
				map.put(key, count);
			}
		}
	}
}

package interview.Subsets;

import java.util.ArrayList;
import java.util.List;

public class SolutionBacktrack {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();

		if (nums == null || nums.length == 0) return rst;

		dfs(rst, nums, new ArrayList<>(), 0);

		return rst;
	}

	public void dfs(List<List<Integer>> rst, int[] nums, List<Integer> list, int level) {
		if (level >= nums.length) {
			rst.add(new ArrayList<>(list));
			return;
		}

		list.add(nums[level]);
		dfs(rst, nums, list, level + 1);

		list.remove(list.size() - 1);
		dfs(rst, nums, list, level + 1);
	}

	// use bfs
	public List<List<Integer>> bfsSolution(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();

		if (nums == null || nums.length == 0) return rst;

		rst.add(new ArrayList<>());
		List<Integer> list = new ArrayList<>();
		list.add(nums[0]);
		rst.add(list);

		for (int i = 1; i < nums.length; i++) {
			int size = rst.size();
			for (int j = 0; j < size; j++) {
				List<Integer> oldList = rst.get(j);
				List<Integer> newList = new ArrayList<>(oldList);
				newList.add(nums[i]);
				rst.add(newList);
			}
		}

		return rst;
	}
}

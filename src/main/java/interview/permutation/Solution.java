package interview.permutation;

import java.util.ArrayList;
import java.util.List;

class Solution {
	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return rst;
		}
		rst.add(new ArrayList<>());

		for (int i = 0; i < nums.length; i++) { // Pick element
			int currRstSize = rst.size();
			for (int j = 0; j < currRstSize; j++) { // pick base list
				List<Integer> baselist = rst.get(j);
				// Pick slot to insert element
				for (int index = 0; index < baselist.size(); index++) {
					baselist.add(index, nums[i]);
					rst.add(new ArrayList<>(baselist));
					baselist.remove(index);
				}
				baselist.add(nums[i]); //add to end, maybe it's cleaner to put it into the for loop??
			}
		}

		return rst;
	}
}

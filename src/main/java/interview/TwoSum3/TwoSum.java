package interview.TwoSum3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TwoSum {
	List<Integer> nums;
	/** Initialize your data structure here. */
	public TwoSum() {
		nums = new LinkedList<>();
	}

	/** Add the number to an internal data structure.. */
	public void add(int number) {
		nums.add(number);
	}

	/** Find if there exists any pair of numbers which sum is equal to the value. */
	public boolean find(int value) {
		Map<Integer, Integer> map = new HashMap<>();

		for (Integer num : nums) {
			// Remember keyword "Pair"!!!
			// todo Use map to store instead of array
			// count how many times an input appear. duplicate is possible. ex. 1 + 1 = 2
			if (map.containsKey(num)) {
				map.put(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}

			int diff = value - num;

			// I don't know wtf I was thinking about. This has nothing to do with whether value == num
			// only 2 situation:
			// 1. 2 nums are different
			// 2. 2 nums are the same

//			if (diff == 0 && num == 0 && map.get(0) > 1) {
//				return true;
//			}
//
//			if (diff != 0 && map.getOrDefault(diff, 0) >= 1) {
//				return true;
//			}

			if (map.containsKey(diff)) {
				 if (diff != num) return true;
				 else if (map.get(num) >= 2) return true;
			}

		}

		return false;
	}
}

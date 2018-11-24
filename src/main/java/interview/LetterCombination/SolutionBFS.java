package interview.LetterCombination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class SolutionBFS {
	final Map<Character, List<String>> map = new HashMap<>();
	public List<String> letterCombinations(String digits) {
		List<String> rst = new ArrayList<>();

		if (digits == null || digits.length() == 0) return rst;

		map.put('2', Arrays.asList("a","b","c"));
		map.put('3', Arrays.asList("d","e","f"));
		map.put('4', Arrays.asList("g","h","i"));
		map.put('5', Arrays.asList("j","k","l"));
		map.put('6', Arrays.asList("m","n","o"));
		map.put('7', Arrays.asList("p","q","r","s"));
		map.put('8', Arrays.asList("t","u","v"));
		map.put('9', Arrays.asList("w","x","y","z"));

		Queue<String> queue = new LinkedList<>();

		// populating the queue use the first digit
		for (String letter : map.get(digits.charAt(0))) {
			queue.offer(letter);
		}

		char[] chars = digits.toCharArray();

		for (int j = 1; j < chars.length; j++) {
			char c = chars[j];
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String str = queue.poll();
				for (String letter : map.get(c)) {
					queue.offer(str + letter);
				}
			}
		}

		while (!queue.isEmpty()) {
			rst.add(queue.poll());
		}

		return rst;
	}
}
package interview.LetterCaseMutation;

import java.util.ArrayList;
import java.util.List;

/**
 * replace char at is slow
 */
public class BetterSolution {
	public List<String> letterCasePermutation(String s) {
		List<String> result = new ArrayList();
		result.add("");

		if (s == null || s.length() == 0) {
			return result;
		}

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			result = appendChar(result, c);
		}

		return result;
	}

	private List<String> appendChar(List<String> result, char c) {
		boolean isDigit = c >= '0' && c <= '9';
		List<String> updated = new ArrayList<>();

		while (!result.isEmpty()) {
			String s = result.remove(0);
			if (isDigit) {
				updated.add(s + c);
			} else {
				updated.add(s + Character.toLowerCase(c));
				updated.add(s + Character.toUpperCase(c));
			}
		}

		return updated;
	}
}

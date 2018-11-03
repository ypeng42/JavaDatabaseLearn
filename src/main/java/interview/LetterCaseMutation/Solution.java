package interview.LetterCaseMutation;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> letterCasePermutation(String s) {
		List<String> result = new ArrayList();
		if (s == null || s.length() == 0) {
			result.add("");
			return result;
		}

		result.add(s);

		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i))) {
				List<String> updatedResult = new ArrayList();
				while (!result.isEmpty()) {
					String str = result.remove(0);
					addMutationAtPos(updatedResult, str, i);
				}
				result = updatedResult;
			}
		}

		return result;
	}

	private void addMutationAtPos(List<String> result, String s, int pos) {
		char c = s.charAt(pos);
		result.add(replaceCharAt(s, pos, Character.toUpperCase(c)));
		result.add(replaceCharAt(s, pos, Character.toLowerCase(c)));
	}

	private String replaceCharAt(String s, int pos, char c) {
		return s.substring(0, pos) + c + s.substring(pos + 1);
	}
}

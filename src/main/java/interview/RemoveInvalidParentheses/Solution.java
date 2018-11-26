package interview.RemoveInvalidParentheses;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> removeInvalidParentheses(String s) {
		List<String> rst = new ArrayList<>();
		if (validateInput(rst, s)) return rst;

		dfs(rst, new StringBuffer(s), 0, 0, '(', ')');
		return rst;
	}

	private void dfs(List<String> rst, StringBuffer sb, int x, int y, char open, char close) {
		// for loop start from i to validate all chars
		for (int count = 0, i = x; i < sb.length(); i++) {
			if (sb.charAt(i) == open) count++;
			if (sb.charAt(i) == close) count--;
			if (count >=0) continue;
			// remove char if invalid: try all candidates from [j ~ i], but skip consecutive close parenthese
			for (int j = y; j <= i; j++) {
				if (sb.charAt(j) == close && (j == y || sb.charAt(j - 1) != close)) {
					dfs(rst, sb.deleteCharAt(j), i, j, open, close);
					sb.insert(j, close);
				}
			}
			return;
		}
		// reverse s, and reverse open/close, call dfs
		sb.reverse();
		if (open == '(') {
			dfs(rst, sb, 0, 0, close, open);
		} else { // return result if all validations passed
			rst.add(sb.toString());
		}
		sb.reverse();
	}

	private boolean validateInput(List<String> rst, String s) {
		if (s == null) return true;
		if (s.length() == 0) {
			rst.add("");
			return true;
		}
		return false;
	}
}

package interview.ValidParentheses;

import java.util.Stack;

class Solution {
	public boolean isValid(String s) {
		if (s == null || s.length() == 0) {
			return true;
		}

		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '{' || c == '[' || c == '(') {
				stack.push(c);
			}

			if ((c == '}' || c == ']' || c == ')') && stack.isEmpty()) {
				return false;
			}

			if (c == '}' && stack.pop() != '{') return false;
			if (c == ')' && stack.pop() != '(') return false;
			if (c == ']' && stack.pop() != '[') return false;
		}

		return stack.isEmpty();
	}
}
package interview.validPalindrome2;

class Solution {
	public boolean validPalindrome(String s) {
		if (s == null || s.length() <= 1) return true;

		return validate(s, 0, s.length() - 1, true);
	}

	public boolean validate(String s, int start, int end, boolean chance) {
		if (start > end) return false;
		while (start < end) {
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
				continue;
			} else if (chance) {
				return validate(s, start + 1, end, false) || validate(s, start, end - 1, false);
			}
			return false;
		}
		return true;
	}

	public boolean wrongSolution(String s) {
		if (s == null || s.length() == 0) return true;

		boolean chance = true;
		int start = 0;
		int end = s.length() - 1;

		while(start < end) {
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} else if (chance) {
				chance = false;
				// ex. cecace
				if (s.charAt(start) == s.charAt(end - 1)) { // should use recursive check here, there are 2 paths possible
					end--;
				} else if (s.charAt(start + 1) == s.charAt(end)) { // should use recursive check here, there are 2 paths possible
					start++;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}

		return true;
	}
}
package interview.validPalindrome;

class Solution {
	public boolean isPalindrome(String s) {
		if (s.length() == 0) return true;

		int end = s.length() - 1;
		int start = 0;

		while (start < end) {
			while(end >= 0 && !Character.isLetterOrDigit(s.charAt(end))) {
				end--;
			}

			while(start <= (s.length() - 1) && !Character.isLetterOrDigit(s.charAt(start))) {
				start++;
			}

			if (end >= 0 && start <= (s.length() - 1) && Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
				return false;
			}

			end--;
			start++;
		}

		return true;
	}
}
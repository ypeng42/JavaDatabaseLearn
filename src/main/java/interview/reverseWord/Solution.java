package interview.reverseWord;

class Solution {
	public void reverseWords(char[] str) {
		if (str == null || str.length == 0) {
			return;
		}

		reverseAll(str, 0, str.length - 1);

		//reverse each word
		int start = 0;
		for (int i = 0; i < str.length; i++) {
			if (str[i] == ' ') {
				reverseAll(str, start, i - 1);
				start = i + 1;
			}
		}

		//reverse the last word, for 1 word sentence, reverse back the first reverse
		reverseAll(str, start, str.length - 1);
	}

	public void reverseAll(char[] str, int start, int end) {
		while (start <= end) {
			swap(str, start, end);
			start++;
			end--;
		}
	}

	public void swap(char[] str, int a, int b) {
		char temp = str[a];
		str[a] = str[b];
		str[b] = temp;
	}
}
package interview.wordAbbr;

public class Solution {
	public boolean validWordAbbreviation(String word, String abbr) {
		// s counter
		// loop through abbr, if match, increase sc, if number increase sc, no error at end -> success
		int s_i = 0, abbr_i = 0;
		while (abbr_i < abbr.length() && s_i < word.length()) {

			//skip over number
			if (abbr.charAt(abbr_i) >= '1' && abbr.charAt(abbr_i) <= '9') {
				int[] info = parseNum(abbr, abbr_i);

				s_i += info[1];
				abbr_i += info[0];
				continue;
			}

			// compare char
			if (!sameChar(word, abbr, s_i, abbr_i)) return false;

			abbr_i++;
			s_i++;
		}

		return s_i == word.length() || abbr_i == abbr.length();
	}

	private int[] parseNum(String s, int i) {
		int[] res = new int[2];
		int counter = 0;
		StringBuilder sb = new StringBuilder();

		while(i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
			sb.append(s.charAt(i));
			i++;
			counter++;
		}

		res[0] = counter;
		res[1] = Integer.parseInt(sb.toString());
		return res;
	}

	private boolean sameChar(String a, String b, int a_i, int b_i) {
		return a.charAt(a_i) == b.charAt(b_i);
	}
}

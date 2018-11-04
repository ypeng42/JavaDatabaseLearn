package interview.backStringCompare;

/**
 * Aim for o(n) time and o(1) space
 */
public class Solution {
	public boolean backspaceCompare(String s, String t) {
		int i = s.length() - 1; //s -> i
		int j = t.length() - 1; //t -> j

		while(i >= 0 && j >= 0) {
			i = setBack(s, i);
			j = setBack(t, j);

			if (i >= 0 && j >= 0) {
				if (t.charAt(j) != s.charAt(i)) {
					return false;
				} else {
					j--;
					i--;
				}
			}
		}

		i = setBack(s, i);
		j = setBack(t, j);

		return i < 0 && j < 0;
		// a, bc##a

		// b#cx#d, a#c##d
	}

	/**
	 * Miss this case initially
	 *  bxj##tw
	 *  bxo#j##tw
	 */
	private int setBack(String s, int i) {
		int hashNum = 0;
		while (i >= 0 && (s.charAt(i) == '#' || hashNum > 0)) {
			if (s.charAt(i) == '#') {
				hashNum++;
			} else {
				hashNum--;
			}
			i--;
		}

		return i - hashNum;
	}
}

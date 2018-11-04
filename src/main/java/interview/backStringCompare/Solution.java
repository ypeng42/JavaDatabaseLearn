package interview.backStringCompare;

/**
 * Aim for o(n) time and o(1) space.
 *
 * I feel like this is more elegant than solution I saw online
 */
public class Solution {
	public boolean backspaceCompare(String s, String t) {
		int i = s.length() - 1; //s -> i
		int j = t.length() - 1; //t -> j

		while(i >= 0 && j >= 0) {
			i = setBack(s, i);
			j = setBack(t, j);

			// after setBack, index could be negative. Remember anything change before charAt() -> index check!
			if (i >= 0 && j >= 0) {
				if (t.charAt(j) != s.charAt(i)) {
					return false;
				} else {
					j--;
					i--;
				}
			}
		}

		// for case:
		// a
		// vx##a
		i = setBack(s, i);
		j = setBack(t, j);

		return i < 0 && j < 0;
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

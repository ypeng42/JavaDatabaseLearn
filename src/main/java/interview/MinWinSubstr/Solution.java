package interview.MinWinSubstr;

public class Solution {
	public String minWindow(String S, String T) {
		char[] s = S.toCharArray(), t = T.toCharArray();
		int sindex = 0, tindex = 0, slen = s.length, tlen = t.length, start = -1, len = slen;
		while(sindex < slen) {
			if(s[sindex] == t[tindex]) {
				if(++tindex == tlen) {
					// b d e
					// for b b b d d e
					// we want to get back to the right most b
					int end = sindex+1;
					while(--tindex >= 0) {
						while(s[sindex--] != t[tindex]);
					}
					++sindex;
					// b d e
					// may have b b d e x y z b b b d d e
					// set to back to zero
					++tindex;

					//record the current smallest candidate
					if(end - sindex < len) {
						len = end - sindex;
						start = sindex;
					}
				}
			}
			++sindex;
		}
		return start == -1? "" : S.substring(start, start + len);
	}
}

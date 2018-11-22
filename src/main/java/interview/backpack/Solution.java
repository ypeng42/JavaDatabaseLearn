package interview.backpack;

public class Solution {
	public int backPack(int m, int[] A) {
		if (A == null || A.length < 0) {
			return 0;
		}
		int n = A.length;
		int[][] dp = new int[n + 1][m + 1];

		// 6
		// 2, 3, 4

		// Calculcate possibility for i items to fill up w weight
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				// default: item(i-1) not used:
				dp[i][j] = dp[i - 1][j];

				// ex. 2, 3, 4; limit is 6
				// after only use 2, for limit 2 - 6, we will have 2 2 2 2 2
				// now we have 2 books to use
				// if limit - curr size > 0, we could use the curr book
				// dp[i - 1][j - A[i - 1]] ensures that the value won't go over limit, because dp[i - 1][j - A[i - 1]] <= j - A[i - 1]
				if (j - A[i - 1] >= 0) { // possible to use item(i-1)

					// 5, 2, 3, limit 6
					// after 5, limit 5, 6 are 5
					// during 5, 2,   limit 5: 2, limit 6 : 2, 2 is not used, that's wht the Math.max() here is needed
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - A[i - 1]] + A[i - 1]); // use item(i-1)
				}
			}
		}

		return dp[n][m];
	}

	public int backPackIII(int[] A, int[] V, int m) {
		if (A == null || A.length == 0 || V == null || V.length == 0 || m <= 0) {
			return 0;
		}
		int n = A.length;
		int[] dp = new int[m + 1]; // DP on weight
		dp[0] = 0; // 0 items to fill 0 weight, value = 0

		for (int i = 1; i <= n; i++) {
			for (int j = A[i - 1]; j <= m && j >= A[i - 1]; j++) {
				// 3, 5, 7 in dp[] are just multiple copies of book size 2
				// dp[j] is max using i - 1 book, dp[j - A[i - 1]] + V[i - 1] is using i book
				dp[j] = Math.max(dp[j], dp[j - A[i - 1]] + V[i - 1]);
			}
		}
		return dp[m];
	}
}
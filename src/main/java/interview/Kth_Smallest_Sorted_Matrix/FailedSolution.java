package interview.Kth_Smallest_Sorted_Matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The base assumption is wrong! (A later slice will be larger than the previous slice)
 */
public class FailedSolution {
	public int kthSmallest(int[][] matrix, int k) {
		if (matrix == null || matrix.length == 0) {
			return 0;
		}

		int len = matrix.length;
		int outNum = 1;
		int slice = 1;
		boolean passD = false;

		while (k - outNum > 0) {
			k = k - outNum;
			if (!passD) {
				outNum++;
				if (outNum >= len) {
					passD = true;
				}
			} else {
				outNum--;
			}
			slice++;
		}

		//now find the kth of the current slice

		//get corrdinate of all items on the slice
		List<Integer> list = new ArrayList<>();
		if (slice <= len) {
			int y = slice - 1;
			int x = 0;
			while(y >= 0) {
				list.add(matrix[x][y]);
				y--;
				x++;
			}
		} else {
			int x = slice - (len - 1) - 1;
			int y = slice - x - 1;
			while (x <= (len - 1)) {
				list.add(matrix[x][y]);
				y--;
				x++;
			}
		}

		Collections.sort(list);

		return list.get(k - 1);
	}
}

package interview.SpiralMatrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0) return new ArrayList<>();

		int len = matrix[0].length;
		int height = matrix.length;
		int size = len * height;

		// 0 -> right
		// 1 -> down
		// 2 -> left
		// 3 -> up
		int action = 0;
		int row = 0;
		int col = 0;

		int right = len - 1;
		int left = 0;
		int up = 0;
		int down = height - 1;

		List<Integer> rst = new ArrayList<>(size);
		if (rst.size() == 1) {
			rst.add(matrix[0][0]);
			return rst;
		}

		while (rst.size() != size) {
			switch(action) {
				case 0: //right
					for (col = left; col <= right; col++) {
						rst.add(matrix[row][col]);
					}
					col--; // to reset the for loop last ++ effect
					up++;
					action = 1;
					break;

				case 1: //down
					for(row = up; row <= down; row++) {
						rst.add(matrix[row][col]);
					}
					row--;
					right--;
					action = 2;
					break;

				case 2: //left
					for(col = right; col >= left; col--) {
						rst.add(matrix[row][col]);
					}
					col++;
					down--;
					action = 3;
					break;

				case 3: //up
					for(row = down; row >= up; row--) {
						rst.add(matrix[row][col]);
					}
					row++;
					left++;
					action = 0;
					break;
			}
		}

		return rst;
	}
}

package interview.Surrounded_Region;

public class FailedSolution {
	/**
	 * Things did wrong:
	 * 1. Don't forget to -1 when handling length!
	 * 2. for 2d array, arr.length is row (how many horizontal array being stacked), arr[0].length is col
	 * 3. really subtle thing, pay attention to the direction of recursion, in this case, I should move to left and top first,
	 * if instead I choose to move right and bottom it will stuck and cause error
	 * 4. No.3 cont, should go right before going bottom, if not -> another bug
	 * 5. this is a search problem, didn't recognize that, think it's a recursion problem
	 * 6. reverse thought, should go from border to mark all connected dot, but go from middle instead, which is impossible
	 * to do, for it to work, it will cause stack overflow exception (this one is KILLING me)
	 *
	 * thought when doing this one, I start implement recursion before feeling 100% certain about the idea, and
	 * looking back, the recursion alg is just a mess. Start too soon.
	 *
	 * trick: store pos on 2d array can use row * length + col, since each position is a unique int
	 */
	public void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}

		// 0 is unvisited, 1 is fixed, 2 is should flip, -1 visited
		int[][] fixedPoints = new int[board.length][board[0].length];

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == 'O') {
					if (onBorder(row, col, board)) {
						fixedPoints[row][col] = 1;
						continue;
					}

					if (fixedPoints[row][col] == 2 || shouldFlip(row, col, board, fixedPoints)) {
						board[row][col] = 'X';
					}
				}
			}
		}
	}

	private boolean shouldFlip(int row, int col, char[][] board, int[][] pointInfo) {
		// terminate if we either visited it or it's 'X'
		if (board[row][col] == 'X' || pointInfo[row][col] == 2) {
			return true;
		}

		if (pointInfo[row][col] == 1) {
			return false;
		}

		if (onBorder(row, col, board)) {
			pointInfo[row][col] = 1;
			return false;
		}

		// cannot make decision, propagate the check to connected cells
		boolean shouldFlip =  shouldFlip(row - 1, col, board, pointInfo) &&
			shouldFlip(row, col - 1, board, pointInfo) &&
			shouldFlip(row, col + 1, board, pointInfo) &&
			shouldFlip(row + 1, col, board, pointInfo);

		if (!shouldFlip) {
			pointInfo[row][col] = 1;
		} else {
			pointInfo[row][col] = 2;
		}

		return shouldFlip;
	}

	private boolean onBorder(int row, int col, char[][] board) {
		return row == 0 || row == board.length - 1 || col == 0 || col == board[0].length - 1;
	}
}

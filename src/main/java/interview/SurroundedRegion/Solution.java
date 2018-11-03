package interview.SurroundedRegion;

public class Solution {

	/**
	 * After realizing the right approach, the whole thing becomes SO MUCH easier to code
	 */
	public void solve(char[][] board) {
		if (board.length == 0) {
			return;
		}

		//left column
		for (int row = 0; row < board.length; row++) {
			if (board[row][0] == 'O') {
				bfsMarker(row, 0, board);
			}
		}

		// right column
		for (int row = 0; row < board.length; row++) {
			int rightCol = board[0].length - 1;
			if (board[row][rightCol] == 'O') {
				bfsMarker(row, rightCol, board);
			}
		}

		// top row
		for (int col = 0; col < board[0].length; col++) {
			if (board[0][col] == 'O') {
				bfsMarker(0, col, board);
			}
		}

		// bottom row
		for (int col = 0; col < board[0].length; col++) {
			int bottomRow = board.length - 1;
			if (board[bottomRow][col] == 'O') {
				bfsMarker(bottomRow, col, board);
			}
		}

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				if (board[row][col] == '*') {
					board[row][col] = 'O';
				} else if (board[row][col] == 'O') {
					board[row][col] = 'X';
				}
			}
		}
	}

	/**
	 * Mark 'O' connected to border 'O' as '*'
	 */
	private void bfsMarker(int row, int col, char[][] board) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return;
		}

		if (board[row][col] == 'O') {
			board[row][col] = '*';
			bfsMarker(row - 1, col, board);
			bfsMarker(row, col - 1, board);
			bfsMarker(row, col + 1, board);
			bfsMarker(row + 1, col, board);
		}
	}
}

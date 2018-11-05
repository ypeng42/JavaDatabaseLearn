package interview.DesignTicTacToe;

class TicTacToe {
	int[] rows;
	int[] cols;
	int diag;
	int revDiag;
	int n;

	/** Initialize your data structure here. */
	public TicTacToe(int n) {
		this.rows = new int[n];
		this.cols = new int[n];
		this.diag = 0;
		this.revDiag = 0;
		this.n = n;
	}

	/** Player {player} makes a move at ({row}, {col}).
	 @param row The row of the board.
	 @param col The column of the board.
	 @param player The player, can be either 1 or 2.
	 @return The current winning condition, can be either:
	 0: No one wins.
	 1: Player 1 wins.
	 2: Player 2 wins. */
	// play 1 to win -> n
	// play 2 to win -> -n
	public int move(int row, int col, int player) {
		rows[row] = rows[row] + (player == 1 ? 1 : -1);
		cols[col] = cols[col] + (player == 1 ? 1 : -1);

		// diag
		if (row == col) {
			diag = diag + (player == 1 ? 1 : -1);
		}

		if (row + col == this.n - 1) {
			revDiag = revDiag + (player == 1 ? 1 : -1);
		}

		return (Math.abs(rows[row]) == this.n || Math.abs(cols[col]) == this.n || Math.abs(diag) == this.n || Math.abs(revDiag) == this.n) ? player : 0;
	}
}

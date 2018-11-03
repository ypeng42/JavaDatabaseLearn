package interview.Surrounded_Region;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
//		char[][] input = {
//			{'X','X','X','X'},
//			{'X','O','O','X'},
//			{'X','X','O','X'},
//			{'X','O','X','X'}
//		};

		//bug: should explore left & top before right & bottom
//		char[][] input = {
//			{'O','X','O','O','O','X'},
//			{'O','O','X','X','X','O'},
//			{'X','X','X','X','X','O'},
//			{'O','O','O','O','X','X'},
//			{'X','X','O','O','X','O'},
//			{'O','O','X','X','X','X'}
//		};

		//bug: should explore right before explore bottom
//		char[][] input = {
//			{'X','X','X','X','O','X'},
//			{'O','X','X','O','O','X'},
//			{'X','O','X','O','O','O'},
//			{'X','O','O','O','X','O'},
//			{'O','O','X','X','O','X'},
//			{'X','O','X','O','X','X'}
//		};

		char[][] input3 = {
			{'X','O','X','O','X','O','O','O','X','O'},
			{'X','O','O','X','X','X','O','O','O','X'},
			{'O','O','O','O','O','O','O','O','X','X'},
			{'O','O','O','O','O','O','X','O','O','X'},
			{'O','O','X','X','O','X','X','O','O','O'},
			{'X','O','O','X','X','X','O','X','X','O'},
			{'X','O','X','O','O','X','X','O','X','O'},
			{'X','X','O','X','X','O','X','O','O','X'},
			{'O','O','O','O','X','O','X','O','X','O'},
			{'X','X','O','X','X','X','X','O','O','O'}
		};

		char[][] output3 = {
			{'X','O','X','O','X','O','O','O','X','O'},
			{'X','O','O','X','X','X','O','O','O','X'},
			{'O','O','O','O','O','O','O','O','X','X'},
			{'O','O','O','O','O','O','X','O','O','X'},
			{'O','O','X','X','O','X','X','O','O','O'},
			{'X','O','O','X','X','X','X','X','X','O'},
			{'X','O','X','X','X','X','X','O','X','O'},
			{'X','X','O','X','X','X','X','O','O','X'},
			{'O','O','O','O','X','X','X','O','X','O'},
			{'X','X','O','X','X','X','X','O','O','O'}};

		solution.solve(input3);
		print(input3);
		print(output3);
	}

	private static void print(char[][] board) {
		System.out.println("------------------------");
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				System.out.print(board[row][col]);
			}
			System.out.println("");
		}
	}
}

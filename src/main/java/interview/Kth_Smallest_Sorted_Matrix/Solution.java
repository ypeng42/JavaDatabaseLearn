package interview.Kth_Smallest_Sorted_Matrix;

import java.util.PriorityQueue;

public class Solution {
	public int kthSmallest(int[][] matrix, int k) {
		int len = matrix.length;
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();

		// use first row as base
		for (int col = 0; col <= len - 1; col++)
			pq.offer(new Tuple(0, col, matrix[0][col]));

		for (int i = 0; i < k-1; i++) {
			Tuple t = pq.poll();

			if(t.x == len - 1) continue;

			// add() and offer() are same thing
			pq.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
		}

		//poll() always returns the min, to return max, pass in a reverse lambda comparator
		return pq.poll().val;
	}

	class Tuple implements Comparable<Tuple> {
		int x, y, val;
		public Tuple (int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(Tuple that) {
			return this.val - that.val;
		}
	}
}

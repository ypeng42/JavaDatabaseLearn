package interview.ConnectingGraph;

/**
 * The idea of union find is that:
 * ex. 1,3,6,9 are in a union, how we know 3 is in this union?
 * let them ALL pointing to the same ROOT!
 *
 * which node is chosen as root doesn't matter, as long as there is ONE root.
 *
 * then when we check 3 and 10, just compare their root. Same root, same union.
 */
public class ConnectingGraph {
	// Placeholder for all the UninFind relationships
	private int[] father = null;

	/**
	 Initialize one element to each individual union.
	 */
	public ConnectingGraph(int n) {
		father = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			father[i] = i;
		}
	}

	/**
	 Union function.
	 Find the root father, if not the same, union them together.
	 */
	public void connect(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA != rootB) { // they shouldn't be in the same union!
			father[a] = rootB; // doesn't mater which assigns to which one.
		}
	}

	/** Check if the two integer are in the same union */
	public boolean query(int a, int b) {
		return find(a) == find(b);
	}

	/*
		Find function: find the root parent as the head for entire union.
		If found parent as itself, return it.
		Otherwise, recursively look for father and assign the result eventually.
	*/
	private int find(int x) {
		if (father[x] == x) {
			return x; // x is the root parent, return itself.
		}
		//whenever we are doing find root, update each node poing to the Root (could be updated now)
		//to avoid doing this over and over
		return father[x] = find(father[x]);
	}
}

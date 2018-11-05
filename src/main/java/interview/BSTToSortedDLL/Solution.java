package interview.BSTToSortedDLL;

public class Solution {
	class Node {
		public int val;
		public Node left;
		public Node right;

		public Node() {}

		public Node(int _val,Node _left,Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	}

	private Node prev; // the node smaller than the current node
	private Node head;

	public Node bstToSortedDLL(Node node) {
		if(node == null) return null;

		bstToSortedDLL(node.left);

		node.left = prev;

		if (prev != null) {
			prev.right = node;
		} else {
			head = node; // head will stay equals to the smallest element. this is used to point smallest to largest.
		}

		prev = node;

		Node right = node.right;

		//the following 2 lines are used to point smallest to largest.
		head.left = node; // before reaching the max node, head.left will keep getting reset. The last time it get updated, it points to max node.
		node.right = head; //before reaching the max node, node.right will keep getting reset (curr node become prev and prev.right overwrite it)

		bstToSortedDLL(right);
		return head;
	}

}

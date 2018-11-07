package interview.LinkedListSum;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	class Pair {
		ListNode node;
		int c;
		public Pair(){}

		public Pair(ListNode node, int c) {
			this.node = node;
			this.c = c;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int len1 = length(l1);
		int len2 = length(l2);

		if (len1 > len2) {
			l2 = padZero(l2, len1 - len2);
		} else if (len2 > len1) {
			l1 = padZero(l1, len2 - len1);
		}

		Pair val = addListHelper(l1, l2);
		if (val.c > 0) {
			return prepend(val.node, 1);
		} else {
			return val.node;
		}
	}


	public Pair addListHelper(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null) {
			return new Pair();
		}

		Pair prevVal = addListHelper(l1.next, l2.next);

		int val = l1.val + l2.val + prevVal.c;
		int c = val / 10;

		ListNode node = prepend(prevVal.node, val % 10);

		return new Pair(node, c);
	}



	public int length(ListNode node) {
		ListNode head = node;
		int len = 0;
		while (head != null) {
			head = head.next;
			len++;
		}

		return len;
	}

	public ListNode padZero(ListNode node, int n) {
		ListNode head = node;
		for (int i = 0; i < n; i++) {
			head = prepend(head, 0);
		}

		return head;
	}

	public ListNode prepend(ListNode node, int val) {
		ListNode head = new ListNode(val);
		head.next = node;
		return head;
	}
}
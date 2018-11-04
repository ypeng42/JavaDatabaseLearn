package interview;

public class TreeHelper {
	public static void inOrder(TreeNode root) {
		if (root != null) {
			inOrder(root.left);
			System.out.print(root.val + " ");
			inOrder(root.right);
		}
	}

	public static TreeNode makeTree(int[] arr) {
		return insertLevelOrder(arr, 0);
	}

	public static TreeNode insertLevelOrder(int[] arr, int i) {
		TreeNode root = null;
		if (i < arr.length) {
			root = new TreeNode(arr[i]);

			root.left = insertLevelOrder(arr,2 * i + 1);
			root.right = insertLevelOrder(arr,2 * i + 2);
		}
		return root;
	}

	public static TreeNode makeBST(int[] arr) {
		TreeNode root = new TreeNode(arr[0]);

		for (int i = 1; i < arr.length; i++) {
			addNode(root, arr[i]);
		}

		return root;
	}

	private static void addNode(TreeNode node, int val) {
		if (val > node.val) {
			if (node.right == null) {
				node.right  = new TreeNode(val);
			} else {
				addNode(node.right, val);
			}
		} else {
			if (node.left == null) {
				node.left  = new TreeNode(val);
			} else {
				addNode(node.left, val);
			}
		}
	}
}

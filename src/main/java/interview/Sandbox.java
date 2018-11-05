package interview;

public class Sandbox {
	public static void main(String[] args) {
		TreeNode node = new TreeNode();
		func(node);
		System.out.println(node);

		TreeNode[] arr = new TreeNode[1];
		arr[0] = node;
		update(arr);
		System.out.println(arr);
	}

	// this will!
	public static void update(TreeNode[] arr) {
		arr[0] = null;
	}

	// this won't update the object passed in.
	public static void func(TreeNode a) {
		a = null;
	}
}

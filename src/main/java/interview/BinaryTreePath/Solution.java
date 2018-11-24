package interview.BinaryTreePath;

import interview.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public List<String> binaryTreePaths(TreeNode root) {
		if (root == null) return new ArrayList<>();

		List<String> rst = new ArrayList<>();
		List<Integer> path = new ArrayList<>();

		getPath(root, path, rst);

		return rst;
	}

	public void getPath(TreeNode node, List<Integer> path, List<String> rst) {
		if (node == null) return;

		path.add(node.val);

		//terminate
		if (node.left == null && node.right == null) {
			rst.add(printList(path));
			path.remove(path.size() - 1);
			return;
		}

		if (node.left != null) { //This check is unnecessary
			getPath(node.left, path, rst);
		}

		if (node.right != null) { //This check is unnecessary
			getPath(node.right, path, rst);
		}

		path.remove(path.size() - 1);
	}

	public String printList(List<Integer> list) {
		if (list.isEmpty()) return null;

		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));

		for(int i = 1; i < list.size(); i++) {
			sb.append("->" + String.valueOf(list.get(i)));
		}

		return sb.toString();
	}
}

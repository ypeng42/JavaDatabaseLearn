package interview.binaryTreeVerticalOrder;

import interview.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
	class Node {
		int val, level;
		public Node (int val, int level) {
			this.val = val;
			this.level = level;
		}
	}

	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> rst = new ArrayList<>();
		if (root == null) return rst;

		HashMap<Integer, List<Node>> map = new HashMap<>();
		dfs(root, map, 0, 0);

		List<Integer> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys);

		for (Integer key : keys) {
			List<Node> nodes = map.get(key);

			nodes.sort((a, b) -> a.level - b.level);

			rst.add(nodes.stream().map(node -> node.val).collect(Collectors.toList()));
		}

		return rst;
	}

	public void dfs(TreeNode node, HashMap<Integer, List<Node>> map, int col, int level) {
		if (node == null) return;

		map.putIfAbsent(col, new ArrayList<>());
		List<Node> l = map.get(col);

		l.add(new Node(node.val, level));

		dfs(node.left, map, col - 1, level + 1);
		dfs(node.right, map, col + 1, level + 1);
	}
}
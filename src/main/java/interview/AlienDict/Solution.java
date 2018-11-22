package interview.AlienDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	Map<Character, List<Character>> graph = new HashMap<>();
	Map<Character, Integer> visited = new HashMap<>();
	StringBuffer sb = new StringBuffer();

	public String alienOrder(String[] words) {
		if (words == null || words.length == 0) {
			return "";
		}

		// Build graph, and visited map.
		buildGraph(words);

		// Topological sort with dfs
		for (char c : graph.keySet()) { // go through each node and store rst in stack is topo sort
			if (!dfs(c)) {
				return "";
			}
		}

		return sb.toString();
	}

	private boolean dfs(Character c) {
		if (visited.get(c) == 1) return true; // success
		if (visited.get(c) == -1) return false; //loop, false

		// -1 is used to prevent loop.
		// ex.
		// [x, z, x]
		visited.put(c, -1);
		for (char edgeNode : graph.get(c)) {
			if (!dfs(edgeNode)) {
				return false;
			}
		}
		visited.put(c, 1);
		sb.insert(0, c); // leaf element, add to buffer; this is topo sort, similar to use tack
		return true;
	}

	/**
	 * [
	 *   "wrt",
	 *   "wrf",
	 *   "er",
	 *   "ett",
	 *   "rftt"
	 * ]
	 */
	private void buildGraph (String[] words) {
		// Create nodes (all characters)
		for (String word: words) {
			for (char c : word.toCharArray()) {
				if (!graph.containsKey(c)) {
					graph.put(c, new ArrayList<>());
					visited.put(c, 0);
				}
			}
		}

		/*
		 * [
		 *   "wrt",
		 *   "wrf",
		 *   "er",
		 *   "ett",
		 *   "rftt"
		 * ]
		 *
		 * horizontal order doesn'r matter, wrf doesn't mean 'w' should come before 'r'
		 * only care vertical order
		 * ex. wrt vs wrf -> t before f
		 */
		// Build edges
		for (int i = 0; i < words.length - 1; i++) {
			int index = 0;
			while (index < words[i].length() && index < words[i + 1].length()) {
				char c1 = words[i].charAt(index);
				char c2 = words[i + 1].charAt(index);
				if (c1 != c2) {
					graph.get(c1).add(c2); // c1 comes before c2
					break;
				}
				index++;
			}
		}
	}
}
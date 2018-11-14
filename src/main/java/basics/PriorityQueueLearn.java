package basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueLearn {
	public static void main(String[] args) {
		//logical shift right, essentially divide by 2. don't need to care about negative since length can't be negative
//		System.out.println(3 >>> 1);
//		System.out.println(3 / 2);

		// build a tree from 0 1 2 3 4 5 6, that's a quick way to figure out pattern between parent/child
		List<Integer> list = new ArrayList<>();
		list.add(23);
		list.add(13);
		list.add(43);
		list.add(5);
		list.add(17);
		list.add(9);
		PriorityQueue q = new PriorityQueue(list);

		//x implements Comparable<TYPE_HERE>
//		System.out.println(q.poll());

		list.sort(Collections.reverseOrder()); // basically (a, b) b.comp(a) instead of a.comp(b)
		list.sort((a, b) -> a.compareTo(b)); //same as Comparator.naturalOrder();
		System.out.println(list);
	}
}

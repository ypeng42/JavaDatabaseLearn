package interview;

public class PositionBinarySearch {
	public static void main(String[] args) {

		System.out.println(PositionBinarySearch.binarySearch(new int[]{0, 1, 2}, 1));

//		System.out.println(PositionBinarySearch.binarySearch(new int[]{0, 2}, -1));
//		System.out.println(PositionBinarySearch.binarySearch(new int[]{0, 2}, 3));
//		System.out.println(PositionBinarySearch.binarySearch(new int[]{10, 20, 30, 40, 50}, 40));
//		System.out.println(PositionBinarySearch.binarySearch(new int[]{2, 5, 7, 11, 15, 20}, 11));

	}

	/**
	 * return the index of the position I will insert target to arr
	 */
	public static int binarySearch(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;

		while(left <= right) {
			int mid = (left + right) / 2;

			if (arr[mid] == target) { //let's be crystal clear here what to do for equal
				return mid + 1;
			} else if (arr[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return left;
	}
}

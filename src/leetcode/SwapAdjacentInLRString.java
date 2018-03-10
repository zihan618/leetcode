package leetcode;

import java.util.ArrayList;

public class SwapAdjacentInLRString {

	public static void main(String[] args) {
		System.out.println(new SwapAdjacentInLRString().canTransform("RXXLRXRXL", "XRLXXRRLX"));

	}

	/**
	 * ºÜ¼òµ¥
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean canTransform(String start, String end) {
		if (start.length() != end.length()) {
			return false;
		}
		ArrayList<Character> arrayList1 = new ArrayList<>();
		ArrayList<Character> arrayList2 = new ArrayList<>();
		ArrayList<Integer> arrayList3 = new ArrayList<>();
		ArrayList<Integer> arrayList4 = new ArrayList<>();
		for (int i = 0; i < start.length(); i++) {
			if (start.charAt(i) != 'X') {
				arrayList1.add(start.charAt(i));
				arrayList3.add(i);
			}
			if (end.charAt(i) != 'X') {
				arrayList2.add(end.charAt(i));
				arrayList4.add(i);
			}
		}
		if(!arrayList1.equals(arrayList2)) {
			return false;
		}
		for( int i=0; i<arrayList1.size(); i++) {
			if(arrayList1.get(i) == 'L' && arrayList3.get(i) < arrayList4.get(i)) {
				return false;
			}else if( arrayList1.get(i) == 'R' && arrayList3.get(i) > arrayList4.get(i)) {
				return false;
			}
		}
		return true;
		
	}
}

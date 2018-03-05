package leetcode;

import java.util.LinkedList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new LetterCombinationsofaPhoneNumber().letterCombinations("2"));

	}

	/**
	 * 利用队列来实现 每个的自由组合
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		LinkedList<String> res = new LinkedList<>();
		if(digits.isEmpty()) {
			return res;
		}
		res.add("");
		for (int i = 0; i < digits.length(); i++) {
			while (res.peek().length() != i+1) {
				String tmp = res.removeFirst();
				int index = Character.getNumericValue(digits.charAt(i))  ;
				for(int j=0; j<mapping[index].length(); j++) {
					res.addLast(tmp+mapping[index].charAt(j));
				} 
			}
		}
		return res;
	}

}

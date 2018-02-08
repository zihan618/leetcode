package leetcode;

public class ZigZag {

	public static void main(String[] args) {
		System.out.println((new ZigZag()).convert("A", 3));

	}

	/**
	 * 自己写的方法，根据每行的下表的周期性规律，逐个添加字符即可，字符串的每个位置只到达一次， 时间复杂度应该是 线性的
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		StringBuilder stringBuilder = new StringBuilder();
		int cycle = 2 * numRows - 2;
		for (int i = 0; i < numRows; i++) {
			int j = i;
			while (j < s.length()) {
				if (i == 0 || i == numRows - 1) {
					stringBuilder.append(s.charAt(j));
				} else {
					stringBuilder.append(s.charAt(j));
					int possible_pos = cycle * ((j / cycle) + 1) - i;
					if (possible_pos < s.length()) {
						stringBuilder.append(s.charAt(possible_pos));
					}
				}
				j += cycle;
			}
		}
		return stringBuilder.toString();

	}
}

package leetcode;

public class FindtheClosestPalindrome {

	public static void main(String[] args) {
		System.out.println(new FindtheClosestPalindrome().nearestPalindromic("1"));
	}

	public String nearestPalindromic(String n) {
		StringBuilder stringBuilder = new StringBuilder();
		if (n.length() % 2 == 1) {
			int pre = Integer.parseInt(n.substring(0, n.length() / 2 + 1));
			stringBuilder.append(pre);
			stringBuilder.append(
					new StringBuilder(stringBuilder.toString()).reverse().subSequence(1, stringBuilder.length()));
			int res1 = Integer.parseInt(stringBuilder.toString());

			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append(pre - 1);
			StringBuilder stringBuilder2 = new StringBuilder(stringBuilder.toString()).reverse();
			if(stringBuilder2.length() < n.length() / 2 + 1) {
				stringBuilder.append(stringBuilder2.toString());
			}else {
				stringBuilder.append(stringBuilder2.subSequence(1, stringBuilder.length()));
			}
			int res2 = Integer.parseInt(stringBuilder.toString());

			
			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append(pre + 1);
			stringBuilder2 = new StringBuilder(stringBuilder.toString()).reverse();
			if(stringBuilder2.length() == n.length() / 2 + 1) {
				stringBuilder.append(stringBuilder2.subSequence(1, stringBuilder.length()));
			}else {
				stringBuilder.delete(0, stringBuilder.length());
				for(int i=0; i<n.length(); i++) {
					stringBuilder.append(9);
				}
			}
			int res3 = Integer.parseInt(stringBuilder.toString());

			int std = Integer.parseInt(n);
			int res = Math.abs(res1 - std) < Math.abs(res2 - std) ? res1 : res2;
			if(Math.abs(res1 - std) == Math.abs(res2 - std) ) {
				res = Math.min(res1, res2) ;
			}
			res = Math.abs(res - std) < Math.abs(res3 - std) ? res : res3;
			if(Math.abs(res - std) == Math.abs(res3 - std)) {
				res = Math.min(res, res3) ;
			}
			return String.valueOf(res);
		} else {
			int pre = Integer.parseInt(n.substring(0, n.length() / 2));
			stringBuilder.append(pre);
			stringBuilder.append(new StringBuilder(stringBuilder.toString()).reverse().toString());
			int res1 = Integer.parseInt(stringBuilder.toString());

			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append(pre - 1);
			stringBuilder.append(new StringBuilder(stringBuilder.toString()).reverse().toString());
			int res2 = Integer.parseInt(stringBuilder.toString());

			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append(pre + 1);
			stringBuilder.append(new StringBuilder(stringBuilder.toString()).reverse().toString());
			int res3 = Integer.parseInt(stringBuilder.toString());

			int std = Integer.parseInt(n);
			int res = Math.abs(res1 - std) < Math.abs(res2 - std) ? res1 : res2;
			res = Math.abs(res1 - std) == Math.abs(res2 - std) ? Math.min(res1, res2) : res;
			res = Math.abs(res - std) < Math.abs(res3 - std) ? res : res3;
			res = Math.abs(res - std) == Math.abs(res3 - std) ? Math.min(res, res3) : res;
			return String.valueOf(res);
		}

	}
}

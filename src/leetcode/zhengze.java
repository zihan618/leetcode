package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class zhengze {

	public static void main(String[] args) {
		
		System.out.println((new zhengze()).isMatch("", "."));
	}

	public boolean isMatch(String s, String p) {
		ArrayList<String> arrayList = new ArrayList<>();
		for (int i = 0; i < p.length(); i++) {
			if (i < p.length() - 1 && p.charAt(i + 1) == '*') {
				arrayList.add("" + p.charAt(i) + p.charAt(i + 1));
				i++;

			} else {
				arrayList.add("" + p.charAt(i));
			}
		}
		Iterator<String> iterator = arrayList.iterator();
		int pos = 0;
		while (iterator.hasNext()) {
			String tmp = iterator.next();
			
			if (tmp.length() == 1) {
			//	System.out.println(tmp.length());
				if (pos < s.length() && tmp.equals(".")) {
					pos++;
				} else if (pos < s.length() && tmp.equals("" + s.charAt(pos)) ) {
			
					pos++;
				} else {
					//System.out.println(tmp.length());
					return false;
				}
			} else {
				if (tmp.charAt(0) != '.') {
					while (pos < s.length() && tmp.charAt(0) == s.charAt(pos)) {
						pos++;
					}
				}else if(tmp.charAt(0) == '.'){
					while (pos < s.length() ) {
						pos++;
					}
				}

			}
			
			if( pos == s.length() && iterator.hasNext()) {
				return false;
			}
		}
		if( pos != s.length()) {
			return false;
		}
		return true;
	}

}

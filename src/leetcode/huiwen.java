package leetcode;

/**
 * 
 * @author 12260
 *
 */
public class huiwen {
	public static void main(String[] strings) {
		String s = "2332";

		
		// System.out.println(end2-start2);
		System.out.println((new huiwen()).longestPalindrome2(s));
		// return s.subString(start+1, end);
	}
	
	public String longestPalindrome(String s) {
		int first = 0, last = 0;
		int start2 = 0, end2 = 0;
		int res = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			last = first = i;
			while (last < len && first >= 0 && s.charAt(first) == s.charAt(last)) {

				if (res < last - first + 1) {
					res = last - first + 1;
					start2 = first;
					end2 = last;
				}

				last++;
				first--;
			}
			first = i;
			last = i + 1;
			while (last < len && first >= 0 && s.charAt(first) == s.charAt(last)) {
				if (res < last - first + 1) {
					res = last - first + 1;
					start2 = first;
					end2 = last;
				}

				last++;
				first--;

			}
		}
		return s.substring(start2, end2 + 1);
    }

	public String longestPalindrome3(String s) {
		return null;
	}
	public String longestPalindrome2(String s) {
		int max_len = 0;
		int start_index = 0;
		for(int i=0; i<s.length(); i++) {
			if(i>max_len && start_index==i-max_len && s.charAt(i)==s.charAt(i-max_len-1) ) {
				max_len = max_len + 2;
				start_index = i-max_len+1;
			}else if (isPalindrome(s, i-max_len, i)) {
				max_len = max_len + 1;
				start_index = i-max_len+1;
			}
		}
		return s.substring(start_index, start_index+max_len);
	}
	
	boolean isPalindrome(final String  s, int start, int end) {
		while( start < end) {
			if( s.charAt(start) != s.charAt(end) ) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}
}

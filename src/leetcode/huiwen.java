package leetcode;

/**
 * 
 * @author 12260
 *
 */
public class huiwen {
	public static void main(String[] strings) {
		String s = "babad";
		
		System.out.println((new huiwen()).longestPalindrome3(s));
		// return s.subString(start+1, end);
	}
	
	/**
	 * 从中间向两边扩展
	 * @param s
	 * @return
	 */
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

	
	/**
	 * manacher 算法
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {
		StringBuilder stringBuilder = new StringBuilder("#");
		for( int i=0; i<s.length(); i++) {
			stringBuilder.append( s.charAt(i) + "#");
		}
		
		int pos=0, maxRight=0;
		int[] radis = new int[stringBuilder.length()];
		for( int i=0;i<radis.length; i++) {
			radis[i]=0;
		}
		for( int i=0; i<stringBuilder.length(); i++) {
			if(i < maxRight) {
				radis[i] = Math.min(radis[2*pos-i], maxRight-i);
			}
			while (i>radis[i] && i+radis[i]<stringBuilder.length()-1 && stringBuilder.charAt(i-1-radis[i])==stringBuilder.charAt(i+radis[i]+1)) {
				radis[i]++;
			}
			if( i+radis[i] > maxRight) {
				pos = i;
				maxRight = i+radis[i];
			}
		}
	//	System.out.println(s);
		int res_pos = 0;
		for( int i=0; i<radis.length; i++) {
			res_pos = radis[res_pos] > radis[i] ?  res_pos : i;
		}
		return stringBuilder.substring(res_pos-radis[res_pos], res_pos+radis[res_pos]+1).replaceAll("#", "");
	}
	/**
	 * 从外侧向中间扩展
	 * @param s
	 * @return
	 */
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

package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class zhengze {

	public static void main(String[] args) {
		
		System.out.println(new zhengze().isMatch("aa", "a*"));
	
	}

	/**
	 * 自己想的  动态规划解决  类似于最长公共子序列的求解方法
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		ArrayList<String> s_subs = new ArrayList<>();
		s_subs.add("");
		for( int i=0; i<s.length(); i++) {
			s_subs.add("" + s.charAt(i));
		}
		ArrayList<String> p_subs = new ArrayList<>();
		for( int i=0; i<p.length(); i++) {
			if( p.charAt(i) == '*') {
				p_subs.set(p_subs.size()-1,  p_subs.get(p_subs.size()-1)+"*" );
			}else {
				p_subs.add("" + p.charAt(i));
			}
		}
		boolean[][] matrix = new boolean[s_subs.size()][p_subs.size()];
		String tmp_p = null, tmp_s = null;
		for( int i=0; i<s_subs.size(); i++) {
			for( int j=0; j<p_subs.size(); j++) {
				if( i== 0) {
					int k=0;
					while( k<p_subs.size() && p_subs.get(k).length()>1) {
						matrix[0][k++] = true;
					}
					while(k<p_subs.size()) {
						matrix[0][k++] = false;
					}
					break;
				}else {
					tmp_p = p_subs.get(j);
					tmp_s = s_subs.get(i);
					if(tmp_p.length() < 2) {
						if( j==0 ) {
							if( i==1) {
								matrix[i][j] = isSimpleMatch(tmp_s, tmp_p);
							}else {
								matrix[i][j] = false;
							}
						}else {
							matrix[i][j] = (matrix[i-1][j-1]) && isSimpleMatch(tmp_s, tmp_p);
						}
						//matrix[i][j] = (( i==1 && j==0)? true:matrix[i-1][j-1]) && isSimpleMatch(tmp_s, tmp_p);
					}else {
						if( j == 0) {
							matrix[i][j] = matrix[i-1][j] && isSimpleMatch( tmp_s, tmp_p);
						}else {
							matrix[i][j] = (matrix[i-1][j] && isSimpleMatch( tmp_s, tmp_p)) 
									|| ( (matrix[i][j-1]) && isSimpleMatch("", tmp_p))
									|| ( (matrix[i-1][j-1]) && isSimpleMatch(tmp_s, tmp_p));
						}
					}
				}
			}
		}
		return matrix[s_subs.size()-1][p_subs.size()-1];
	}
	
	boolean isSimpleMatch( String string, String pattern) {
		// string = a, pattern = a  or string = a, pattern = a*
 		if( !string.isEmpty() && pattern.startsWith(string)) {
			return true;
		}
		//  a , .
		if( pattern.equals(".") && !string.isEmpty()) {
			return true;
		}
		// "", a*
		if( string.isEmpty() && pattern.endsWith("*")) {
			return true;
		}
		
		if( pattern.equals(".*")) {
			return true;
		}
		return false;
	}

}

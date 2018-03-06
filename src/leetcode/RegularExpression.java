package leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public class RegularExpression {

	public static void main(String[] args) {
		
		System.out.println(new RegularExpression().isMatch2("aa", "a*"));
	
	}

	/**
	 * �Լ����  ��̬�滮���  ����������������е���ⷽ��
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if( p.isEmpty()) {
			return !s.isEmpty();
		}
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
							matrix[i][j] = matrix[i-1][j] && isSimpleMatch( tmp_s, tmp_p);//���ϵ���
						}else {
							matrix[i][j] = (matrix[i-1][j] && isSimpleMatch( tmp_s, tmp_p)) //������
									|| ( (matrix[i][j-1]) && isSimpleMatch("", tmp_p)) //������ ƥ���
									|| ( (matrix[i-1][j-1]) && isSimpleMatch(tmp_s, tmp_p)); //���ϵ�����
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

	/**
	 * ���Լ���Ĵ�ͬС�� ����û�а�pattern ��Ϊһ��һ�� ��Ҳ��ʡ����ArrayList��ʹ�� ��������һЩ   �ռ临�Ӷ� n*n
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch2( String s, String p) {
		if( p.isEmpty()) {
			return s.isEmpty();
		}
		boolean[][] matrix = new boolean[s.length()+1][p.length()+1];
		matrix[0][0] = true;
		//��ʼ�� �յ����
		for( int i=1; i<p.length()+1; i++) {
			if(p.charAt(i-1) == '*') {
				matrix[0][i] = matrix[0][i-2];
			}
		}
		//System.err.println(matrix[0][3]);
		for( int i=1; i<s.length()+1; i++) {
			for( int j=1; j<p.length()+1; j++) {
				if( p.charAt(j-1) == '.') {
					matrix[i][j] = matrix[i-1][j-1];
				}else if (p.charAt(j-1) != '*') {
					matrix[i][j] = matrix[i-1][j-1] && p.charAt(j-1)==s.charAt(i-1);
				}else {
					matrix[i][j] = (matrix[i][j-2])//������
							||(matrix[i-1][j] && (p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'));//���ϵ���
							//||(matrix[i-1][j-2] && p.charAt(j-2)==s.charAt(i-1))//���ϵ�����
							//|| (p.charAt(j-2) == '.' && matrix[i-1][j-2]);//���ϵ����� �� ��.*
				}
			}
			//System.out.println(matrix[1][1]);
		}
		return matrix[s.length()][p.length()];
	}
	
	/**
	 * 
	 * ����һ��������������  �ռ临�ӶȽ�Ϊn ��ΪBooleanֵ �����true ����false ����д��
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch3(String s, String p) {
		
		return false;
	}
}

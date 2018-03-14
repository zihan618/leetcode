package leetcode;

import javax.naming.InitialContext;

public class RotateString {

	public static void main(String[] args) {
		System.out.println(new RotateString().rotateString3("abcde",  "cdeab"));
	}

	/**
	 * �Լ�д�ı��취
	 * @param A
	 * @param B
	 * @return
	 */
	 public boolean rotateString(String A, String B) {
	        if(A.length() != B.length()) {
	        	return false;
	        }
	        if(A.equals(B)) {
	        	return true;
	        }
	        for(int i=1; i<A.length(); i++) {
	        	if(B.lastIndexOf(A.substring(0, i)) == A.length()-i && B.indexOf(A.substring(i, A.length())) == 0) {
	        		return true;
	        	}
	        }
	        return false;
	 }
	 
	 /**
	  * �Ƚ�����ķ��� ����java���ַ���ƥ���Ǳ����ⷨ
	  * @param A
	  * @param B
	  * @return
	  */
	 public boolean rotateString2(String A, String B) {
		 return A.length() == B.length() && (A+A).contains(B);
	 }
	 
	 /**
	  * KMP ģʽƥ��  ������Ȼ����һ��������  ��low��
	  * @param A
	  * @param B
	  * @return
	  */
	 public boolean rotateString3(String A, String B) {
		 int[] next = new int[B.length()];
		 next[0] = -1;
		 int j=0, k=-1;
		 while (j<B.length()-1) {
			if( k==-1 || B.charAt(j)==B.charAt(k)) {
				j++;
				k++;
				next[j] = k;
			}else {
				k = next[k];
			}
		}
		 int a = 0, b = 0;
		 A = A + A;
		 while (a<A.length() && b<B.length()) {
			if(b == -1 || A.charAt(a) == B.charAt(b)) {
				a++;
				b++;
			}else {
				b = next[b];
			}
		}
		return b>=B.length();
		 
	 }
}

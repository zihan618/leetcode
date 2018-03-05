package leetcode;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] strings = {"bab","bcc" };
		System.out.println(new LongestCommonPrefix().longestCommonPrefix(strings));
	}

	 public String longestCommonPrefix(String[] strs) {
		 if (strs.length == 0) {
			return "";
		}
		
		 StringBuilder stringBuilder = new StringBuilder();
		 char c = ' ';
		 for(int i=0; true; i++) {
			 for(int j=0; j<strs.length; j++) {
				 if(i>=strs[j].length()) {
					 return stringBuilder.toString();
				 }else {
					if(j==0) {
						c = strs[0].charAt(i);
					}else{
						if(strs[j].charAt(i) != c) {
							return stringBuilder.toString();
						}
						
					}
					if(j == strs.length-1) {
						stringBuilder.append(c);
					}
				}
			 }
		 }
	    }
	 /**
	  * 其实不用stringbuilder也可以
	  * @param strs
	  * @return
	  */
	 public String longestCommonPrefix2(String[] strs) {
		 if( strs.length == 0) {
			 return "";
		 }
		 int i=0;
		 for(; i<strs[0].length(); i++) {
			 char c = strs[0].charAt(i);
			 for (String string : strs) {
				if(i>=string.length() || string.charAt(i) != c ) {
					return strs[0].substring(0, i);
				}
			}
		 }
		 return strs[0].substring(0, i);
	 }
}

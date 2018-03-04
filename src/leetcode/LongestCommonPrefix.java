package leetcode;

public class LongestCommonPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] strings = {"bab","bcc" };
		System.out.println(new StringBuffer().toString());
	}

	 public String longestCommonPrefix(String[] strs) {
		 if(strs.length == 0) {
			 return null;
		 }
	        int shortest = 0;
	       
	        for(int i=0; i<strs.length; i++) {
	        	shortest = strs[shortest].length() < strs[i].length()? shortest :i;
	        }
	        String shortest_str = strs[shortest];
	        StringBuilder stringBuilder = new StringBuilder();
	        for(int i=0; i<shortest_str.length(); i++) {
	        	stringBuilder.append(shortest_str.charAt(i));
	        	String tmp = stringBuilder.toString();
	        	for( int j=0; j<strs.length; j++) {
	        		if(strs[i].startsWith(tmp)) {
	        			
	        		}else {
	        			stringBuilder.deleteCharAt(stringBuilder.length()-1);
						return stringBuilder.toString();
					}
	        	}
	        }
	        return stringBuilder.toString();
	    }
}

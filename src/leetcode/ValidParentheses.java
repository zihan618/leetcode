package leetcode;

import java.util.LinkedList;

public class ValidParentheses {

	public static void main(String[] args) {
		System.out.println(new ValidParentheses().isValid("([])"));
	}
	/**
	 * use stack 
	 * @param s
	 * @return
	 */
	 public boolean isValid(String s) {
	        LinkedList<Integer> stack = new LinkedList<>();
	        for(int i=0; i<s.length(); i++) {
	        	if(!stack.isEmpty() && s.charAt(i)==')' && s.charAt(stack.getFirst())=='(') {
	        		stack.pop();
	        	}else if (!stack.isEmpty() &&  s.charAt(i)==']' && s.charAt(stack.getFirst())=='[') {
	        		stack.pop();
				}else if (!stack.isEmpty() && s.charAt(i)=='}' && s.charAt(stack.getFirst())=='{') {
					stack.pop();
				}else {
					stack .push(i);
				}
	        }
	       return stack.isEmpty();
	    }

}

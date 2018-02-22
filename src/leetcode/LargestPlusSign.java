package leetcode;

import java.util.HashSet;

public class LargestPlusSign {

	public static void main(String[] args) {
			
	}

	 public int orderOfLargestPlusSign(int N, int[][] mines) {
	        int res = 0;
	        HashSet<Integer[]> hashSet = new HashSet<>();
	        for( int i=0; i<mines.length; i++) {
	        	Integer[] objects = new Integer[2];
	        	objects[0]=mines[i][0];
	        	objects[1]=mines[i][1];
	        	hashSet.add(objects);
	        } 
	        Integer[] tmp = new Integer[2];
	        for( int i=0; i<N; i++) {
	        	for( int j=0; j<N; j++) {
	        		//tmp[0]=
	        		//if(hashSet.contains());
	        	}
	        }
	        return 0;
	    }
}

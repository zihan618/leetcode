package leetcode;

import java.util.stream.Stream;

public class LargestPlusSign {

	public static void main(String[] args) {
		System.out.println( new LargestPlusSign().orderOfLargestPlusSign(5, new int[][] {{4,2}}));
	}

	/**
	 * ±©Á¦½â·¨
	 * 
	 * @param N
	 * @param mines
	 * @return
	 */
	public int orderOfLargestPlusSign(int N, int[][] mines) {
		boolean[][] matrix = new boolean[N][N];
		for(int i=0; i<mines.length; i++) {
			matrix[mines[i][0]][mines[i][1]] = true;
		}
		int res = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int min = Stream.of(i, j, N-i-1, N-j-1).min(Integer::compare).get();
				for(int k=0; k<min+1; k++) {
					if(!matrix[i+k][j] && !matrix[i-k][j] &&!matrix[i][j-k] &&!matrix[i][j+k]) {
						res = Math.max(res, k+1);
					}
				}
			}
		}
		return res;
	}
}

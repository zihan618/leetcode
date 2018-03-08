package leetcode;

import java.util.stream.Stream;

public class LargestPlusSign {

	public static void main(String[] args) {
		System.out.println( new LargestPlusSign().orderOfLargestPlusSign2(5, new int[][] {{4,2}}));
	}

	/**
	 * 暴力解法 每个点求出以它为中心的最大十字半径  复杂度 n^3
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
					}else {
						break;
					}
				}
			}
		}
		return res;
	}
	/**
	 * 比较巧妙的一个方法  除去不可能的情况之后 就是十字的点了
	 * @param N
	 * @param mines
	 * @return
	 */
	public int orderOfLargestPlusSign2(int N, int[][] mines) {
		boolean[][] matrix = new boolean[N][N];
		int left_pos = N*N;
		for(int i=0; i<mines.length; i++) {
			matrix[mines[i][0]][mines[i][1]] = true;
			left_pos--;
		}
		int res = 0;
		for(int i=1; left_pos!=0; i++) {
			for(int j=0; j<N; j++) {
				if(!matrix[i-1][j]) {
					matrix[i-1][j] = true;
					left_pos--;
				}
				if(!matrix[N-i][j]) {
					matrix[N-i][j] = true;
					left_pos--;
				}
				if(!matrix[j][i-1]) {
					matrix[j][i-1] = true;
					left_pos--;
				}
				if(!matrix[j][N-i]) {
					matrix[j][N-i] = true;
					left_pos--;
				}
			}
			for(int j=0; j<mines.length; j++) {
				if(i <= mines[j][0] && !matrix[mines[j][0]-(i)][mines[j][1]]) {
					matrix[mines[j][0]-(i)][mines[j][1]] = true;
					left_pos--;
				}
				if(N-i-1 >= mines[j][0] && !matrix[mines[j][0]+(i)][mines[j][1]]) {
					matrix[mines[j][0]+(i)][mines[j][1]] = true;
					left_pos--;
				}
				if(i <= mines[j][1] && !matrix[mines[j][0]][mines[j][1]-(i)]) {
					matrix[mines[j][0]][mines[j][1]-i] = true;
					left_pos--;
				}
				if(N-i-1 >= mines[j][1] && !matrix[mines[j][0]][mines[j][1]+(i)]) {
					matrix[mines[j][0]][mines[j][1]+i] = true;
					left_pos--;
				}
			}
			res = i;
			
		}
		return res;
		
	}
}

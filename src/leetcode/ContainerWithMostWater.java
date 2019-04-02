package leetcode;

public class ContainerWithMostWater {

	public static void main(String[] args) {

	}

	/**
	 * 暴力解法
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int res = 0;
		int temp = 0;
		for (int i = 0; i < height.length - 1; i++) {
			for (int j = i + 1; j < height.length; j++) {
				temp = Math.min(height[i], height[j]) * (j - i);
				res = res > temp ? res : temp;
			}
		}
		return res;
	}
	
	public int maxArea2(int[] height) {
		int i=0, j=height.length;
		int res = 0, temp = 0;
		while(i<j) {
			temp = (j-i) * Math.min(height[i], height[j]);
			res = Math.max(res, temp);
			if( height[i] < height[j]) {
				i++;
			}else {
				j--;
			}
		}
		return res;
	}

}

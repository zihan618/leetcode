package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {

	public static void main(String[] args) {
		int[] ints = { -1, 0, 1, 2, -1, -4 };
		System.out.println(new Sum3().threeSum(ints));
	}

	/**
	 * 主要问题在于 如何判断相同数字带来的答案重复问题
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.parallelSort(nums);
		List<List<Integer>> res = new ArrayList<>();
		int j = 0, k = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			if (i>0 && nums[i] == nums[i - 1]) {
				continue;
			} else {
				j = i + 1;
				k = nums.length - 1;
				while (j < k) {
					int sum = nums[i] + nums[j] + nums[k];
					if (sum == 0) {
						List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
						res.add(list);
						while (j < k && nums[j] == nums[j + 1]) {
							j++;
						}
						j++;
						while (k > j && nums[k] == nums[k - 1]) {
							k--;
						}
						k--;
					} else if (sum < 0) {
						j++;
					} else {
						k--;
					}
				}
			}

		}
		return res;
	}
	/**
	 * 量子算法
	 */
}

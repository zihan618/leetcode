package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum4 {

	public static void main(String[] args) {
		int[] ints = new int[] {1,0,-1,0,-2,2};
		System.out.println(new Sum4().fourSum(ints, 0));
	}
/**
 * 当有重复的数字时 应该保持只是在最开始的位置考虑一下，后面的相同数字位置就跳过了
 * @param nums
 * @param target
 * @return
 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < nums.length - 3; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			for (int j = i + 1; j < nums.length - 2; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				int k = j + 1, l = nums.length - 1;
				int needed = target - nums[i] - nums[j];
				while (k < l) {
					int had = nums[k] + nums[l];
					if (had < needed) {
						k++;
					} else if (had > needed) {
						l--;
					} else {
						ArrayList<Integer> arrayList = new ArrayList<>();
						arrayList.add(nums[i]);
						arrayList.add(nums[j]);
						arrayList.add(nums[k]);
						arrayList.add(nums[l]);
						res.add(arrayList);
						while (k<l && nums[k]==nums[k+1]) {
							k++;
						}
						k++;
						while (k<l && nums[l]==nums[l-1]) {
							l--;
						}
						l--;
					}
					
				}

			}
		}
		return res;
	}
}

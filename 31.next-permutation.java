import java.util.Arrays;

import static java.util.Arrays.sort;

/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 *
 * https://leetcode.com/problems/next-permutation/description/
 *
 * algorithms
 * Medium (33.05%)
 * Total Accepted:    433.4K
 * Total Submissions: 1.3M
 * Testcase Example:  '[1,2,3]'
 *
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such an arrangement is not possible, it must rearrange it as the lowest
 * possible order (i.e., sorted in ascending order).
 * 
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 *
 *
 * 
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 * Input: nums = [1]
 * Output: [1]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 * 
 * 
 */
class Solution {
    public void nextPermutation(int[] nums) {
//    	int right = nums.length - 1;
		for (int left = nums.length - 1; left >= 0; left--)
		{
			int pos = nums.length - 1;
			int right = nums.length - 1;
			boolean flag = false;
			int maxV = Integer.MAX_VALUE;
			for (right = nums.length - 1; right > left; right--) {
				if (nums[right] > nums[left] && nums[right] <= maxV) {
					pos = right;
					flag = true;
					maxV = nums[pos];
				}
			}
			if (flag)
			{
				swap(nums, left, pos);
				if (left != nums.length - 1)
				{
					sort(nums, left + 1, nums.length);
				}
				return;
			}
		}
		sort(nums);
    }

	private void swap(int[] nums, int left, int right)
	{
		int cache = nums[left];
		nums[left] = nums[right];
		nums[right] = cache;
	}

	public static void main(String[] args)
	{
		Solution a = new Solution();
		int[] input = new int[] {2, 3, 1};
		a.nextPermutation(input);
		System.out.println(a);
//		Arrays.stream(input).forEach(i -> {
//			System.out.println();
//				}
//		);
	}
}

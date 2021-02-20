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
		final int end = nums.length - 1;
		int right = end;
		int left = end;

		if (nums.length == 1) {
			return;
		}

		while (right > 0) {
			if (nums[right - 1] < nums[right]) {
				right--;
				break;
			}
			right--;
		}

		//if the whole list is reversed
		if (right == -1) {
			reverseOrder(nums, 0, end);
			return;
		}

		left = right + 1;

		while (left < end)
		{
			if (nums[right] >= nums[left + 1] && nums[right] < nums[left])
			{
				swap(nums, right, left);
				break;
			}
			left++;
		}

		//last pair
		if (left == end)
		{
			if (nums[right] < nums[left])
			{
				swap(nums, right, left);
			}
			else
			{
				reverseOrder(nums, 0, end);
				return;
			}
		}

		reverseOrder(nums, right + 1, nums.length - 1);
    }

	private void reverseOrder(int[] nums, int l, int r)
	{
		while (l != r) {
			swap(nums, l, r);
			if (l + 1 == r) {
				break;
			}
			l++;
			r--;
		}
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
//		int[] input = new int[] {2, 3, 1};
//		int[] input = new int[] {1, 2, 3};
		int[] input = new int[] {1, 5, 1};
		a.nextPermutation(input);
		System.out.println(a);
//		int i;
//		for (i = 5; i > 0 ; i--)
//		{
//			System.out.println(i);
//		}
//		System.out.println(i);
//		Arrays.stream(input).forEach(i -> {
//			System.out.println();
//				}
//		);
	}
}

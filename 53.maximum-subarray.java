/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 *
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (47.26%)
 * Total Accepted:    1.3M
 * Total Submissions: 2.8M
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 * 
 * 
 * Example 1:
 * 
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1]
 * Output: 1
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [0]
 * Output: 0
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: nums = [-1]
 * Output: -1
 * 
 * 
 * Example 5:
 * 
 * 
 * Input: nums = [-100000]
 * Output: -100000
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 3 * 10^4
 * -10^5 <= nums[i] <= 10^5
 * 
 * 
 * 
 * Follow up: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 */
class SubArray{
    public static int maxSubArray(int[] nums) {
        return maxSum(nums, 0, nums.length -1);
    }

    private static int maxSum(int[] arr, int leftIndex, int rightIndex)
    {
        if (leftIndex == rightIndex) {
            return arr[leftIndex];
        }
        int leftSum = maxSum(arr, leftIndex, (leftIndex + rightIndex) / 2);
        int rightSum = maxSum(arr, (leftIndex + rightIndex) / 2 + 1, rightIndex);
        int leftContinuousSum = continuousSum(arr, (leftIndex + rightIndex) / 2, leftIndex, false);
        int rightContinuousSum = continuousSum(arr, (leftIndex + rightIndex) / 2 + 1, rightIndex, true);
        int midSum = leftContinuousSum + rightContinuousSum;
        return Math.max(Math.max(leftSum, rightSum), midSum);
    }

    /**
     *
     * @param arr
     * @param leftIndex
     * @param rightIndex
     * @param isIncrease indicate whether the continuous sum counting is increase (right part)
     *                   or decrease (left part)
     * @return
     */
    private static int continuousSum(int[] arr, int leftIndex, int rightIndex, Boolean isIncrease)
    {
        if (leftIndex == rightIndex) {
            return arr[leftIndex];
        }
        int currMax = Integer.MIN_VALUE;
        int currSum = 0;
        int step = isIncrease ? 1 : -1;
        for (int i = leftIndex; i != rightIndex + step; i += step) {
            currSum += arr[i];
            currMax = Math.max(currMax, currSum);
        }
        return currMax;
    }

    public static void main(String[] args)
    {
//        int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        int[] nums = {1};
        System.out.println(maxSubArray(nums));
        int[] nums1 = {0};
        System.out.println(maxSubArray(nums1));
        int[] nums2 = {-1};
        System.out.println(maxSubArray(nums2));
    }
}

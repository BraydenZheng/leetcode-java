/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (42.71%)
 * Total Accepted:    722.2K
 * Total Submissions: 1.7M
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array of distinct integers and a target value, return the
 * index if the target is found. If not, return the index where it would be if
 * it were inserted in order.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * 
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 * Example 4:
 * Input: nums = [1,3,5,6], target = 0
 * Output: 0
 * Example 5:
 * Input: nums = [1], target = 0
 * Output: 0
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums contains distinct values sorted in ascending order.
 * -10^4 <= target <= 10^4
 * 
 * 
 */
class searchInsert {
    public int searchInsert(int[] nums, int target) {
//        Arrays.binarySearch();
        int index = (nums.length - 1) / 2;
        int median = nums[index];
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (target < median) {
                right = index - 1;
            }
            else if (target > median) {
                left = index + 1;
            }
            else {
                target = median;
                return index;
            }
            index = (left + right) / 2;
            median = nums[index];
        }
            return left;
    }

    public static void main(String[] args) {
        BrickHit solution = new BrickHit();
        int[] nums = {1, 3, 5, 6};
//        int result = solution.searchInsert(nums, 4);
//        System.out.println(result);
    }
}

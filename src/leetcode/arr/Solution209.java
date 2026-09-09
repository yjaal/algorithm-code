package leetcode.arr;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * <p>
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * <p>
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 */
public class Solution209 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println(new Solution209().solution(nums, target));
    }
    public int solution(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0, sum = 0;
        // 注意：j=0时还没有任何元素加入到和计算，最后只有j=len时才算将所有元素都计算了一遍
        while (j <= len) {
            System.out.println("i:" + i + ",j:" + j + ",sum:" + sum + ",min:" + min);
            if (sum >= target) {
                min = Math.min(j - i, min);
                sum -= nums[i];
                i++;
            } else {
                if(j == len) break;
                sum += nums[j];
                j++;
            }
        }
        return min;
    }

    /**
     * 上面方式有点别扭，这里我们先算和，然后再收缩，这样理解起来比较简单
     */
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int min = Integer.MAX_VALUE;
        int i = 0, sum = 0;
        for (int j = 0; j < len; j++) {
            sum += nums[j];  // 先把当前元素加入
            // 当满足条件时，不断收缩左边界
            while (sum >= target) {
                min = Math.min(j - i + 1, min);
                sum -= nums[i];
                i++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}

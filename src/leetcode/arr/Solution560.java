package leetcode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为 K 的子数组
 * <p>
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * <p>
 * 子数组是数组中元素的连续非空序列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class Solution560 {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        // 关键就在这里，保存了前缀和和次数
        Map<Integer, Integer> preMap = new HashMap<>();
        preMap.put(0, 1);
        int preSum = 0;
        for (int num : nums) {
            preSum += num;
            if (preMap.containsKey(preSum - k)) {
                count += preMap.get(preSum - k);
            }
            preMap.put(preSum, preMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}

package leetcode.arr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数组中重复的数据
 * 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，且每个整数出现 最多两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间（不包括存储输出所需的空间）的算法解决此问题。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [4,3,2,7,8,2,3,1]
 * 输出：[2,3]
 * <p>
 * 示例 2：
 * 输入：nums = [1,1,2]
 * 输出：[1]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[]
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= n <= 105
 * 1 <= nums[i] <= n
 * nums 中的每个元素出现 一次 或 两次
 */
public class Solution442 {

    /**
     * 暴力解法
     */
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            Integer cnt = map.get(num);
            if (cnt == null) {
                map.put(num, 1);
            } else {
                res.add(num);
            }
        }
        return res;
    }


    /**
     * 负数标记法，也就是原地hash
     */
    public List<Integer> findDuplicates1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 这里一定要注意，我们使用下标 i 表示 数字 i+1
            int idx = Math.abs(nums[i]) - 1;
            if (nums[idx] < 0) {
                // 如果是负数，表示出现过
                res.add(idx + 1);
            } else {
                nums[idx] = -Math.abs(nums[idx]);
            }
        }
        return res;
    }

}

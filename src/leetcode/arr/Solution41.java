package leetcode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 缺失的第一个正数
 * <p>
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 * 解释：范围 [1,2] 中的数字都在数组中。
 * <p>
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 解释：1 在数组中，但 2 没有。
 * <p>
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 * 解释：最小的正数 1 没有出现。
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 */
public class Solution41 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, -1, 1};
        System.out.println(new Solution41().solution1(nums));
    }

    /**
     * 暴力解法
     */
    public int solution(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        int i = 1;
        for (; i <= nums.length; i++) {
            if (null == map.get(i)) {
                break;
            }
        }
        return i;
    }

    /**
     * 这里的核心要点在于：我们要将数组中的元素放置到应该存在的位置，比如数组[1,3,2]，如果转换后变成[1,2,3]，那第一个缺失的正整数就是4
     * 也就是说 i 位置上的元素应该是 i + 1，如果nums[i] != nums[nums[i] - 1]，那么就进行交换。这就是原地hash方法
     * 最后再次寻找第一个缺失的正整数
     */
    public int solution1(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 这里必须要使用循环，因为交换后当前位置的元素可能不满足条件，需要继续交换
            while (nums[i] >= 1 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }
        for (int num : nums) {
            System.out.print(num + ",");
        }
        System.out.println();
        int i = 0;
        for (; i < len; i++) {
            if (nums[i] != (i + 1)) {
                return i + 1;
            }
        }
        return len + 1;
    }


    /**
     * 负数标记法
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 第一步：把所有 <=0 的数改成 n+1（无效值）
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        // 第二步：标记存在的数字
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                // 把索引 num-1 位置的数标记为负数（表示 num 存在）
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // 第三步：找第一个正数
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }
}

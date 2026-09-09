package leetcode.arr;

/**
 * 除自身以外数组的乘积
 * <p>
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除了 nums[i] 之外其余各元素的乘积 。
 * <p>
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * <p>
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 * <p>
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * 输入 保证 数组 answer[i] 在  32 位 整数范围内
 * <p>
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 */
public class Solution238 {

    /**
     * 这里的关键点就是不能用除法，所以可以构造一个前缀积数组和一个后缀积数组，那么除开某个位置的元素的积就是前缀积 * 后缀积
     * 也就是 res[j] = pre[j - 1] * pos[j + 1]，这是最简单办法。
     */
    public int[] solution1(int[] nums) {
        int len = nums.length;
        int[] pre = new int[len];
        pre[0] = 1;// 第一个元素左边没有元素，乘积为1
        for (int i = 1; i < len; i++) {
            pre[i] = nums[i - 1] * pre[i - 1];
        }

        int[] pos = new int[len];
        pos[len - 1] = 1;// 最后一个元素右边没有元素，乘积为1
        for (int i = len - 1; i >= 0; i--) {
            pos[i] = nums[i + 1] * pos[i + 1];

        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = pre[i] * pos[i];
        }
        return res;
    }

    public int[] solution2(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        for (int i = 1; i < len; i++) {
            res[i] = nums[i - 1] * res[i - 1];
        }

        int right = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];

        }
        return res;
    }

}

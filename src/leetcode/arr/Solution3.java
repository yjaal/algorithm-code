package leetcode.arr;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * <p>
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Solution3 {

    public static void main(String[] args) {
        System.out.println(new Solution3().solution("pwewkew"));
    }

    public int solution(String s) {
        int count = Integer.MIN_VALUE;
        Map<String, Integer> charMap = new HashMap<>();
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            String curChar = s.charAt(j) + "";
            Integer mount = charMap.get(curChar);
            if (mount != null) {
                // 如果已经存在，那么存在两种情况
                if (j - mount == 1) {
                    // 两个重复的字符刚好紧挨着，那么直接清空map，i直接跳转到j，相当于重新统计一个新的字符串
                    charMap = new HashMap<>();
                    i = j;
                } else {
                    // 如果重复字符不是紧挨着，那么就需要将i直接跳到重复字符的后面一个位置，然后将
                    // 之前的字符缓存清空
                    for (int k = i; k < mount + 1; k++) {
                        charMap.remove(s.charAt(k) + "");
                    }
                    i = mount + 1;
                }
            } else {
                // 在map中不存在那么久一直计算count
                count = Math.max(j - i + 1, count);
            }
            // 记录了字符和字符位置
            charMap.put(curChar, j);
        }
        return count == Integer.MIN_VALUE ? 0 : count;
    }


    /**
     * 其实思路差不多，只不过更简洁
     */
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // 只包含ASCII字符，最大127
        int[] index = new int[128];
        for (int j = 0, i = 0; j < n; j++) {
            // 这里决定i的位置，
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            // 这里记录每个字符位置的后面一个位置，这样就可以确定如果重复时重置i时应该给什么值
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}

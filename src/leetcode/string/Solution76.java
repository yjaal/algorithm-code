package leetcode.string;

/**
 * 最小覆盖子串
 * <p>
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * <p>
 * 测试用例保证答案唯一。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s 和 t 由英文字母组成
 */
public class Solution76 {

    public String minWindow(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        // 表示需要匹配的字符长度
        int count = tLen;
        int minLeft = 0;
        // 最小长度使用一个大值初始化
        int minLen = sLen + 1;
        int[] map = new int[128];
        int left = 0;
        for (int i = 0; i < tLen; i++) {
            // 将t中字符使用map记录出现次数
            map[t.charAt(i)]++;
        }

        // 移动右边窗口
        for (int right = 0; right < sLen; right++) {
            // s 中的字符次数减去 1
            char c = s.charAt(right);
            // 表示欠账
            map[c]--;

            // 如果此时该字符出现次数还是不小于0，表示在t中存在，此时
            // 还需要匹配的字符减去1
            if (map[c] >= 0) {
                count--;
            }
            // 当所有字符都匹配时，则需要移动左边窗口了
            while (count == 0) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minLeft = left;
                }

                char leftC = s.charAt(left);
                // 还款
                map[leftC]++;
                if (map[leftC] > 0) {
                    // 还款后还大于0，表示是t中的字符
                    // 将一个t中的字符移动出去了，此时还需要匹配的字符就增加了
                    // 这里注意：如果是某个t中的字符在s中同时出现两次，那么在最初
                    // 欠款的时候就会欠多次，在到底最后一次移出之前，其值是不会
                    // 大于0的。
                    // 比如 s = "AAOBECODEBANC", t = "ABC"
                    // 第一次左移A计数为0，因为最初变成了-1，在第二次
                    // 才会变成1，此时表示A已经不在窗口范围了
                    count++;
                }
                left++;
            }
        }
        // 因为minLen初始化是一个较大的值
        return minLen > s.length() ?
                "" : s.substring(minLeft, minLeft + minLen);
    }
}

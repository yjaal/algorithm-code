package leetcode.string;

/**
 * 找出字符串中第一个匹配项的下标
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 * <p>
 * <p>
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 */
public class Solution28 {

    public static void main(String[] args) {
        int idx = new Solution28().strStr("sadbutsad", "sad");
        System.out.println(idx);
    }

    public int strStr(String haystack, String needle) {
        if (!haystack.contains(needle)) {
            return -1;
        }
        return haystack.indexOf(needle);
    }

    public int strStr1(String haystack, String needle) {
        int[] lsp = lsp(needle);
        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            // 不匹配时，根据 lsp 数组回退 j
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = lsp[j - 1];
            }
            // 匹配时，j 前进
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 完全匹配，返回起始下标
            if (j == needle.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int[] lsp(String s) {
        int[] lsp = new int[s.length()];
        int len = 0, i = 1;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lsp[i] = len;
                i++;
            } else {
                if (len > 0) {
                    // 注意，这里i不动
                    len = lsp[len - 1];
                } else {
                    lsp[i] = 0;
                    i++;
                }
            }
        }
        return lsp;
    }
}

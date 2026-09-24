package leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 找到字符串中所有字母异位词
 * <p>
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * 示例 2:
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class Solution438 {

    public static void main(String[] args) {
        List<Integer> resList = new Solution438().findAnagrams("abaacbabc", "abc");
        System.out.println(resList);
    }

    public List<Integer> findAnagrams(String s, String p) {
        int pLen = p.length();
        int left = 0, right = 0;

        // 记录原始p中每个字符出现的次数，也可以使用数组模拟map进行记录
        Map<Character, Integer> sourceMap = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            sourceMap.put(p.charAt(i), sourceMap.getOrDefault(p.charAt(i), 0) + 1);
        }

        List<Integer> resList = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char rightC = s.charAt(right);
            if (!p.contains(rightC + "")) {
                // 不包含
                right = right + 1;
                left = right;
                // 如果用数组，则可以再new一个数组
                map.clear();
                continue;
            } else {
                map.merge(rightC, 1, Integer::sum);
                while (map.get(rightC) > sourceMap.get(rightC)) {
                    // 如果超过p中字符出现的次数，就左移动
                    char leftC = s.charAt(left);
                    map.put(leftC, map.get(leftC) - 1);
                    left++;
                }
            }
            if (right - left + 1 == pLen) {
                resList.add(left);
                char leftC = s.charAt(left);
                map.put(leftC, map.get(leftC) - 1);
                left++;
            }
            right++;
        }
        return resList;
    }


}

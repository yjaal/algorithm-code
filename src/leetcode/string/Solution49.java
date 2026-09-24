package leetcode.string;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * <p>
 * <p>
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * <p>
 * 解释：
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 * <p>
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 * <p>
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 * <p>
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Solution49 {

    public static void main(String[] args) {
        List<List<String>> lists = new Solution49().groupAnagrams(new String[]{"stop", "pots", "reed", "", "tops", "deer", "opts", ""});
        System.out.println(lists);
    }

    /**
     * 暴力解法耗时较高
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        final String FLAG = "-1";
        List<List<String>> res = new ArrayList<>();
        if (strs.length <= 1) {
            res.add(List.of(strs));
            return res;
        }

        Map<String, Integer[]> map = new HashMap<>();
        for (String str : strs) {
            Integer[] times = new Integer[26];
            for (int j = 0; j < str.length(); j++) {
                int idx = str.charAt(j) - 'a';
                if (Objects.isNull(times[idx])) {
                    times[idx] = 1;
                } else {
                    times[idx]++;
                }
            }
            map.put(str, times);
        }
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (Objects.equals(str, FLAG)) {
                continue;
            }
            List<String> tmpRes = new ArrayList<>();
            tmpRes.add(str);
            for (int j = 1; j < strs.length; j++) {
                if (Objects.equals(strs[j], FLAG) || i == j) {
                    continue;
                }
                if (Objects.equals(strs[i], "") && Objects.equals(strs[j], "")) {
                    tmpRes.add(strs[j]);
                    strs[j] = FLAG;
                    continue;
                }
                boolean check = check(str, strs[j], map);
                if (check) {
                    tmpRes.add(strs[j]);
                    strs[j] = FLAG;
                }
            }
            strs[i] = FLAG;
            res.add(tmpRes);
        }
        return res;
    }

    private boolean check(String s1, String s2, Map<String, Integer[]> map) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.contains(s2)) {
            return true;
        }
        Integer[] s1Arr = map.get(s1);
        Integer[] s2Arr = map.get(s2);
        for (int i = 0; i < s1.length(); i++) {
            int idx = s1.charAt(i) - 'a';
            Integer s1Cnt = s1Arr[idx];
            Integer s2Cnt = s2Arr[idx];
            if (Objects.isNull(s1Cnt) || Objects.isNull(s2Cnt) || !Objects.equals(s1Cnt, s2Cnt)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 排序法，排序后异位字符串一定相同
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length <= 1) {
            res.add(List.of(strs));
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 计数法，其实可以将每个字符出现的次数保存在数组中，那么最后数组下标本身是有序的，这样就可以比较了
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length <= 1) {
            res.add(List.of(strs));
            return res;
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] cnts = new int[26];
            for (char c : str.toCharArray()) {
                cnts[c - 'a']++;
            }
            String key = String.join("#", Arrays.toString(cnts));
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }
}

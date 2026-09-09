Java中String是不可变对象，频繁修改时务必使用`StringBuilder`或`StringBuffer`。

*   **核心场景**：字符串反转、子串匹配、回文串判断、字符统计、字符串编码转换。
*   **解题套路**：
    1.  **双指针相向而行**：反转字符串或判断回文串的标准解法，一个指针从左，一个指针从右，向中间移动并比较。
    2.  **中心扩散法**：专门用于寻找最长回文子串。遍历每个字符（及两个字符间的位置）作为中心，向两边扩散查找最长回文。
    3.  **KMP算法**：解决字符串匹配问题（如实现`strStr()`）。核心是理解`next`数组（前缀表），它记录了模式串的最长公共前后缀长度，用于在匹配失败时高效地回退，避免从头开始。虽然不常要求手写，但其思想（利用已匹配信息）非常关键。

*   **推荐练习题**：
    *   [344. 反转字符串](https://leetcode.cn/problems/reverse-string/) (双指针基础)
    *   [5. 最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/) (中心扩散法经典题)
    *   [28. 找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/) (KMP算法入门)
    *   [14. 最长公共前缀](https://leetcode.cn/problems/longest-common-prefix/) (字符串基础操作)
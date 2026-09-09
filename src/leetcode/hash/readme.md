哈希表的核心是用空间换时间，提供接近 O(1) 的查找、插入和删除操作。

*   **核心场景**：快速判断一个元素是否出现过、统计元素频率、建立两数或多元素之间的映射关系、缓存数据。
*   **解题套路**：
    1.  **空间换时间**：当遇到暴力解法需要两层循环（O(n²)）查找时，首先想到用`HashSet`或`HashMap`将查找的时间复杂度降至O(1)。
    2.  **巧设Key值**：
        *   **两数之和**：`key`是数组元素的值，`value`是其索引。
        *   **字母异位词**：`key`可以是排序后的字符串，也可以是长度为26的字符计数数组。
    3.  **哈希表与滑动窗口**：在滑动窗口中，用`HashMap`动态记录窗口内字符出现的次数，是解决“最长无重复子串”等问题的核心技巧。
    4.  **Java实现注意**：充分利用Java集合框架提供的`HashMap`和`HashSet`，它们是现成的、经过高度优化的数据结构。

*   **推荐练习题**：
    *   [1. 两数之和](https://leetcode.cn/problems/two-sum/) (最经典的哈希表应用，必刷)
    *   [3. 无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/) (哈希表+滑动窗口)
    *   [49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/) (自定义Key的典型)
    *   [560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/) (前缀和+哈希表，优化思想的重要体现)
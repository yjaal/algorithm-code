
## 基础处理与性能避坑
### 核心套路：
- 转字符数组：算法题起手式通常是 s.toCharArray()。因为 String 不可变，且 charAt() 在循环中有边界检查开销，转为 char[] 后不仅能原地修改，访问速度也大幅提升。
- 拼接陷阱：在循环中拼接字符串绝对不能用 +（会导致 O(n^2) 的时间复杂度和大量临时对象），必须使用 StringBuilder（单线程）或 StringBuffer（多线程）。
- 高频API避坑：尽量用 indexOf() + substring() 组合替代 split()，因为 split() 底层依赖正则编译，高频调用开销极大。

### 重点题目：
- 151：反转字符串中的单词（考察 trim 去空格、整体反转、局部反转的组合）
- 58：最后一个单词的长度（考察基础遍历与边界处理）

## 双指针与滑动窗口（最核心套路）
### 核心套路：
- 相向双指针：常用于字符串反转、判断回文串。遇到非字母数字字符时，通过 Character.isLetterOrDigit() 配合指针跳过。
- 同向双指针：常用于原地去除多余空格、移除特定字符。
- 滑动窗口：解决“子串”问题的万能钥匙。模板是维护一个窗口，右指针扩张，左指针根据条件收缩，并用 HashMap 或 int[128] 记录窗口内字符状态。

### 重点题目：
- 125：验证回文串（相向双指针）
- 3：无重复字符的最长子串（滑动窗口经典题）
- 76：最小覆盖子串（**滑动窗口进阶）**，重点题目
- 438：找到字符串中所有字母异位词（滑动窗口 + 频次统计）

## 哈希表与数组计数（空间换时间）
### 核心套路：
- 频率统计：如果题目只涉及小写字母，直接用 int[26] 数组代替 HashMap，不仅代码简洁，还能避免哈希冲突和装箱拆箱开销。
- 映射与查找：利用 HashMap 实现 O(1) 的字符查找，常用于判断两个字符串是否互为变形词（异位词）。

### 重点题目：
- 242：有效的字母异位词（数组计数基础）
- 387：字符串中的第一个唯一字符（计数 + 遍历）
- 49：字母异位词分组（哈希表进阶）

## 字符串匹配与字典树
### 核心套路：
- KMP算法：当数据规模较大时，暴力匹配会超时。KMP的核心是构建 next 数组（前缀表），利用已匹配的信息避免主串指针回溯，时间复杂度严格为 O(n+m)。
- Trie树（前缀树）：专门解决前缀匹配、自动补全、单词搜索等问题，核心是节点包含子节点映射和一个 isEnd 标记。
### 重点题目：
- 28：找出字符串中第一个匹配项的下标（KMP算法经典应用）
- 208：实现 Trie (前缀树)（字典树基础模板）
- 212：单词搜索 II（Trie + DFS回溯）

## KMP算法
参考：https://zhuanlan.zhihu.com/p/1928405324878033399
haystack：表示原始字符串
needle：表示需要匹配的目标字符串
一般暴力解法就是不断滑动needle和haystack进行比较，但是这样效率比较低，特别是
陪到aaaaaabcsd, abcs的情况。
于是这里用到了KMP算法，算法的核心在于构建LSP（最长前后缀子串）

### 怎样移动才能提高效率
haystack:ababdab
needle: ababc
第一次碰到不匹配的字符时，我们需要将needle往后滑动，那滑动到哪里就很关键了，暴力
解法中是滑动一位，这显然比较慢。KMP算法中使用LSP来记录滑动到哪里。具体LSP可以
参考上面文章，我们只需要知道
ababc的LSP数组是[0,0,0,1,2]
于是上面例子中第一次滑动应该变成这样

```text
    i
ababdab
  ababc
    j
```
为什么要这样滑动呢？因为说白了，因为needle在往后移动，其实就是要比较haystack的
最长后缀和needle的最长前缀，而haystack[0,i-1]==needle[0,j-1]的，最终其实
就是找needle在最后一个不匹配位置的最长前后缀子串。相当于不用每次都从头开始比较，
而是将一部分具有相同前后缀子串的部分去掉不再检查。

因为现在已经不匹配了，所以haystack的前面部分不能要了，而needle后面部分还需要
检查匹配。

### LSP
就是构建最长相等前后缀子串数组
```java
int[] buildLPS(String needle) {
    int[] lps = new int[needle.length()];
    int len = 0; // len 是当前最长公共前后缀的长度
    int i = 1; // i 是当前遍历到的位置
    while (i < needle.length()) {
        // needle[i] 与 needle[len] 匹配，说明我们找到了一个更长的公共前后缀
        if (needle.charAt(i) == needle.charAt(len)) {
            len++;
            lps[i] = len;
            i++;
        } else {
            if (len > 0) {
                len = lps[len - 1]; // 关键步骤：len 回溯
            } else {
                lps[i] = 0; // len 已经是 0，无法再缩短
                i++;
            }
        }
    }
    return lps;
}
```
这里有两点需要理解：
1、相等时就扩展，因为如果一直相等，表示子串一直在增加，可以理解为自己跟自己比较。
2、其次，如果不相等，那么下一次要从哪里开始比较呢？此时就需要到lsp中找，也就是len位置前
子字符串中最大相等前后缀子串长度（ABABC，当C不匹配时就为2），于是我们就从这个位置
再次进行比较（其实是一种回溯len=2）


## Trie字典树
就是构建一棵树，root节点为空。每个节点需要两样东西：
- 子节点映射：可以用长度为 26 的数组（限定小写字母时），也可以用 `HashMap<Character, TrieNode>`（通用字符集）
- 结束标记：isEnd，标记从根到当前节点是否构成一个完整单词

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // 插入单词
    public void insert(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.isEnd = true;  // 标记单词结尾
    }

    // 精确查找：路径完整 + 结尾标记
    public boolean search(String word) {
        TrieNode node = findPrefix(word);
        return node != null && node.isEnd;
    }

    // 前缀查找：只需路径完整
    public boolean startsWith(String prefix) {
        return findPrefix(prefix) != null;
    }

    // 辅助方法：沿路径查找，返回前缀末尾节点
    private TrieNode findPrefix(String prefix) {
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (node.children[idx] == null) {
                return null;  // 路径断了，不存在
            }
            node = node.children[idx];
        }
        return node;
    }
}
```







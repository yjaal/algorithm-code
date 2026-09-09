在Java中，推荐使用`Deque`（双端队列）代替`Stack`，因为`Deque`功能更强大，用法更规范。

*   **核心场景**：括号匹配、表达式求值、维护单调序列、实现树的深度优先搜索(DFS)和广度优先搜索(BFS)、设计特殊数据结构。
*   **解题套路**：
    1.  **括号匹配**：遇到左括号就压入栈，遇到右括号时，弹出栈顶元素并检查是否匹配。若栈为空或不匹配，则字符串无效。
    2.  **单调栈（详见后文）**。
    3.  **用栈模拟队列 / 用队列模拟栈**：考察对两种数据结构底层特性的理解。
    4.  **优先队列 (PriorityQueue)**：即堆。常用于求解第K大/小的元素、合并K个有序链表等TopK问题。

*   **推荐练习题**：
    *   [20. 有效的括号](https://leetcode.cn/problems/valid-parentheses/) (栈的经典应用，必刷)
    *   [232. 用栈实现队列](https://leetcode.cn/problems/implement-queue-using-stacks/) (必刷)
    *   [155. 最小栈](https://leetcode.cn/problems/min-stack/) (辅助栈应用)
    *   [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/) (优先队列的典型应用)
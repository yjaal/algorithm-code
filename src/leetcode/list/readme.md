链表是线性表，操作的是节点引用，需格外留意指针断裂和形成环的问题。

*   **核心场景**：反转链表、合并有序链表、删除节点（尤其是倒数第N个）、判断链表是否有环、寻找环的入口或相交节点。
*   **解题套路**：
    1.  **虚拟头节点 (Dummy Node)**：当可能操作到头节点时（如删除或插入），创建一个`dummy`节点，令`dummy.next = head`。这样，头节点就可以像普通节点一样被处理，无需单独判空，极大简化代码逻辑。
    2.  **快慢指针**：这是链表题的“王牌”技巧。
        *   **找中点**：快指针每次走两步，慢指针走一步，当快指针到末尾时，慢指针正好在中点，可用于归并排序链表或判断回文链表。
        *   **判环与找环入口**：快慢指针在环内必相遇。相遇后，将快指针移回头部，然后两指针每次都走一步，再次相遇的节点就是环的入口。
    3.  **递归/迭代反转**：熟练使用“三指针”法（`prev`, `curr`, `next`）进行原地反转，或使用递归方法解决“两两交换链表节点”等反转变体问题。

*   **推荐练习题**：
    *   [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/) (基础，必刷)
    *   [21. 合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/) (基础，必刷)
    *   [19. 删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/) (快慢指针典型应用)
    *   [141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/) (快慢指针判环)
    *   [160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/) (双指针浪漫相遇)
package leetcode.list;

import leetcode.list.Solution206.ListNode;

/**
 * 反转链表 II
 * <p>
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * <p>
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * 进阶： 你可以使用一趟扫描完成反转吗？
 */
public class Solution92 {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        listNode = new Solution92().reverseBetween(listNode, 2, 5);
        System.out.println(listNode);
    }

    /**
     * 最简单的方法就是将链表切割、反转，然后再进行拼接
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 1. 引入虚拟头节点，统一处理 left=1 的边界情况
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // 2. 定位到反转区间的前驱节点 leftPre
        ListNode leftPre = dummy;
        for (int i = 0; i < left - 1; i++) {
            leftPre = leftPre.next;
        }

        // 3. leftNode 是反转区间的第一个节点，在整个过程中它的位置是不动的
        // 1, 2, 3, 4, 5 此时 leftPre = 1, leftNode = 2
        // 1, 3, 2, 4, 5 此时 leftPre = 1, leftNode = 2，只是将3插入到了leftPre的后面，同时让leftNode指向了3原来的next
        // 1，4，3，2，5 同上
        ListNode leftNode = leftPre.next;

        // 一定要注意：leftPre和leftNode是不动的
        // 4. 使用头插法，进行 right - left 次操作
        // 每次把 leftNode 后面的节点拔下来，插到 leftPre 后面
        for (int i = 0; i < right - left; i++) {
            ListNode cur = leftNode.next; // 找到要被拔下来的节点

            leftNode.next = cur.next;     // 步骤1：把 cur 从原位摘下来
            cur.next = leftPre.next;      // 步骤2：cur 指向当前区间的第一个节点
            leftPre.next = cur;           // 步骤3：leftPre 指向新的区间头 cur
        }
        return dummy.next;
    }
}

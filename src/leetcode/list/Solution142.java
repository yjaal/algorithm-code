package leetcode.list;

import leetcode.list.Solution206.ListNode;

/**
 * 环形链表 II
 * <p>
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 * <p>
 * 示例1:
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 * <p>
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 * <p>
 * <p>
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 */
public class Solution142 {

    /**
     * 假设头到入环扣距离为a，环的长度为c
     * 设相遇的时候，慢指针走了b步，那么快指针走了2b步
     * 设快指针比慢指针多走了k圈，也就是2b-b=kc，于是b=kc
     *
     * 慢指针从入环扣开始在环中走了b-a=kc-a步到达相遇点，这里k是一个整数，
     * 所以再走a步就可以到达入环扣，此时如果将快指针移动到头节点，让其走a步
     * 就刚好可以到达入环扣，因为此时kc-a+a=kc才是一个整圈数。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode f = head;
        ListNode s = head;
        // 是否有环
        boolean flag = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                f = head;
                flag = true;
                break;
            }
        }
        if (flag) {
            while (s != f) {
                s = s.next;
                f = f.next;
            }
            return s;
        }
        return null;
    }
}

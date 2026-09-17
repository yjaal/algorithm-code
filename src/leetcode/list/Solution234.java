package leetcode.list;

import leetcode.list.Solution206.ListNode;

/**
 * 回文链表
 * <p>
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 */
public class Solution234 {

    /**
     * 这里主要是三个步骤，其实还可以简化，就是快慢指针找中点时就可以对前序节点进行反转
     */
    public boolean isPalindrome(ListNode head) {

        // 找中点
        ListNode f = head;
        ListNode s = head;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
        }

        // 链表反转
        ListNode pre = null;
        ListNode cur = s;
        while (cur != null) {
            // 保留后续链表
            ListNode tmp = cur.next;
            // 反转
            cur.next = pre;
            // 往后移动
            pre = cur;
            cur = tmp;
        }

        // 逐一对比
        // 3. 逐一比较前半部分和反转后的后半部分
        ListNode p1 = head;
        ListNode p2 = pre; // p2 指向反转后的后半部分头节点
        while (p2 != null) { // 注意：这里以 p2 为准，因为如果是奇数个节点，前半部分会多出一个中点
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }
}

package leetcode.list;

import leetcode.list.Solution206.ListNode;

/**
 *
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution21 {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode listNode = new Solution21().mergeTwoLists(list1, list2);
        System.out.println(listNode);
    }

    // 1 2 4   1 3 4
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        detail(res, list1, list2);
        return res.next;
    }

    private void detail(ListNode pre, ListNode cur1, ListNode cur2) {
        if (cur1 == null) {
            pre.next = cur2;
            return;
        } else if (cur2 == null) {
            pre.next = cur1;
            return;
        }
        if (cur1.val < cur2.val) {
            pre.next = cur1;
            detail(pre.next, cur1.next, cur2);
        } else {
            pre.next = cur2;
            detail(pre.next, cur1, cur2.next);
        }
    }


    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode temp = result;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        temp.next = list1 == null ? list2 : list1;
        return result.next;
    }
}

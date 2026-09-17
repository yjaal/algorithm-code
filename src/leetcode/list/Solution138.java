package leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机链表的复制
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 *
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。复制链表中的指针都不应指向原链表中的节点 。
 *
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 */
public class Solution138 {

    /**
     *  其实就是因为存在random节点，可能拷贝时会重复，这里就是要求深度拷贝，但是保证不能重复
     *
     *  此方法较为容易理解，就是先遍历一遍，只存值并且记录新旧节点之间的映射
     *  然后遍历第二遍，根据映射关系连接 next 和 random
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Node, Node> map = new HashMap<>();

        // 1. 第一遍：复制所有节点并建立映射
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        // 2. 第二遍：根据映射关系连接 next 和 random
        cur = head;
        while (cur != null) {
            Node newNode = map.get(cur);
            newNode.next = map.get(cur.next);       // 注意：这里也要通过 map 获取新节点
            newNode.random = map.get(cur.random);   // 如果 cur.random 为 null，map.get 返回 null，刚好兼容
            cur = cur.next;
        }

        return map.get(head);
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }
}

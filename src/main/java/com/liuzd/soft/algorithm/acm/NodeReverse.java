package com.liuzd.soft.algorithm.acm;

import com.liuzd.soft.algorithm.utils.PrintUtils;

/**
 * 链表反转
 *
 * @author: liuzd
 * @date: 2025/9/12
 * @email: liuzd2025@qq.com
 * @desc
 */
public class NodeReverse {
    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode first = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = first;
            first = head;
            head = next;
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        ListNode reverseList = reverseList(node);
        PrintUtils.printNode(reverseList);
    }

}

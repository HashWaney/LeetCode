package cn.hash.leetcode.sort;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class leetcode_02_两数相加 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(7);
        ListNode listNode11 = new ListNode(8);
        ListNode listNode111 = new ListNode(9);

        //链表 7->8->9
        listNode.next = listNode11;
        listNode11.next = listNode111;
        ListNode listNode1 = new ListNode(3);
        listNode1.next = new ListNode(2);
        // 链表 3->2
        ListNode listNode2 = addTwoNum(listNode, listNode1);
        StringBuilder builder = new StringBuilder();

        System.out.println(printNode(builder, listNode2).toString());
    }

//    public static void main(String[] args) {
//
////        7->8->9->null;
////      + 3->2->0->null;
////        ---------
////  carry  1  1  1
////        0  1  0  1
//    }

    public static String printNode(StringBuilder builder, ListNode listNode) {
        if (listNode != null) {
            builder.append("->").append(listNode.val);
        }

        if (listNode.next != null) {
            printNode(builder, listNode.next);
        }
        String s = builder.toString();
        return s.substring(2); // 去掉第一个"->" 相当于是两个字符，因此从01 干掉了，从2个角标开始。
    }

    public static ListNode addTwoNum(ListNode li, ListNode lii) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (li != null || lii != null) {
            int x = li == null ? 0 : li.val;
            int y = lii == null ? 0 : lii.val;
            int sum = x + y + carry;

            carry = sum / 10; // 相加之后取得进位位
            sum = sum % 10; // 相加之后保留的个位数，
            cur.next = new ListNode(sum); //保存在当前的链表中，

            cur = cur.next;
            if (li != null) {
                li = li.next;
            }
            if (lii != null) {
                lii = lii.next;
            }

        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;

    }
}

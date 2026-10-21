package org.example;


class ListNode {
    int val;
    ListNode next;
    ListNode(int val) { this.val = val; }
}

public class Solution {

    public ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0); // head of "less" chain
        ListNode bigDummy = new ListNode(0); // head of "greater or equal" chain

// tails[0] = tail of less chain, tails[1] = tail of big chain
        ListNode[] tails = { lessDummy, bigDummy };

        helper(head, x, tails);

        tails[1].next = null; // end the list (avoid cycles)
        tails[0].next = bigDummy.next; // join less → big
        return lessDummy.next;
    }

    // Processes one node per call, then recurses on the remainder
    private void helper(ListNode node, int x, ListNode[] tails) {
        if (node == null) return; // base case

        ListNode next = node.next; // save before relinking

        if (node.val < x) {
            tails[0].next = node;
            tails[0] = node;
        } else {
            tails[1].next = node;
            tails[1] = node;
        }


        helper(next, x, tails); // recursive step
    }

    // ---------- Helpers for testing ----------
    static ListNode build(int[] arr) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : arr) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    static void print(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(",");
            head = head.next;
        }
        System.out.println(sb.append("]"));

    }

    public static void main(String[] args) {
        Solution s = new Solution();

// Test 1
        print(s.partition(build(new int[]{1, 4, 3, 2, 5, 2}), 3));
// Test 2
        print(s.partition(build(new int[]{2, 1}), 2));
    }
}

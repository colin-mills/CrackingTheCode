package LinkedLists;

public class LinkedList {

    // Definition for singly-linked list as inner class.
    private class Node {
        int val;
        Node next;
        Node() {}
        Node(int val, Node next) { this.val = val; this.next = next; }
        Node(int val) { this.val = val; }
    }

    public static Node removeDups(Node head) {
        //early exit / edge cases
        if (head == null || head.next == null) return head;
    }
}

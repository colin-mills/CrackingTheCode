package LinkedLists;

import java.util.HashSet;

public class LinkedList {

    // Definition for singly-linked list as inner class.
    private class Node {
        int val;
        Node next;
        Node() {}
        Node(int val, Node next) { this.val = val; this.next = next; }
        Node(int val) { this.val = val; }
    }

    /*
    2.1 Remove Dups
    pg. 94
    TC: O(n)
    SC: O(1)
     */
    public static Node removeDups(Node head) {
        //early exit / edge cases
        if (head == null || head.next == null) return head;
        HashSet<Integer> visitedSet = new HashSet<>();
        Node ptr = head;
        visitedSet.add(head.val);
        while (ptr != null && ptr.next != null) {
            if (visitedSet.contains(ptr.val)) removeNext(ptr);
            else {
                visitedSet.add(ptr.next.val);
                ptr = ptr.next;
            }
        }
        return head;
    }

    /*
    2.2 Return kth to last
    Pg. 94
    TC: O(n)
    SC: O(1)
     */
    public static Node returnKthToEnd(Node head, int k) {
        if (head == null) return head;
        Node slow = head, fast = head;
        //We are defining k = 0 as the last element
        while (fast != null && fast.next != null && k > 0) {
            fast = fast.next;
            k--;
        }
        if (k != 0) return null; //if we don't have enough elements

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static void removeNext(Node prev) {
        if (prev == null || prev.next == null) return;

        prev.next = prev.next.next;
    }
}

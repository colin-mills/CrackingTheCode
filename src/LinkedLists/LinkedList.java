package LinkedLists;

import java.util.HashSet;

public class LinkedList {

    // Definition for singly-linked list as inner class.
    private static class Node {
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

    /*
    2.3 Delete Middle Node
    Pg. 94
    TC: O(n)
    SC: O(1)
     */
    public static void RemoveMiddleNode(Node head, Node rem) {
        //can't be the middle node if these conditions aren't met
        if (head == rem || head == null || head.next == null) return;

        while (head != null && head.next != rem) head = head.next;

        //If not found or is the tail
        if (head == null || head.next == null) return;

        removeNext(head);
    }

    //Overridden function for if we are not given head
    public static void RemoveMiddleNode(Node rem) {
        //this won't work if it is the last node
        if (rem == null || rem.next == null) return;
        rem.val = rem.next.val;
        removeNext(rem);
    }

    /*
    2.4 Partition
    Pg. 94
    TC: O(n)
    SC: O(1)
     */
    public static Node[] partition(Node head, int p) {
        if (head == null) return null;

        //Create dummy sentinel nodes and add to partitionSet
        Node leftHead = new Node(-1);
        Node rightHead = new Node(-1);
        Node[] partitionSet = new Node[] {leftHead, rightHead};

        //if just one element
        if (head.next == null) {
            if (head.val < p) leftHead.next = head;
            else rightHead.next = head;

            return partitionSet;
        }
        Node ptr = head, leftPtr = leftHead, rightPtr = rightHead;

        while (ptr != null) {
            if (ptr.val < p) {
                leftPtr.next = ptr;
                leftPtr = leftPtr.next;
            } else {
                rightPtr.next = ptr;
                rightPtr = rightPtr.next;
            }
            ptr = ptr.next;
        }
        leftPtr.next = rightPtr.next = null;
        return partitionSet;
    }

    private static void removeNext(Node prev) {
        if (prev == null || prev.next == null) return;

        prev.next = prev.next.next;
    }
}

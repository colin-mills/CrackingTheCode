package LinkedLists;

import java.util.HashSet;
import java.util.Stack;

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

    /*
    2.5 Sum lists
    Pg. 95
    TC: O(a+b)
    SC: O(1)
    Iterative solution
     */
    public static Node sumLists(Node headOne, Node headTwo) {
        long carry = 0, sum = 0;
        Node headNew = new Node();
        Node ptrNew = headNew;

        while (headOne != null && headTwo != null) {
            if (headOne != null) sum += headOne.val;
            if (headTwo != null) sum += headTwo.val;
            carry = sum >= 10 ? 1 : 0;
            ptrNew.val += sum;
            ptrNew.next = new Node((int) carry);
            sum = 0;
            ptrNew = ptrNew.next;
        }
        return headNew;
    }

    /*
    2.5 Recursive approach
     */
    public static Node sumListsRecursive(Node headOne, Node headTwo) {
        //base case or end of recursion
        if (headOne == null && headTwo == null) return null;
        long sum = 0;
        if (headOne != null) sum += headOne.val;
        if (headTwo != null) sum += headTwo.val;

        Node newList = new Node((int) sum);
        newList.next = sumListsRecursive(headOne.next, headTwo.next, sum > 9 ? 1 : 0);
        return newList;
    }

    public static Node sumListsRecursive(Node headOne, Node headTwo, int carry) {
        //base case or end of recursion
        if (headOne == null && headTwo == null) return null;
        long sum = carry;
        if (headOne != null) sum += headOne.val;
        if (headTwo != null) sum += headTwo.val;

        Node newList = new Node((int) sum);
        newList.next = sumListsRecursive(headOne.next, headTwo.next, sum > 9 ? 1 : 0);
        return newList;
    }

    /*
    2.6 Palindrome
    Pg. 95
    TC: O(n)
    SC: O(n) // O(n/2) = O(n * 1/2) = O(n)
     */
    public static boolean isPalindrome(Node head) {
        //if empty return false, if one value return true
        if (head == null || head.next == null) return head != null;

        Stack<Integer> reverse = new Stack<>();

        Node slow = head, fast = head;

        //If even fast will equal null
        while (fast != null && fast.next != null) {
            reverse.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }

        //if it is odd skip the middle
        if (fast != null) slow = slow.next;

        while (slow != null) {
            if (slow.val != reverse.pop()) return false;
            slow = slow.next;
        }
        return true;
    }

    /*
    2.7 Intersection
    Pg. 95
    TC: O(A+B)
    SC: O(A)
    HashSet approach
     */
    public static Node getIntersection (Node a, Node b) {
        HashSet<Node> set = new HashSet<>();

        while (a != null) {
            set.add(a);
            a = a.next;
        }

        while (b != null) {
            if (set.contains(b)) return b;
            b = b.next;
        }
        return null;
    }

    private static void removeNext(Node prev) {
        if (prev == null || prev.next == null) return;

        prev.next = prev.next.next;
    }
}

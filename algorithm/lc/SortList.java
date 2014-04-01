package algorithm.lc;

import java.util.Random;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 */
public class SortList {

  static class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
      val = x;
      next = null;
    }
  }

  public static class Solution {
    
    static class HeadTail {
      ListNode head;
      ListNode tail;
      
      public HeadTail(ListNode head, ListNode tail) {
        this.head = head;
        this.tail = tail;
      }
    }
    
    public ListNode sortList(ListNode head) {
      // IMPORTANT: Please reset any member data you declared, as
      // the same Solution instance will be reused for each test case.
      if (head == null || head.next == null) { // list with less than 2 elements
        return head;
      }
      ListNode tail = head;
      while (tail.next != null) {
        tail = tail.next;
      }
      HeadTail headTail = sort(head, tail);
      return headTail.head;
    }
    
    private HeadTail sort(ListNode head, ListNode tail) {
      if (head == tail) {
        tail.next = null;
        HeadTail headTail = new HeadTail(head, tail);
        return headTail;
      }
      
      // divide the list into two parts
      ListNode slow = head;
      ListNode fast = head.next;
      
      while (fast != tail) {
        fast = fast.next;
        if (fast != tail) {
          fast = fast.next;
          slow = slow.next;
        }
      }
      
      // sort respectively
      ListNode rightHead = slow.next;
      HeadTail leftSorted = sort(head, slow);
      HeadTail rightSorted = sort(rightHead, tail);
      
      // merge
      ListNode fakeHead = new ListNode(0);
      ListNode cur = fakeHead;
      ListNode left = leftSorted.head;
      ListNode right = rightSorted.head;
      
      while (left != null || right != null) {
        if (left == null) {
          cur.next = right;
          right = right.next;
        }
        else if (right == null) {
          cur.next = left;
          left = left.next;
        }
        else if (left.val <= right.val) {
          cur.next = left;
          left = left.next;
        }
        else {
          cur.next = right;
          right = right.next;
        }
        cur = cur.next;
      }
      
      return new HeadTail(fakeHead.next, cur);
    }
    
  }
  
  public static void printList(ListNode head) {
    StringBuilder sb = new StringBuilder();
    while (head != null) {
      sb.append(head.val);
      sb.append(' ');
      head = head.next;
    }
    sb.append('\n');
    System.out.println(sb.toString());
  }

}

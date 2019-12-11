package LeetCode;

import LeetCode.Definition.ListNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    /*141.Linked List Cycle
     * Given a linked list, determine if it has a cycle in it.
     * To represent a cycle in the given linked list,
     * we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
     *  If pos is -1, then there is no cycle in the linked list.
     * */
    //141 hash表方式
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> nodeSet = new HashSet<>();
        while (head != null) {
            if (nodeSet.contains(head)) {
                return true;
            } else {
                nodeSet.add(head);
            }
            head = head.next;
        }
        return false;
    }

    //141 快慢指针方式
    public boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = slow;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }


    /*
     * 142. Linked List Cycle II
     * Given a linked list, return the node where the cycle begins.
     * If there is no cycle, return null.
     * To represent a cycle in the given linked list,
     * we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to.
     * If pos is -1, then there is no cycle in the linked list.
     * Note: Do not modify the linked list.
     * */
    //142 hash表方式略

    //142 快慢指针方式
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = slow;
        boolean hasCycle = false;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        fast = head;
        while (fast != null) {
            if (fast == slow) {
                return slow;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return null;
    }

    //78. 迭代方式
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> allList = new ArrayList<>();
        allList.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            for (int p = 0, size = allList.size(); p < size; p++) {
                List<Integer> temp = new ArrayList<>(allList.get(p));
                temp.add(nums[i]);
                allList.add(temp);
            }
        }
        return allList;
    }

    public static void main(String[] args) {
        subsets(new int[]{1, 2, 3});
    }
}



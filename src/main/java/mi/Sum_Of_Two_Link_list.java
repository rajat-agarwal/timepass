package mi;

import mi.utilities.AssortedMethods;
import mi.utilities.ListNode;

import java.util.LinkedList;

/**
 * Created by rajat.agarwal on 30/06/17.
 */
public class Sum_Of_Two_Link_list {
    public static void main(String[] args) {
        ListNode one = AssortedMethods.createLinkedListFromArray(new int[]{1, 2, 3, 4, 5});
        ListNode two = AssortedMethods.createLinkedListFromArray(new int[]{3, 4, 5, 6, 7});
        System.out.println(one.printForward());
    }

    private static LinkedList add(ListNode one, ListNode two) {
        LinkedList<ListNode> res = new LinkedList();
        int carry = 0;
        while (one != null && two != null) {
            res.add(new ListNode((carry + one.data + two.data) % 10));
            carry = (carry + one.data + two.data) / 10;
            one = one.next;
            two = two.next;
        }
        while (one != null) {
            res.add(new ListNode((carry + one.data) % 10));
            carry = (carry + one.data) / 10;
            one = one.next;
        }
        while (two != null) {
            res.add(new ListNode((carry + two.data) % 10));
            carry = (carry + two.data) / 10;
            two = two.next;
        }
        return res;
    }
}

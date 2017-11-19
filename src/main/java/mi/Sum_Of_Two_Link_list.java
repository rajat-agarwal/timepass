package mi;

import mi.utilities.AssortedMethods;
import mi.utilities.LinkedListNode;

import java.util.LinkedList;

/**
 * Created by rajat.agarwal on 30/06/17.
 */
public class Sum_Of_Two_Link_list {
    public static void main(String[] args) {
        LinkedListNode one = AssortedMethods.createLinkedListFromArray(new int[]{1, 2, 3, 4, 5});
        LinkedListNode two = AssortedMethods.createLinkedListFromArray(new int[]{3, 4, 5, 6, 7});
        System.out.println(one.printForward());
    }

    private static LinkedList add(LinkedListNode one, LinkedListNode two) {
        LinkedList<LinkedListNode> res = new LinkedList();
        int carry = 0;
        while (one != null && two != null) {
            res.add(new LinkedListNode((carry + one.data + two.data) % 10));
            carry = (carry + one.data + two.data) / 10;
            one = one.next;
            two = two.next;
        }
        while (one != null) {
            res.add(new LinkedListNode((carry + one.data) % 10));
            carry = (carry + one.data) / 10;
            one = one.next;
        }
        while (two != null) {
            res.add(new LinkedListNode((carry + two.data) % 10));
            carry = (carry + two.data) / 10;
            two = two.next;
        }
        return res;
    }
}

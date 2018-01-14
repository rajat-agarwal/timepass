package mi.utilities;

public class ListNode {
    public ListNode next;
    public ListNode prev;
    public ListNode last;
    public int data;

    public ListNode(int d, ListNode n, ListNode p) {
        data = d;
        setNext(n);
        setPrevious(p);
    }

    public ListNode(int d) {
        data = d;
    }

    public ListNode() {
    }

    public void setNext(ListNode n) {
        next = n;
        if (this == last) {
            last = n;
        }
        if (n != null && n.prev != this) {
            n.setPrevious(this);
        }
    }

    public void setPrevious(ListNode p) {
        prev = p;
        if (p != null && p.next != this) {
            p.setNext(this);
        }
    }

    public String printForward() {
        if (next != null) {
            return data + "->" + next.printForward();
        } else {
            return ((Integer) data).toString();
        }
    }

    public ListNode clone() {
        ListNode next2 = null;
        if (next != null) {
            next2 = next.clone();
        }
        ListNode head2 = new ListNode(data, next2, null);
        return head2;
    }
}
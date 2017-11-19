package mi;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by rajat.agarwal on 01/08/17.
 */
public class Max_In_Sliding_K_Window {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        int window = 3;
        int currWindowEndIdx = window - 1;
        int slidingInterval = 2;

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[i] >= arr[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (i == currWindowEndIdx) {
                System.out.print(arr[deque.peekFirst()] + "\t");
                currWindowEndIdx += slidingInterval;
                while (!deque.isEmpty() && deque.peekFirst() <= currWindowEndIdx + 1 - window) {
                    deque.removeFirst();
                }
            }
        }
    }
}

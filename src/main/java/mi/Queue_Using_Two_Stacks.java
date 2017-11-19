package mi;
import java.util.Stack;

/**
 * Created by rajat.agarwal on 22/07/17.
 */
public class Queue_Using_Two_Stacks {
    Stack<Integer> dequeueStack = new Stack<>();
    Stack<Integer> enqueueStack = new Stack<>();

    public void enqueue(int v){
        enqueueStack.push(v);
    }

    public int dequeue(){
        if (dequeueStack.isEmpty()){
            while (!enqueueStack.isEmpty()) {
                dequeueStack.push(enqueueStack.pop());
            }
        }
        return dequeueStack.isEmpty() ? -1 : dequeueStack.pop();
    }

    public static void main(String[] args) {
        Queue_Using_Two_Stacks c = new Queue_Using_Two_Stacks();
        c.enqueue(1);
        c.enqueue(2);
        c.enqueue(3);
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        c.enqueue(4);
        c.enqueue(5);
        System.out.println(c.dequeue());
        c.enqueue(6);
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        System.out.println(c.dequeue());
        c.enqueue(7);
        System.out.println(c.dequeue());
    }
}

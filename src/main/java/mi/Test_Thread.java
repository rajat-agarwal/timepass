package mi;
import lombok.NoArgsConstructor;

/**
 * Created by rajat.agarwal on 12/08/17.
 */
@NoArgsConstructor
public class Test_Thread {

    @NoArgsConstructor
    class SharedObj {
        //        Integer val = 1;
        ThreadLocal<Integer> threadLocalInt = new ThreadLocal<Integer>() {
            @Override
            protected Integer initialValue() {
                return 100;
            }
        };
    }

    class TestThreadLocalAdd implements Runnable {
        SharedObj sharedObj;

        public TestThreadLocalAdd(SharedObj sharedObj) {
            this.sharedObj = sharedObj;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                this.sharedObj.threadLocalInt.set(this.sharedObj.threadLocalInt.get() + 1);
            }
            System.out.println(this.sharedObj.threadLocalInt.get());
        }
    }

    class TestThreadLocalSubtract implements Runnable {
        SharedObj sharedObj;

        public TestThreadLocalSubtract(SharedObj sharedObj) {
            this.sharedObj = sharedObj;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                this.sharedObj.threadLocalInt.set(this.sharedObj.threadLocalInt.get() - 1);
            }
            System.out.println(this.sharedObj.threadLocalInt.get());
        }
    }

    public static void main(String[] args) {
        Test_Thread classObj = new Test_Thread();
        SharedObj sharedObj = classObj.new SharedObj();

        Thread thread1 = new Thread(classObj.new TestThreadLocalAdd(sharedObj));
        Thread thread2 = new Thread(classObj.new TestThreadLocalSubtract(sharedObj));

        thread1.start();
        thread2.start();
    }
}

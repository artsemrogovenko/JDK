
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Runnable {

    private CountDownLatch cdl;
    /*
     * @Override
     * public String toString() {
     * return left +"+" +right;
     * }
     */

    // private final int left, right;
    private final AtomicInteger value;

    public Task(int val) {
        this.value = new AtomicInteger(val);
    }

    /*
     * public Task(int left, int right) {
     * this.left = left;
     * this.right = right;
     * }
     */

    public void inc() {
        synchronized (Task.class) {
            value.incrementAndGet();
        }

    }

    public int getvalue() {
        return value.get();
    }

    public void setCdl(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        // System.out.println(left+right);
        for (int i = 0; i < 1000000; i++) {

            inc();
        }
        cdl.countDown();
    }

}

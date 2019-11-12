package concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class AqsTest {
    private Object obj =  new Object();
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
    }
}

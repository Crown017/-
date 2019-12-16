package concurrent.test_demo;

import java.util.concurrent.locks.ReentrantLock;


/**
 * 源码的入口
 */
public class AqsTest {
    private Object obj =  new Object();
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
    }
}

package concurrent.juc;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class JucLock {
    public static void main(String[] args) {
        //LOCK
        ReentrantLock lock = new ReentrantLock();
        //条件队列
        Condition condition = lock.newCondition();
        //阻塞
        LockSupport.unpark(Thread.currentThread());
        //Latch------AQS
        CountDownLatch latch = new CountDownLatch(10);
        //信号量------AQS
        Semaphore semaphore = new Semaphore(10);
        //栅栏
        CyclicBarrier barrier = new CyclicBarrier(10);
        //map
        HashMap map = new HashMap();
        //红黑树 synchronized
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        //跳跃表 大数据量
        ConcurrentSkipListMap skipListMap = new ConcurrentSkipListMap();
    }
}

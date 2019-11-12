package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用BlockingQueue实现生产者消费者模型
 */
public class Test3 {
    private static AtomicInteger count = new AtomicInteger(0);
    //创建一个阻塞队列
    private ReentrantLock lock = new ReentrantLock();
    final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    public static void main(String[] args) {
        Test3 test3 = new Test3();
        new Thread(test3.new Producer()).start();
        new Thread(test3.new Consumer()).start();
        new Thread(test3.new Producer()).start();
        new Thread(test3.new Consumer()).start();
        new Thread(test3.new Producer()).start();
        new Thread(test3.new Consumer()).start();
        new Thread(test3.new Producer()).start();
        new Thread(test3.new Consumer()).start();
    }
    class Producer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    lock.lock();
                    blockingQueue.put(count.incrementAndGet());
                    System.out.println(Thread.currentThread().getName()
                            + "生产者生产，目前总共生产了" + count.get());
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                   Integer num =  blockingQueue.take();
                    System.out.println(Thread.currentThread().getName()
                            + "消费者消费，目前消费数" + num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
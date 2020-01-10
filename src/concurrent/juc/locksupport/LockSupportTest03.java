package concurrent.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest03 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //只有一个多次获取的结果也是一样的
            LockSupport.unpark(Thread.currentThread());
            LockSupport.unpark(Thread.currentThread());
            //这里将不会阻塞
            LockSupport.park();
            System.out.println("park 1 not block ");
            LockSupport.park();
            System.out.println("Thread not blocking");

        });

        thread.start();
    }
}

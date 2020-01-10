package concurrent.juc.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            //get the permit
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

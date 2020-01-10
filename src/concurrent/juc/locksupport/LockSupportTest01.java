package concurrent.juc.locksupport;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("sub thread run.....");
            LockSupport.park();
            System.out.println("sub get permit");
        });
        thread.start();
        Thread.sleep(2000);
        System.out.println("main thread give permit to blocking thread , and then it released");
        LockSupport.unpark(thread);
    }
}

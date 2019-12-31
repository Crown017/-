package concurrent.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static int num = 0 ;

    private  static Lock mylock = new ReentrantLock();

    public int IncrementAndGet(){
           try{
               mylock.lock();
               num = num + 1;
               return num;
           }finally {
              mylock.unlock();
           }

    }


    public static void main(String[] args) {
        LockTest test = new LockTest();
        test.IncrementAndGet();
    }
}

package concurrent.atomic.base;

import javafx.concurrent.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicIntegerTest {


    /**
     * 在高并发场景下效率不高，需要不断的刷新内存 建议使用LongAdder
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        AtomicLong atomicInteger = new AtomicLong();
        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int  i = 0 ; i< 100 ;i++){
            service.submit(new Task(atomicInteger));
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(atomicInteger.get());
        service.shutdown();
    }


    static class Task  implements  Runnable{

        private final AtomicLong atomicLong;

        public Task(AtomicLong atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            atomicLong.incrementAndGet();
        }
    }

}

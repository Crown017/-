package concurrent.atomic.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    /**
     * LongAdder引入分段累加的概念 内部一共有两个参数参与计数：
     *
     * 1. base 是一个变量在竞争不激烈的情况下直接把累加结果改到base变量上面
     *
     *
     * 2. Cell 是一个数组，一旦竞争激烈，各个线程会根据Hash值分散到各个Cell数组上，而不会大家共用一个
     *
     *
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        LongAdder  adder = new LongAdder();

        ExecutorService service = Executors.newFixedThreadPool(16);
        for (int  i = 0 ; i< 100 ;i++){
            service.submit(new Task(adder));
        }

        TimeUnit.SECONDS.sleep(2);
        System.out.println(adder.sum());
        service.shutdown();
    }


    static class Task  implements  Runnable{

        private final LongAdder atomicLong;

        public Task(LongAdder atomicLong) {
            this.atomicLong = atomicLong;
        }

        @Override
        public void run() {
            atomicLong.add(1);
        }
    }
}

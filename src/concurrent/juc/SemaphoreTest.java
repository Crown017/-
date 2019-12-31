package concurrent.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest {

    private Semaphore semaphore = new Semaphore(10);
    private AtomicInteger counter   = new AtomicInteger(0);

    public static void main(String[] args) {

        SemaphoreTest semaphoreTest = new SemaphoreTest();
        for (int i = 0 ; i < 100 ; i++ ){
            new Thread(()-> {
                semaphoreTest.getInfo();
            }).start();
        }



    }

    protected void getInfo(){
        try {
            semaphore.acquire();
            int andIncrement = counter.getAndIncrement();
            System.out.println(" thread execute " + andIncrement);
            Thread.sleep(1000);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }

    }

}

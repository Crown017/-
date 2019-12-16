package concurrent.test_demo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest {
    public static void main(String[] args) {
        try {
            FutureTask<String> futureTask2 = new FutureTask(new T2Task());
            FutureTask<String> futureTask1 = new FutureTask<>(new T1Task(futureTask2));
            Thread thread = new Thread(futureTask1);
            thread.start();
            Thread thread2 = new Thread(futureTask2);
            thread2.start();
            System.out.println(futureTask1.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class T1Task implements Callable<String>{

    FutureTask<String> ft2 ;

    public T1Task(FutureTask<String> ft2) {
        this.ft2 = ft2;
    }



    @Override
    public String call() throws Exception {
        System.out.println("T1 洗水壶");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T1:烧开水");
        String tf = ft2.get();
        System.out.println("T1:拿到茶叶："+tf);
        System.out.println("T1:泡茶。。。");
        return "上茶"+tf;
    }
}


class T2Task implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("T2 :洗茶壶...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("T2 :洗茶杯...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("T2: 拿茶叶...");
        TimeUnit.SECONDS.sleep(1);
        return "龙井";
    }
}

package concurrent.test_demo;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ConcurrencyTest2 {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(
                () -> {
                    TimeUnit.SECONDS.sleep(8);
                    return "蛋糕做好了";
                }
        );

        FutureTask<String> futureTask1 = new FutureTask<>(
                () -> {
                    System.out.println("T2：我要买蛋糕");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("T2：看电影。。。");
                    TimeUnit.SECONDS.sleep(7);
                    String result = futureTask.get();
                    System.out.println(result);
                    return "T2： 去拿蛋糕。。";
                }
        );


        Thread thread1 = new Thread(futureTask);
        thread1.start();
        Thread thread2 = new Thread(futureTask1);
        thread2.start();
        try {
            System.out.println(futureTask1.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
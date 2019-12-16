package concurrent.test_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTest {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.submit(()-> {
            return a+b;
        });


    }
}

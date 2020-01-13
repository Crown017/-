package concurrent.compalte_future;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest04 {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> future = CompletableFuture
                                .runAsync(() -> System.out.println(new Date()))
                                .whenComplete((__, th) -> {
                                    System.out.println("已经结束了");
                                    th.printStackTrace();
                                });

        //CompletableFuture是守护线程

        Thread.currentThread().join();
    }
}

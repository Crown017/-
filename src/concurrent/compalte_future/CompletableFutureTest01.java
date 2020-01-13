package concurrent.compalte_future;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest01 {
    public static void main(String[] args) {
        CompletableFuture<Integer> completableFutur = CompletableFuture.supplyAsync(() -> (Integer) (1 + 2) / 0);

        completableFutur.thenAccept(System.out::println);

        CompletableFuture<Integer> exceptionally = completableFutur.exceptionally((ex) -> {
            ex.printStackTrace();
            return  3;
        });

        //等待主线程完成之后
        completableFutur.join();
    }
}

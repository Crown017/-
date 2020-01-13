package concurrent.compalte_future;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CompletableFutureTest03 {

    public static void main(String[] args) {
        Supplier<String> supplier12306 =  ()-> {
            try {
                Thread.sleep(100);

            }catch (Exception e){

            }
            return "从12306买到票";
        };

        //从某某某电商平台买到票
        Supplier<String> supplier1MMM =  ()-> {
            try {
                Thread.sleep(50);

            }catch (Exception e){

            }
            return "从某某某买到票";
        };


        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(supplier12306);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(supplier1MMM);

        CompletableFuture<Object> resultFuture = CompletableFuture.anyOf(f1, f2);

        resultFuture.thenAccept(System.out::println);

        resultFuture.join();
    }
}

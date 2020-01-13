package concurrent.compalte_future;

import java.sql.Time;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CompletableFutureTest06 {
    public static void main(String[] args) throws Exception {
        IntStream.range(0, 10).boxed().forEach(i -> {
            CompletableFuture.supplyAsync(CompletableFutureTest06::getUuid)
                    .whenComplete((re,th) -> {
                        System.out.println(re);

                        if (th != null){
                            th.printStackTrace();
                        }
                    });
        });

        Thread.currentThread().join();
    }




    public static String getUuid(){
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){

        }
        System.out.println("生成");
        return UUID.randomUUID().toString();
    }

}

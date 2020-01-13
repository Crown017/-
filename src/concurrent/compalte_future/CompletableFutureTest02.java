package concurrent.compalte_future;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest02 {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> "2020-01-13 12:23:45");

        CompletableFuture<Date> cf2 = cf1.thenApplyAsync((date) -> {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                Date parse = simpleDateFormat.parse(date);
                return parse;
            }catch (Exception e){
                e.printStackTrace();
                return new Date();
            }
        });

        CompletableFuture<Long> cf3 = cf2.thenApplyAsync(date -> date.getTime());
        cf3.thenAccept(System.out::println);

        cf3.join();

    }
}

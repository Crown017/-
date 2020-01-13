package concurrent.compalte_future;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureTest05 {
    public static void main(String[] args) throws Exception {
        //这里就相当于Callable不接受参数、返回值
        //只有一个方法 T get()
        Supplier<String> supplier = () ->{
            sleep(3,TimeUnit.SECONDS);
            return UUID.randomUUID().toString();
        };
        CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);

        future.whenComplete((uuid,th) -> {
            if (uuid!= null)
            System.out.println("UUID 创建完成 : "+uuid);

            if (th != null){
                th.printStackTrace();
            }
        });

        future.join();
    }




    static void sleep(int time, TimeUnit timeUnit){
        try {
            timeUnit.sleep(time);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

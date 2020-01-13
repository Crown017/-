package concurrent.compalte_future;

import java.util.concurrent.*;

public class TestFuture1 {
    public static void main(String[] args) {
        ExecutorService poolExecutor = Executors.newFixedThreadPool(1);

        Callable<String> callable = () -> {
            Thread.sleep(3000);
            return  "hello";
        };

        Future<String> future = poolExecutor.submit(callable);
        String result = null;
        try {
            result = future.get(4, TimeUnit.SECONDS);
        }catch (TimeoutException e){

        }catch (Exception e){
            future.cancel(true);
            result = "执行失败";
        }

        System.out.println(result);
        poolExecutor.shutdown();


        /**
         * Future存在一些缺陷就是我们无法感知它什么时候完成
         * Netty中的ChannelFuture可以添加Listener的方式解决这个问题
         *
         * future 是异步的  但是future#get 方法却会阻塞直到结果返回
         * 如果发生异常会导致线程阻塞无法返回,客户端可以使用重载版本的get,
         * Future一定要做好异常的处理,有时候取消任务
         */
    }
}

package concurrent.thread_create;

import java.util.concurrent.*;

public class ThreadTest {


    public static void main(String[] args) throws Exception {
        Callable<String> callable = ()-> "hello"+ "world" ;
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callable);
        String s = submit.get();
        System.out.println(s);
    }

}

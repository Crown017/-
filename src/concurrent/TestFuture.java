package concurrent;

import java.util.concurrent.*;

public class TestFuture {
    public static void main(String[] args) {
        try {
            FutureTask<Integer> futureTask = new FutureTask<>(()->
                {
                    int sum = 0;

                  for (int i = 0; i< 1000;i++){
                      Thread.sleep(200);
                      sum+=i;
                  }
                  return sum;
                });
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(futureTask);
            //这是一个阻塞的操作
            Integer result = futureTask.get();
            System.out.println(result);
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}

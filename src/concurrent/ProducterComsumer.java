package concurrent;

import java.util.Queue;

public class ProducterComsumer {


    private static Queue<String> queue;
    private static final Integer MAX_SIZE =10 ;

    //如果调用wait()方法的线程没有事先获取该对象的监视器锁(synchronized 锁)，则调用wait()方法时调用线程会抛出IllegalMonitorState Exception异常

    //虚假唤醒
    // 就是一个线程在没有被其他线程调用notify，notifyAll() 方法进行通知 或者被在中断，或者请求超时，依然从从挂起状态变为可以运行的状态（ 唤醒），
    // 防止虚假唤醒 循环中一直调用wait()方法进行防范
    // synchronized(obj){
    //      while(条件不满足){
    //          obj.wait()
    //      }
    // }
    public void producter(String ele) {
        synchronized (queue){
            while (queue.size() == MAX_SIZE ){
                try {
                    queue.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            queue.add(ele);
            queue.notifyAll();
        }
    }


    public void comsumer(){
        synchronized (queue){
            while (queue.size() == 0){
                try {
                    queue.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        queue.poll();
        queue.notifyAll();
    }




}

package concurrent;


/***
 * notify() 一个线程调用对象的notify()方法后，会唤醒一个在该变量上调用wait系列方法后
 * 被挂起的线程。一个共享变量上可能存在多个线程在等待，具体唤醒哪个线程是随机的。
 *
 * notifyAll() 则会唤醒被阻塞到该共享变量上，所有在该共享变量上由于调用wait 系列方法而被
 * 挂起的线程
 */
public class NotifyTest {
    private static volatile  Object resourceA = new Object();
    public static void main(String[] args) throws InterruptedException{
        Thread threadA =new Thread(() ->
        {
           synchronized(resourceA){
               System.out.println("threadA get resourceA lock");
               try {
                   System.out.println("threadA begin wait");
                   resourceA.wait();
                   System.out.println("threadA end wait");
               }catch (Exception e){

               }
           }
        });

        Thread threadB =new Thread(() ->
        {
            synchronized(resourceA){
                System.out.println("threadB get resourceA lock");
                try {
                    System.out.println("threadB begin wait");
                    resourceA.wait();
                    System.out.println("threadB end wait");
                }catch (Exception e){

                }
            }
        });


        Thread threadC =new Thread(() ->
        {
            synchronized(resourceA){
                System.out.println("threadC get resourceA lock");
                try {
                    System.out.println("threadC begin notify");
                    resourceA.notify();
                }catch (Exception e){

                }
            }
        });


        threadA.start();
        threadB.start();
        threadC.start();

        threadA.join();
        threadB.join();
        threadC.join();

        System.out.println("main over");

    }
}

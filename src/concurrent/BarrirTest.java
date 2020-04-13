package concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;


/**
 * CountDownLatch 跟 CyclicBarrier 的区别
 *
 *
 * CyclicBarrier中的数量事可以复用的
 *
 *
 * 1. CyclicBarrier中定义了两个变量一个count 、 parties
 * 2.当第一个线程来的时候会调用Condition的await() 方法 然后 --count
 * 3. 当线程数达到parties的数量就会调用condition条件变量的signAll 唤醒其他被调用的线程。
 * 4. 还会给count重新赋值parties，开启新的一个Generation
 *
 * 5.parties 变量就是保存初始化值
 *
 *
 */

public class BarrirTest {


    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("凑齐3个人");
            }
        });

        for (int i = 1 ; i< 61 ; i++){
            new Thread(new Student(cyclicBarrier,i)).start();
        }

    }
}

class Student implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private Integer studentId;


    public Student(CyclicBarrier cyclicBarrier,Integer studentId) {
        this.cyclicBarrier = cyclicBarrier;
        this.studentId = studentId;
    }


    @Override
    public void run() {
        try {

            Random random = new Random();
            TimeUnit.SECONDS.sleep(4 + random.nextInt(4));
            System.out.println("Student"+studentId+"到了车站，开始等待其他人到达");
            cyclicBarrier.await();
            System.out.println("Student "+studentId+" 已经坐上车");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}





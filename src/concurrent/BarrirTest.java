package concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

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

class Car implements Runnable{

    private CyclicBarrier cyclicBarrier;

    public Car(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();

            System.out.println("Car start speed to park");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}






package concurrent.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountLatchTest {

    public static void main(String[] args) throws Exception{

        //
        CountDownLatch countDownLatch = new CountDownLatch(3);


        Person person1 = new Person(countDownLatch,1);
        Person person2 = new Person(countDownLatch,2);
        Person person3 = new Person(countDownLatch,3);

        Car car = new Car(countDownLatch);

        Thread t1 = new Thread(person1);
        Thread t2 = new Thread(person2);
        Thread t3 = new Thread(person3);
        Thread t4 = new Thread(car);


        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }



}


class Person implements Runnable{

    private CountDownLatch downLatch;
    private Integer personId;

    public Person(CountDownLatch downLatch,Integer personId) {
        this.downLatch = downLatch;
        this.personId = personId;
    }


    @Override
    public void run() {
        try {
            Random random = new Random();
            TimeUnit.SECONDS.sleep(random.nextInt(4));
            System.out.println("Person_"+personId+" finished work");
            downLatch.countDown();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}


class Car implements Runnable{

    private CountDownLatch downLatch;

    public Car(CountDownLatch downLatch) {
        this.downLatch = downLatch;
    }

    @Override
    public void run() {
        try {
            //会检查计数器值是不是0，不为零，会进入阻塞队列当中
            downLatch.await();
            System.out.println("wait  all person");
        }catch (Exception e){

        }

    }
}

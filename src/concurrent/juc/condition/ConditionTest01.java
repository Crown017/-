package concurrent.juc.condition;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest01 {


    private static ArrayList<String> con = new ArrayList<>();

    private ReentrantLock lock =  new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer(){
        while (true ){
            lock.lock();
            try {
                 String uuid = UUID.randomUUID().toString();
                 Thread.sleep(300);
                 con.add(uuid);
                 condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }



    public void comsumer(){
        lock.lock();
        try {
            while (con.size() == 0 ){
                condition.await();
            }

            String s = con.get(0);
            System.out.println(s);
            System.out.println(" comsumer" + s);
            boolean removed = con.remove(s);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }








    public static void main(String[] args) {
        ConditionTest01 test01 = new ConditionTest01();
        new Thread(() -> {
            test01.comsumer();
        }).start();

        new Thread(()-> test01.producer()).start();
    }
}

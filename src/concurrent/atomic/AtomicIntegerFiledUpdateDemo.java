package concurrent.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFiledUpdateDemo implements Runnable {

    static Score score;
    static  Score computer;


    /**
     * 1.字段必须是volatile关键字修饰的
     * 2.字段的描述必须与操作的对象关系一致
     * 3.只能是实例变量，不能是类变量
     * 4.只能修改变量，不能是final类型的
     * 5.对于AtomicIntegerFieldUpdate 跟AtomicLongFieldUpdate 只能修改int / long 类型的字段，
     * 不能修改包装类型，如果要修改包装类型就需要使用，AtomicReferenceFieldUpdate
     */
    public static AtomicIntegerFieldUpdater<Score> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Score.class, "scorew");


    @Override
    public void run() {
        for (int i = 0 ; i< 1000 ;i++){
            computer.scorew++;
            scoreUpdater.getAndIncrement(score);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        score = new Score(0);
        computer = new Score(0);


        AtomicIntegerFiledUpdateDemo ad  =new AtomicIntegerFiledUpdateDemo();

        Thread  t1  =new Thread(ad);


        Thread t2 = new Thread(ad);


        t1.start();
        t2.start();


        t1.join();
        t2.join();


        System.out.println("普通的结果："+ computer.scorew + "升级后的结果"+ score.scorew);

    }
}

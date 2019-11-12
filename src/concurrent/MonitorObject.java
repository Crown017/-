package concurrent;

public class MonitorObject {
}

class MyTest{
    private MonitorObject object= new MonitorObject();

    public void  doWait(){
        synchronized (object){
            try{
                System.out.println("线程睡眠-----");
                object.wait();
                System.out.println("线程被唤醒-----");
            }catch (InterruptedException e){

            }
        }
    }

    public void doNotify(){
        synchronized (object){
            System.out.println("开始唤醒-----");
            object.notify();
        }
    }


    public static void main(String[] args) {
        MyTest test = new MyTest();
        Thread thread1 = new Thread(() -> {
            test.doWait();
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            test.doNotify();
        });
        thread2.start();
    }
}
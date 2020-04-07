package concurrent.ThreadLocal;


/**
 * 内存泄漏：
 *
 *      当一个对象不再有用的时候，占用的内存却不能被回收
 *
 *
 *
 *      Thread 每个Thread可以有一个与之对应的ThreadLoccalMap，
 *      ThreadLocalMap可以存放多个ThreadLocal
 *      ThreadLocalMap中的Key为ThreadLocal对象，value为Threadlocal的值
 *
 *      ThreadLocalMap:
 *
 *      |ThreadLocal|value|
 *      |ThreadLocal|value|
 *      |ThreadLocal|value|
 *
 */
public class ThreadLocalTest1 {

    static ThreadLocal<String> localVar = new ThreadLocal<>();

    public static void main(String[] args) {

        Thread t1  = new Thread(() -> {
                //设置线程1中本地变量的值
                localVar.set("localVar1");
                //调用打印方法
                print("thread1");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());
        });

        Thread t2  = new Thread(() -> {
                //设置线程1中本地变量的值
                localVar.set("localVar2");
                //调用打印方法
                print("thread2");
                //打印本地变量
                System.out.println("after remove : " + localVar.get());
        });

        t1.start();
        t2.start();

    }


    static void print(String name){
        //打印当前线程中本地内存中本地变量的值
        System.out.println(name + " :" + localVar.get());
        //清除本地内存中的本地变量
        localVar.remove();
    }


}

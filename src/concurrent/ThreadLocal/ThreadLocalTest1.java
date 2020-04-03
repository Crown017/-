package concurrent.ThreadLocal;


/**
 * 内存泄漏：
 *
 *      当一个对象不再有用的时候，占用的内存却不能被回收
 */
public class ThreadLocalTest1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("hello"));



    }
}

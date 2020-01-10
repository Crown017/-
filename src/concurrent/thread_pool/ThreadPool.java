package concurrent.thread_pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool {


    /**
     *
     * corePoolSize 核心线程数
     *
     * maxnumPoolSize 最大线程数
     *
     * KeepAliveTime
     *
     * TimeUint
     *
     * ThreadFactory
     *
     *
     * corePoolSize 指的是核心线程数，线程池初始化时线程数默认为 0，当有新的任务提交后，
     * 会创建新线程执行任务，如果不做特殊设置，此后线程数通常不会再小于 corePoolSize ，
     * 因为它们是核心线程，即便未来可能没有可执行的任务也不会被销毁。随着任务量的增加，在
     * 任务队列满了之后，线程池会进一步创建新线程，最多可以达到 maxPoolSize 来应对任务
     * 多的场景，如果未来线程有空闲，大于 corePoolSize 的线程会被合理回收。所以正常情
     * 况下，线程池中的线程数量会处在 corePoolSize 与 maxPoolSize 的闭区间内。
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        MyThreadFactory myThreadFactory = new MyThreadFactory("serviceA-");

        BlockingQueue blockingQueue = new ArrayBlockingQueue(10,false);

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 30, 1, TimeUnit.SECONDS, blockingQueue,myThreadFactory);
        Callable<String> callable = () -> "hello" + Thread.currentThread().getName() ;
        Future<String> future = poolExecutor.submit(callable);
        Runnable runnable = () -> System.out.println("Hello");

        Future<?> submit = poolExecutor.submit(runnable);
        System.out.println(future.get());
    }
}


class MyThreadFactory implements ThreadFactory  {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;
    private String servicePrifix = "";

    public String getServicePrifix() {
        return servicePrifix;
    }

    public void setServicePrifix(String servicePrifix) {
        this.servicePrifix = servicePrifix;
    }


    public MyThreadFactory() {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = servicePrifix + "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";

    }

    public MyThreadFactory(String servicePrifix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = servicePrifix + "pool-" +
                poolNumber.getAndIncrement() +
                "-thread-";

    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
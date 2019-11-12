package concurrent.an_ynchronized;

/**
 * JVM中的同步是由进入与退出监视器对象Monitor ,每个Java对象都存在一个与之对应的Monitor对象，随着对象创建而创建，随着对象的销毁而销毁，是由C++实现的
 *
 * 当多个线程访问同一个Monitor对象时，这些线程会被放到一个EntryList当中，当线程尝试获取Monitor对象的时，Monitor依赖与mutex lock ，当线程获取到锁，
 * 当线程调用wait方法时，当前线程会释放掉当前mutex。并将当前线程添加到WaitList 当中。等待下一次的notify跟notifyAll方法，线程执行完成也会释放掉mutex。
 *
 * 在同步锁的这种实现方式当中，因为Monitor底层实现依赖操作系统，存在用户态跟内核态的切换，会增加开销。
 *
 * EntryList 跟 WaitList 当中的线程处于阻塞状态的，阻塞是由Linux当中的pthread_mutex_lock 函数来实现的。阻塞后便进入到了内核态。
 * 这会导致内核态跟用户态来回切换，影响性能。
 *
 * 解决的方法是自旋（空转），如果Owner线程在很短的时间内执行完成，释放掉了锁，争取线程稍微等待一下，就可以得到锁，这样就避免了阻塞，
 * 跟操作系统的切换。如果超出了自旋的时间争用线程还是无法获取到锁，那么当前线程停止自选。进入到WaitList当中，进入阻塞状态，变成内核态。
 *
 * 在JDK1.5 之前synchronized 锁实现是由底层操作系统的Mutex Lock来实现的，这会导致用户态
 * 跟内核态之间的切换。严重影响锁的性能。
 *
 * 在JDK1.6 之后JVM引入了锁标志位，对synchronized 进行了优化。
 *  在JAVA对象头的MARK WORD中有几类标记，等级由低到高：
 *
 *  1.无锁
 *  2.偏向锁
 *  3.轻量级锁
 *  4.重量级锁
 *
 *
 *  偏向锁：
 *      一个线程获取第一次一个Monitor对象的时候，会在MARK WORD中存入锁的偏向锁的标志，以及当前线程的ThreadID。
 *      当线程再次获取Monitor对象的时候会直接进入方法，执行里面的代码。从而避免了用户态跟内核态的切换。
 *  1> 偏向锁的释放(偏向锁使用了一种等到竞争条件出现才释放锁的机制。当其他的线程尝试竞争偏向锁,持有偏向锁的线程才会释放锁)
 *     当有其他线程获取Monitor对象的时候，会检查是否是偏向锁，跟当前线程的ID是否与Monitor对象的存储的ID一样。
 *     如果一样则运行方法。不一样则cas操作把Thread ID设置为Null，并且升级锁的标志为轻量级锁
 *  2> 偏向锁在Java6，7 都是默认启用的，但是如果他在应用程序启动几秒钟后才激活，如果有必要可以使用JVM参数关闭延迟
 *     -XX：BiasedLoockingStartupDeLay=0
 *     关闭偏向锁:-XX:-UseBiasedLocking=flase
 */
public class SynchronizedTest {
}

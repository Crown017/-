package concurrent.test_demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 用于解决互斥的问题
 * Condition 用于解决同步问题
 */
public class ConditionTest {
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    public void test01() throws Exception{
        condition.await();
        condition.signal();
    }

}

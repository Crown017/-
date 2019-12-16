package concurrent.juc;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class JucLock {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = null;
        LockSupport support = null;


        //map
        HashMap map = new HashMap();
        //红黑树
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        //跳跃表
        ConcurrentSkipListMap skipListMap = new ConcurrentSkipListMap();
    }
}

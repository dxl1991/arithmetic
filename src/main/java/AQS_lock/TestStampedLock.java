package AQS_lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * @author dxl
 * @slogan CODE IS TRUTH
 * @date 2020/4/15 10:57
 * 对读写锁的改进，是一种乐观锁。读的时候假设没有锁，直接读，读完后通过validate()判断是否有锁，如果有就尝试获取读锁重新读，没有就直接返回值
 * StampedLock是不可重入锁，不能在一个线程中反复获取同一个锁
 */
public class TestStampedLock {
    private final StampedLock stampedLock = new StampedLock();
    private final Map<String,String> map = new HashMap<>();

    public void write(String key,String value){
        long stamp = stampedLock.writeLock(); //获取写锁
        try{
            map.put(key,value);
        }finally {
            stampedLock.unlockWrite(stamp); //释放写锁
        }
    }

    public String read(String key){
        long stamp = stampedLock.tryOptimisticRead(); // 获得一个乐观读锁
        String value = map.get(key);
        if(!stampedLock.validate(stamp)){ // 检查乐观读锁后是否有其他写锁发生
            stamp = stampedLock.readLock();  // 获取悲观读锁
            try{
                value = map.get(key);
            }finally {
                stampedLock.unlockRead(stamp); // 释放悲观读锁
            }
        }
        return value;
    }
}

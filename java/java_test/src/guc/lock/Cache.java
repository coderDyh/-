package guc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 22:20
 * @description 缓存
 */
public class Cache {
    static Map<String ,Object> map = new HashMap<String ,Object>();
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock r = lock.readLock();
    static Lock w = lock.writeLock();
    //获取key的value
    public static final Object get(String key){
        r.lock();
        try{
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    //设置key
    public static final Object put(String key,Object value){
        w.lock();
        try{
            return map.put(key,value);
        }finally {
            w.unlock();
        }
    }

    //清空
    public static final void clear(){
        w.lock();
        try {
            map.clear();
        }finally {
            w.unlock();
        }
    }
}

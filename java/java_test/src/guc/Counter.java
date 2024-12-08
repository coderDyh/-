package guc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/6 22:38
 * @description 原子操作
 */
public class Counter {
    private int i = 0;
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        final Counter cas = new Counter();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++){
            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int i = 0; i < 1000; i ++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t :ts){
            t.start();
        }
        //所有线程完成
        for (Thread t: ts){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(String.format("计数器:%s", cas.i));
        System.out.println(String.format("value:%s", cas.atomicInteger.get()));
        System.out.println(String.format("耗时：%s", System.currentTimeMillis() - start));
    }

    /**
     * 非线程安全计数器
     * */
    public void count(){
        i++;
    }

    /**
     * CAS线程安全计数器
     * */
    public void  safeCount(){
        for (;;){
            int i = atomicInteger.get();
            boolean suc = atomicInteger.compareAndSet(i , ++i);
            if (suc){
                break;
            }
        }
    }
}

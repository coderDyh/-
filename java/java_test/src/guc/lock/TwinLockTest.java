package guc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 21:04
 * @description
 */
public class TwinLockTest {
    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();
        class Worker extends Thread{
            public void run(){
                while (true){
                    lock.lock();
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    }catch (Exception e){

                    }finally {
                        lock.unlock();
                    }
                }
            }
        }
        //10个线程
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);
            w.start();
        }
        //每隔1s换行
        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println();
        }
    }
}

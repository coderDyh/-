package guc.carrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 23:20
 * @description 信号量
 */
public class SemphoreTest {
    private static final int THREAD_COUNT = 30;
    private static ExecutorService threadPool= Executors.newFixedThreadPool(THREAD_COUNT);
    private static Semaphore semaphore = new Semaphore(10);

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("i:save data ," + atomicInteger.getAndIncrement());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
        threadPool.shutdown();
    }
}

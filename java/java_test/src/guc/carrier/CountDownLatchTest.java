package guc.carrier;

import java.util.concurrent.CountDownLatch;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 22:58
 * @description
 */
public class CountDownLatchTest {
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("i: " + (i + 1));
                    countDownLatch.countDown();
                }
            }
        }).start();
        countDownLatch.await();
        System.out.println("11");
    }
}

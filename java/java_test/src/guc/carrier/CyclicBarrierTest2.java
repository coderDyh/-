package guc.carrier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 23:05
 * @description
 */
public class CyclicBarrierTest2 {
    static CyclicBarrier barrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    barrier.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();
        try {
            barrier.await();
        } catch (Exception e) {
        }
        System.out.println(2);
    }
    static class A implements Runnable {
        public void run() {
            System.out.println(3);
        }
    }
}

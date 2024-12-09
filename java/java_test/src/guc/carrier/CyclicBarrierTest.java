package guc.carrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 23:02
 * @description
 */
public class CyclicBarrierTest {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(1);
            }
        }).start();
        cyclicBarrier.await();
        System.out.println(2);
    }
}

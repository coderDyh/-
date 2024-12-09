package guc.carrier;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 23:27
 * @description 交换者 线程之间交换数据
 */
public class ExchangerTest {
    private static final Exchanger<String> exchanger = new Exchanger();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    exchanger.exchange(A);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPool.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exchanger.exchange("B");
                    System.out.println("A 和 B是否一致:" + A.equals(B) + ", A 录入的是： " + A + ", B录入是: " + B);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        threadPool.shutdown();
    }
}

package guc.carrier;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 22:55
 * @description 协作
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println("t1 finish");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("t2 finish");
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("all thread finish");
    }
}

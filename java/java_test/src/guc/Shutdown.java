package guc;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 18:57
 * @description 终止线程
 */
public class Shutdown {
    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println("Count i = " + i);
        }

        public void cancel(){
            on = false;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        //sleep
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        //sleep
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }
}

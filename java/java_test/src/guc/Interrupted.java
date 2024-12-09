package guc;

import java.util.concurrent.TimeUnit;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 18:16
 * @description 线程中断
 */
public class Interrupted {
    public static void main(String[] args) throws Exception{
        Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
        sleepThread.setDaemon(true);

        Thread runner = new Thread(new BusyRunner(), "busyRunner");
        runner.setDaemon(true);
        runner.start();

        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        runner.interrupt();
        System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
        System.out.println("BusyRunner interrupted is " + runner.isInterrupted());

        TimeUnit.SECONDS.sleep(2);
    }

    static class SleepRunner implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class BusyRunner implements Runnable{

        @Override
        public void run() {
            while(true){}
        }
    }
}

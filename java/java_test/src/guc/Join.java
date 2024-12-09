package guc;

import java.util.concurrent.TimeUnit;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 19:29
 * @description 线程join
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++){
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try{
                thread.join();
            }catch (Exception e){

            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}

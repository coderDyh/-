package guc.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 19:58
 * @description
 */
public class ConnectionTest {
    static ConnectionPool pool = new ConnectionPool(10);

    //所有connection同时开始
    static CountDownLatch start = new CountDownLatch(1);

    static CountDownLatch end;

    public static void main(String[] args) throws Exception {
        int threadNum = 10;
        end = new CountDownLatch(threadNum);
        int count = 20;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        for (int i = 0; i < threadNum; i++) {
            Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionRunnerThread");
            thread.start();
        }
        start.countDown();
        end.await();
        System.out.println("total invoke: " + (threadNum * count));
        System.out.println("got connection: " + got);
        System.out.println("not got Connection: " + notGot);
    }
    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try{
                start.await();
            }catch (Exception ex){

            }
            while (count > 0){
                try{
                    //获取连接
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null){
                        try{
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else {
                        notGot.incrementAndGet();
                    }
                    connection.commit();
                }catch (Exception ex){}finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}

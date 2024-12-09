package guc.carrier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 23:10
 * @description 银行流水线
 */
public class BankWaterService implements Runnable{
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(4, this);
    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> sheetCount = new ConcurrentHashMap<>();

    private void count(){
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetCount.put(Thread.currentThread().getName(), 1);
                    try{
                        cyclicBarrier.await();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    @Override
    public void run() {
        int res = 0;
        for (Map.Entry<String, Integer> entry : sheetCount.entrySet()) {
            res += entry.getValue();
        }
        sheetCount.put("res", res);
        System.out.println(res);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService = new BankWaterService();
        bankWaterService.count();
    }
}

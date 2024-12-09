package guc.lock;

import java.util.concurrent.*;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 22:43
 * @description fork子任务
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 2;//阈值
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        }else {
            //拆分子任务
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle + 1, end);
            //执行任务
            leftTask.fork();
            rightTask.fork();
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();
            sum = leftResult + rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask countTask = new CountTask(1, 4);
        Future<Integer> result = forkJoinPool.submit(countTask);
        try{
            System.out.println(result.get());
        }catch (InterruptedException | ExecutionException e) {}
    }
}

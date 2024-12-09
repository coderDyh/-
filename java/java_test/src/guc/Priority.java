package guc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 17:42
 * @description
 */
public class Priority {
    private static volatile boolean notStatr = true;

    private static volatile boolean notEnd = true;

    public static void main(String[] args) throws Exception{
        List<Job> jobs = new ArrayList<Job>();
        for (int i = 0; i < 10; i++){
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread thread = new Thread(job, "Thread" + i);
            thread.setPriority(priority);
            thread.start();

        }
        notStatr = false;
        TimeUnit.SECONDS.sleep(3);
        notEnd = false;
        for (Job job : jobs){
            System.out.println("Job Priority :" + job.priority + "," +
                    "count :" + job.jobCount);
        }
    }

    static class Job implements Runnable{

        private int priority;

        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }
        @Override
        public void run() {
            while (notStatr){
                Thread.yield();
            }

            while(notEnd){
                Thread.yield();
                jobCount ++;
            }
        }
    }
}

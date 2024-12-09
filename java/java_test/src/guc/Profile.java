package guc;

import java.util.concurrent.TimeUnit;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 19:36
 * @description Profile
 */
public class Profile {
    private static final ThreadLocal<Long> TIME_THREAD = new ThreadLocal<Long>() {
        protected Long initValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREAD.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREAD.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profile.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Cost: " + Profile.end() + " mills");
    }
}

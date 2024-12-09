package guc.lock;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 20:16
 * @description 同步锁
 */
public class Mutex implements Lock {
    static class Sync extends AbstractQueuedSynchronizer {
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //0时获取锁
        public boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        //释放锁
        protected boolean tryRelease(int arg) {
            if(getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //返回Condition
        Condition newCondition() {return new ConditionObject();}
    }
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    public void lockInterruptibly(long timeout, TimeUnit unit) throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}

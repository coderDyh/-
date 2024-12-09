package guc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/8 22:50
 * @description 原子
 */
public class AtomicIntergerArrayTest {
    static int[] value = new int[]{1, 2};
    static AtomicIntegerArray ai = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0, 3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}

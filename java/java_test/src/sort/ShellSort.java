package sort;

import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 14:35
 * @description
 */
public class ShellSort {
    public static void shellSort(int[] arr){
        int n = arr.length;
        for (int gap = n/2; gap > 0; gap /= 2){
            //分组
            for (int i = gap; i < arr.length; i++) {
                // currentNumber 站起来，开始找位置
                int currentNumber = arr[i];
                // 该组前一个数字的索引
                int preIndex = i - gap;
                while (preIndex >= 0 && currentNumber < arr[preIndex]) {
                    // 向后挪位置
                    arr[preIndex + gap] = arr[preIndex];
                    preIndex -= gap;
                }
                // currentNumber 找到了自己的位置，坐下
                arr[preIndex + gap] = currentNumber;
            }
        }
    }
    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 1, 3, 5, 4};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

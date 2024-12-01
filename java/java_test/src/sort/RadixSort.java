package sort;

import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/1 15:05
 * @description 基数排序
 */
public class RadixSort {
    public static void radixSort(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int num : arr) {
            max = Math.max(max, num);
        }
        //计算最大数字的长度
        int maxLength = 0;
        while (max != 0){
            maxLength++;
            max /= 10;
        }
        //计数排序对数组进行排序
        int[] counting = new int[10];
        int[] res = new int[arr.length];
        int dev = 1;
        for (int i = 0; i < maxLength; i++){
            for (int num : arr){
                int radio = num / dev % 10;
                counting[radio] ++;
            }
            for (int j = 1; j < 10; j ++){
                counting[j] += counting[j - 1];
            }
            //倒序
            for (int j = arr.length - 1; j >= 0; j--){
                int radix = arr[j] / dev % 10;
                res[--counting[radix]] = arr[i];
            }
            System.arraycopy(res, 0, arr, 0, arr.length);
            Arrays.fill(counting, 0);
            dev *= 10;

        }
    }
}

package sort;

import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 17:38
 * @description 计数排序
 */
public class CountSort {
    public static void countingSort(int[] arr) {
        if (arr == null || arr.length < 2)  return;
        int max = arr[0];
        int min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        int rangeNum = max - min + 1;
        int count[] = new int[rangeNum];
        for(int ele : arr){
            count[ele - min] ++;

        }
        System.out.println(Arrays.toString(count));
        int preCount = 0;//计数
        for (int i = 0 ; i < rangeNum; i ++){
            preCount += count[i];
            count[i] = preCount - count[i];
//            System.out.println(count[i]);
        }
        System.out.println(Arrays.toString(count));

        int res[] = new int[arr.length];
        for(int ele : arr){
            res[count[ele - min]] = ele;
            count[ele - min]--;
        }
        int i = 0;
        for (int ele : res) {
            arr[i++] = ele;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 6, 12, 31, 54, 4};
        countingSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

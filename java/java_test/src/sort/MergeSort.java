package sort;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 15:38
 * @description
 */
public class MergeSort {
    public static int[] merge(int[] arr1, int[] arr2){
        int[] res = new int[arr1.length + arr2.length];
        int i = 0, j = 0;
        while(i < arr1.length && j < arr2.length){
            if(arr1[i] < arr2[j]){
                res[i+j] = arr1[i];
                i ++;
            }else {
                res[i+j] = arr2[j];
                j++;
            }
        }
        while(i < arr1.length){
            res[i+j] = arr1[i];
            i++;
        }
        while(j < arr2.length){
            res[i+j] = arr2[j];
            j++;
        }
        return res;
    }

    public static void mergeSort(int[] arr) {
        if (arr.length == 0) return;
        int[] result = mergeSort(arr, 0, arr.length - 1);
        // 将结果拷贝到 arr 数组中
        for (int i = 0; i < result.length; i++) {
            arr[i] = result[i];
        }
    }

    // 对 arr 的 [start, end] 区间归并排序
    private static int[] mergeSort(int[] arr, int start, int end) {
        // 只剩下一个数字，停止拆分，返回单个数字组成的数组
        if (start == end) return new int[]{arr[start]};
        int middle = (start + end) / 2;
        // 拆分左边区域
        int[] left = mergeSort(arr, start, middle);
        // 拆分右边区域
        int[] right = mergeSort(arr, middle + 1, end);
        // 合并左右区域
        return merge(left, right);
    }
    
    private static void merge(int[] arr, int start,  int end, int[] result) {
        int middle = (start + end) / 2;
        // 数组 1 的首尾位置
        int start1 = start;
        int end1 = middle;
        // 数组 2 的首尾位置
        int start2 = middle + 1;
        int end2 = end;
        // 用来遍历数组的指针
        int index1 = start1;
        int index2 = start2;
        // 结果数组的指针
        int resultIndex = start1;
        while (index1 <= end1 && index2 <= end2) {
            if (arr[index1] <= arr[index2]) {
                result[resultIndex++] = arr[index1++];
            } else {
                result[resultIndex++] = arr[index2++];
            }
        }
        // 将剩余数字补到结果数组之后
        while (index1 <= end1) {
            result[resultIndex++] = arr[index1++];
        }
        while (index2 <= end2) {
            result[resultIndex++] = arr[index2++];
        }
        // 将 result 操作区间的数字拷贝到 arr 数组中，以便下次比较
        for (int i = start; i <= end; i++) {
            arr[i] = result[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 1, 3, 5, 4};
        mergeSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    //原地归并
    public static void mergeSort2(int[] arr) {
        if (arr.length == 0) return;
        mergeSort2(arr, 0, arr.length - 1);
    }

    private static void mergeSort2(int[] arr, int start, int end) {
        // 只剩下一个数字，停止拆分
        if (start == end) return;
        int middle = (start + end) / 2;
        // 拆分左边区域
        mergeSort2(arr, start, middle);
        // 拆分右边区域
        mergeSort2(arr, middle + 1, end);
        // 合并左右区域
        merge2(arr, start, end);
    }

    public static void merge2(int[] arr, int start, int end) {
        int end1 = (start + end)/2;
        int start2 = end1 + 1;
        int idx1 = start;
        int idx2 = start2;
        while(idx1 <= end1 && idx2 <= end){
            if(arr[idx1] < arr[idx2]){
                idx1 ++;
            }else {
                int val = arr[idx2];
                int index = idx2;
                while (index > idx1){
                    arr[index] = arr[index - 1];
                    index --;
                }
                arr[index] = val;
                idx1 ++;
                idx2 ++;
                end1 ++;
            }
        }
    }


}

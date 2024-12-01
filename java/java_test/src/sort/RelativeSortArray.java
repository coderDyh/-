package sort;

import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/1 13:43
 * @description 相对排序
 */
public class RelativeSortArray {
    public static int[] relativeSortArray(int[] arr1, int[] arr2){
        if (arr1 == null || arr1.length < 2) return arr1;
        int min = arr1[0], max = arr1[0];
        for(int num : arr1){
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        int range = max - min + 1;
        int[] count = new int[range];
        for(int num:arr1){
            count[num-min]++;
        }
        System.out.println(Arrays.toString(count));
        int index = 0;
        int[] res = new int[arr1.length];
        for(int num : arr2){
            while (count[num-min] != 0){
                res[index++] = num;
                count[num-min] --;
            }
        }
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(count));
        System.out.println(index);
        for (int i = 0; i < count.length; i++){
            while (count[i] != 0){
                res[index++] = i + index;
                count[i] --;
            }
        }
        for (int i = 0; i < res.length; i++){
            arr1[i] = res[i];
        }
        return arr1;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = new int[]{2,1,4,3,9,6};
        int[] res = relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(res));
    }
}

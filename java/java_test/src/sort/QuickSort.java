package sort;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/11/30 13:34
 * @description
 */
public class QuickSort {

    public int[] quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        return quickSort2(nums, 0, nums.length);
    }

    private int[] quickSort2(int[] nums, int start, int end) {
        if (start >= end) {
            return nums;
        }
        int pivot = patition(nums, start, end);
        quickSort2(nums, start, pivot - 1);
        quickSort2(nums, pivot + 1, end);

        return nums;
    }

    private int patition(int[] nums, int start, int end) {
        int pivot = nums[start];
        int left = start + 1, right = end;
        while (left < right){
            while (left < right && nums[left] < pivot) left ++;
            while (left < right && nums[right] > pivot) right --;
            if(left < right){
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left ++;
                right --;
            }
        }
        if(left == right && nums[right] > pivot) right --;
        int temp = nums[right];
        nums[right] = nums[start];
        nums[start] = temp;

        return right;

    }
}

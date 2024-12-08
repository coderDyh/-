package test;

import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/5 14:26
 * @description
 *
 * 连续子数组最大和
 *
 * 输入：[-1, 1, 2, 3, -4, 0, 5, -2]
 *
 * 输出： 7 （[1, 2, 3, -4, 0, 5] ）
 */
public class Test {
    public static int maxSubArray(int[] nums){
        if(nums == null || nums.length == 0)
            return 0;
        int curSum = nums[0];
        int maxSum = nums[0];
        int starIdx = 0;
        int endIdx = 0;
        int tempIdx = 0;
        for (int i = 1; i < nums.length; i++){
            if(curSum + nums[i] > nums[i]){
                curSum += nums[i];
            }else {
                curSum = nums[i];
                tempIdx = i;
            }
            if(curSum > maxSum){
                maxSum = curSum;
                starIdx = tempIdx;
                endIdx = i;
            }

//            curSum = Math.max(curSum + nums[i], nums[i]);
//            maxSum = Math.max(maxSum, curSum);
        }
        int[] res = new int[endIdx - starIdx + 1];
        System.arraycopy(nums, starIdx, res, 0 , endIdx - starIdx + 1);
        System.out.println(Arrays.toString(res));
        return maxSum;
    }

    public  static void main(String[] args){
        int[] nums = new int[]{-1, 1, 2, 3, -4, 0, 5, -2};
        int res = maxSubArray(nums);
        System.out.println(res);
    }
}

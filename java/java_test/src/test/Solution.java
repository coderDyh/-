package test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author dengyh
 * @version 1.0
 * @date 2024/12/5 16:45
 * @description
 */
public class Solution {
    public static String getPermutation(int n, int k) {
        int fact[] = new int[n];
        fact[0] = 1;
        for (int i = 1; i < n; ++i){
            System.out.println("i:" + i);
            fact[i] = fact[i-1]*i;
        }
//        System.out.println(Arrays.toString(fact));
        k = k - 1;
        StringBuffer ans = new StringBuffer();
        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        System.out.println(Arrays.toString(fact));
        System.out.println(Arrays.toString(valid));

        for (int i = 1; i <= n; ++i){
            int order = k / fact[n - i] + 1;
            System.out.println(order);
            for (int j = 1; j <= n; ++ j){
                order -= valid[j];
                if (order == 0){
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            k %= fact[n - i];
            System.out.println("k:" + k);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        int n = 3;
        int k = 3;
        String res = getPermutation(n, k);
        System.out.println(res);
    }
}

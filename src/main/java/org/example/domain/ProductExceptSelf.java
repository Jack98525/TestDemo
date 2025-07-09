package org.example.domain;

import java.util.Stack;

public class ProductExceptSelf {

    public static void main(String[] args) {
        Stack<Object> objects = new Stack<>();
        objects.peek();

    }

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int pre = 1;
        int dre = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = pre;
            pre = pre * nums[i];
        }
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * dre;
            dre = dre * nums[i];
        }
        return ans;
    }

}

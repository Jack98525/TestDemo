package org.example.domain;

public class Removeduplicatesfromsortedarrayii {
    public static void main(String[] args) {

    }

    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int p = 2;
        int q = 2;
        while (q < n) {
            if (nums[p - 2] != nums[q]) {
                nums[p] = nums[q];
                p++;
            }
            q++;
        }
        return p;
    }

}

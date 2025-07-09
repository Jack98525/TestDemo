package org.example.domain;

public class RemoveElement {


    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;
        int i = new RemoveElement().removeElement(nums, val);
        System.out.println(i);
        System.out.println(nums);
    }

    public int removeElement(int[] nums, int val) {
        int point2 = nums.length - 1;
        for (int point1 = 0; point1 <= point2; point1++) {
            if (nums[point1] == val) {
                nums[point1] = nums[point2];
                nums[point2] = val;
                point1--;
                point2--;
            }
        }
        return point2 + 1;
    }

}

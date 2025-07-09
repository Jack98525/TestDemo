package org.example.domain;

import java.util.HashMap;

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,1,1,1,2,2};
        int i = new MajorityElement().majorityElement(nums);
        System.out.println(i);
    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer i1 = map.get(nums[i]);
            if (i1 == null) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], ++i1);
                if (i1 >= Math.ceil(nums.length /2.0)) {
                    return nums[i];
                }
            }
        }
        return 0;
    }


}

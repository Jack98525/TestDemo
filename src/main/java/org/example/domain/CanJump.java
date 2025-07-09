package org.example.domain;

public class CanJump {
    public static void main(String[] args) {

    }

    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (maxReach < i ) {
                return false;
            }
            maxReach = Math.max(i + nums[i],maxReach);
        }
        return true;
    }
}

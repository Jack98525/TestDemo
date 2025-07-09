package org.example.domain;

public class MergeSortedArray{

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int point1 = 0;
        int point2 = 0;
        int[] sortedArray = new int[m + n];
        int temp;
        while (point1 < m || point2 < n) {
            if (point1 == m) {
                temp = nums2[point2++];
            } else if (point2 == n) {
                temp = nums1[point1++];
            } else if (nums1[point1] < nums2[point2]) {
                temp = nums1[point1++];
            } else {
                temp = nums2[point2++];
            }
            sortedArray[point1 + point2 - 1] = temp;
        }

        for (int i = 0; i < m + n; i++) {
            nums1[i] = sortedArray[i];
        }
    }
}

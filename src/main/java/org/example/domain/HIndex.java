package org.example.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class HIndex {
    public static void main(String[] args) {

    }

    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }

}

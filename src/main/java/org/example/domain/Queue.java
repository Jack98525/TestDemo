package org.example.domain;

import java.util.LinkedList;

public class Queue {

    public static void main(String[] args) {

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        System.out.printf(String.valueOf(queue.poll()));

    }

}

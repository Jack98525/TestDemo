package org.example.domain;

import java.util.Stack;

public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        System.out.printf("stack: %d\n", stack.pop());

    }



}

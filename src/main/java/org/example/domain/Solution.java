package org.example.domain;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        //String s = "-1+2";
        //String s = "(-1+2) + (-1+2)";
        //String s = "(-1+2) + (-1+2+9+9+9+1+7)";
        Solution solution = new Solution();
        int calculate = solution.calculate(s);
        System.out.println(calculate);
    }

    public int calculate(String s) {
        Stack<Integer> valueQue = new Stack<>();
        Stack<Character> operationQue = new Stack<>();
        valueQue.push(0);
        String newCalString = s.replace(" ", "");
        newCalString = newCalString.replace("(-", "(0-");
        for (int i = 0; i < newCalString.length(); i++) {
            char c = newCalString.charAt(i);
            if (c == '(') {
                operationQue.push(c);
            } else if (c == ')') {
                while (operationQue.peek() != '(') {
                    calculateLatestTwo(valueQue, operationQue);
                }
                operationQue.pop();
            } else if (Character.isDigit(c)) {
                int value = c - '0';
                int j;
                for (j = ++i; j < newCalString.length(); j++) {
                    if (Character.isDigit(newCalString.charAt(j))) {
                        value = 10 * value + (newCalString.charAt(j) - '0');
                    } else {
                        break;
                    }
                }
                i = j - 1;
                valueQue.push(value);
            } else {
                if (!operationQue.isEmpty() && operationQue.peek() != '(') {
                    calculateLatestTwo(valueQue, operationQue);
                }
                operationQue.push(c);
            }

        }
        calculateLatestTwo(valueQue, operationQue);
        return valueQue.peek();
    }

    public void calculateLatestTwo(Stack<Integer> valueQue, Stack<Character> operationQue) {
        if (valueQue.isEmpty() || operationQue.isEmpty()) {
            return;
        }
        Integer valueRight = valueQue.pop();
        Integer valueLeft = valueQue.pop();
        Character operation = operationQue.pop();
        int finalValue = operation == '+' ? valueLeft + valueRight : valueLeft - valueRight;
        valueQue.push(finalValue);
    }

}

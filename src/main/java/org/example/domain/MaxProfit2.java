package org.example.domain;

public class MaxProfit2 {
    public static void main(String[] args) {


    }

    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        int totalProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            cost = Math.min(cost, prices[i]);

            profit = Math.max(profit, prices[i] - cost);

            if (i < prices.length - 1 && prices[i] > prices[i + 1]) {
                totalProfit = totalProfit + profit;
                cost = prices[i + 1];
                profit = 0;
            }
        }
        return totalProfit + profit;
    }
}

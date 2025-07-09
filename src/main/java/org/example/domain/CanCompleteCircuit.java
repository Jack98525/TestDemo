package org.example.domain;

import com.alibaba.druid.sql.visitor.functions.Char;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CanCompleteCircuit {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int i = 0;
        while (i < length) {
            int sumCost = 0;
            int sumGas = 0;
            int count = 0;
            while (count < length) {
                i = (i + count) % length;
                sumGas = sumGas + gas[i];
                sumCost = sumCost + cost[i];
                if (sumCost > sumGas) {
                    break;
                }
                count++;
            }
            if (count == length) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lengthOfLastWord(String s) {
        int len = s.length();

        int n = 0;

        for (int i = len - 1; i >= 0; i--) {
            char curr = s.charAt(i);
            // 不是空白字符就统计
            if (curr != ' ') {
                n++;
            } else if (n != 0) {
                // 遇到空白字符且已记录长度就跳过
                break;
            }
        }

        return n;
    }

    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        String finalS = "";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            s.append(strs[0].charAt(i));
            int count = 0;
            while (count < n) {
                if (!strs[count].startsWith(s.toString())) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == n) {
                finalS = s.toString();
            }
        }
        return finalS;
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        String[] s1 = new String[numRows];
        Arrays.fill(s1, "");
        for (int i = 0; i < s.length(); i++) {
            int j = (i) % (2 * numRows - 2);
            if (j >= numRows) {
                j = 2 * numRows - 2 - j;
            }
            s1[j] = s1[j] + s.charAt(i);
        }
        return String.join("", s1);

    }


    public int strStr(String haystack, String needle) {
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                i = i - j + 1;
                j = 0;
            } else {
                j++;
                i++;
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;
    }

    public int candy(int[] ratings) {
        int length = ratings.length;
        int[] candies = new int[length];
        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i + 1] + 1, candies[i]);
            }
        }
        return Arrays.stream(candies).sum() + length;
    }

    public int trap(int[] height) {
        int length = height.length;
        int[] rains = new int[length];
        for (int i = 1; i < length - 1; i++) {
            if (height[i] < (height[i - 1] + rains[i - 1])) {
                rains[i] = (height[i - 1] + rains[i - 1]) - height[i];
            }
        }
        for (int i = length - 2; i >= 1; i--) {
            if (height[i] < (height[i + 1] + rains[i + 1])) {
                rains[i] = Math.min((height[i + 1] + rains[i + 1]) - height[i], rains[i]);
            } else {
                rains[i] = 0;
            }
        }
        return Arrays.stream(rains).sum();
    }

    public boolean isSubsequence(String s, String t) {
        int p = 0;
        int q = 0;
        while (p < s.length() && q < t.length()) {
            if (s.charAt(p) == t.charAt(q)) {
                p++;
                q++;
            } else {
                q++;
            }
        }
        return p == s.length();
    }

    public int[] twoSum(int[] numbers, int target) {
        int n = numbers.length;
        int p = 0;
        int q = n - 1;
        while (p < q) {
            if (numbers[p] + numbers[q] == target) {
                return new int[]{p + 1, q + 1};
            } else if (numbers[p] + numbers[q] < target) {
                p++;
            } else {
                q--;
            }
        }
        return null;
    }


    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int maxWater = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                maxWater = Math.max(maxWater, height[left] * (right - left));
                left++;
            } else {
                maxWater = Math.max(maxWater, height[right] * (right - left));
                right--;
            }
        }
        return maxWater;
    }


    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        int sum = 0;
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            if (nums[i] > 0) {
                break;
            }
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return lists;
    }



    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int count = 1;
        int[] sums = new int[n];
        while (count <= n) {
            for (int i = 0; i <= n - count; i++) {
                sums[i] = sums[i] + nums[i + count - 1];
                if (sums[i] >= target) {
                    return count;
                }
            }
            count++;
        }
        return 0;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int start = 0;
        int sum = 0;
        int count = Integer.MAX_VALUE;
        for (int end = 0; end < n; end++) {
            sum = sum + nums[end];
            while (sum >= target) {
                count = Math.min(count, end - start + 1);
                sum = sum - nums[start];
                start++;
            }
        }
        return count == Integer.MAX_VALUE ? 0 : count;
    }

    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 4, 5};
        System.out.println(new CanCompleteCircuit().lengthOfLongestSubstring("abcabcbb"));

    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxStrlen = 0;
        int start = 0;
        ArrayList<Character> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!nums.contains(s.charAt(i))) {
                nums.add(s.charAt(i)); // 将字符追加到列表末尾
                maxStrlen = Math.max(maxStrlen, nums.size());
            } else {
                while (nums.contains(s.charAt(i))) {
                    nums.remove(0); // 移除列表的第一个元素
                    start++;
                }
                nums.add(s.charAt(i)); // 将字符追加到列表末尾
            }
        }
        return maxStrlen;
    }
}

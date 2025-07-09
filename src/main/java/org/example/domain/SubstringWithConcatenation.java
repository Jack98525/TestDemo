package org.example.domain;

import jdk.dynalink.NamedOperation;
import jdk.jfr.Frequency;

import java.util.*;

public class SubstringWithConcatenation {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length(); // 每个单词的长度
        int wordCount = words.length; // 单词的数量
        int totalLength = wordLength * wordCount; // 串联子串的总长度

        // 统计 words 中每个单词的频率
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        for (String word : words) {
            wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
        }

        // 遍历字符串 s，分成 wordLength 个窗口
        for (int i = 0; i < wordLength; i++) {
            int left = i; // 窗口左指针
            int right = i; // 窗口右指针
            int count = 0; // 当前窗口内匹配的单词数量
            HashMap<String, Integer> currentCountMap = new HashMap<>();

            while (right + wordLength <= s.length()) {
                // 取出窗口右侧的单词
                String word = s.substring(right, right + wordLength);
                right += wordLength;

                if (wordCountMap.containsKey(word)) {
                    currentCountMap.put(word, currentCountMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 如果某个单词的频率超过了 wordCountMap 中的频率，收缩窗口
                    while (currentCountMap.get(word) > wordCountMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLength);
                        currentCountMap.put(leftWord, currentCountMap.get(leftWord) - 1);
                        left += wordLength;
                        count--;
                    }

                    // 如果窗口内的单词数量等于 words 的数量，记录起始索引
                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    // 如果单词不在 words 中，清空窗口
                    currentCountMap.clear();
                    count = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    /*    public static void main(String[] args) {
     *//* SubstringWithConcatenation solution = new SubstringWithConcatenation();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(solution.findSubstring(s, words)); // 输出: [0, 9]*//*

        SubstringWithConcatenation substringWithConcatenation = new SubstringWithConcatenation();
        System.out.println(substringWithConcatenation.isIsomorphic("foo", "bar"));
    }*/

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (map.containsKey(t.charAt(i))) {
                if ((map.get(t.charAt(i)) != s.charAt(i))) {
                    return false;
                }
            } else {
                map.put(t.charAt(i), s.charAt(i));
            }

            if (map2.containsKey(s.charAt(i))) {
                if ((map2.get(s.charAt(i)) != t.charAt(i))) {
                    return false;
                }
            } else {
                map2.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        } else {
            return climbStairs(n - 2) + climbStairs(n - 1);
        }
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty() || stack.peek() != '(') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == ']') {
                if (stack.empty() || stack.peek() != '[') {
                    return false;
                } else {
                    stack.pop();
                }
            } else if (c == '}') {
                if (stack.empty() || stack.peek() != '{') {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        return stack.empty();
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        int nowSum = 0;
        return this.calculateSum(root, targetSum, nowSum);
    }

    public boolean calculateSum(TreeNode root, int targetSum, int nowSum) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null) {
            return (nowSum + root.val) == targetSum;
        } else {
            return calculateSum(root.left, targetSum, nowSum + root.val) || calculateSum(root.right, targetSum, nowSum + root.val);
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode();
        ListNode listNodeTemp = listNode;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                listNode.next = new ListNode(list2.val);
                listNode = listNode.next;
                list2 = list2.next;
            } else if (list2 == null) {
                listNode.next = new ListNode(list1.val);
                listNode = listNode.next;
                list1 = list1.next;
            } else {
                if (list1.val <= list2.val) {
                    listNode.next = new ListNode(list1.val);
                    listNode = listNode.next;
                    list1 = list1.next;
                } else {
                    listNode.next = new ListNode(list2.val);
                    listNode = listNode.next;
                    list2 = list2.next;
                }
            }
        }
        return listNodeTemp.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /*public static void main(String[] args) {
        // 创建链表 l1 = [1, 2, 4]
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        // 创建链表 l2 = [1, 3, 4]
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        SubstringWithConcatenation solution = new SubstringWithConcatenation();
        ListNode result = solution.mergeTwoLists(l1, l2);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出: 1 1 2 3 4 4
    }*/


    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode listNode = new ListNode();
        ListNode listNodeTemp = listNode;
        ListNode temp = head;
        left = left - 1;
        right = right - 1;
        ArrayList<ListNode> listNodes = new ArrayList<>();
        while (head != null) {
            listNodes.add(new ListNode(head.val));
            head = head.next;
        }
        int count = 0;
        while (temp != null) {
            if (count >= left && count <= right) {
                int i = left + right - count;
                listNode.next = listNodes.get(i);
            } else {
                listNode.next = listNodes.get(count);
            }
            count++;
            listNode = listNode.next;
            temp = temp.next;
        }
        return listNodeTemp.next;
    }

    public static void main(String[] args) {
        // 创建链表：1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        SubstringWithConcatenation solution = new SubstringWithConcatenation();
        ListNode result = solution.reverseBetween(head, 2, 4);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        // 输出: 1 4 3 2 5
    }


    public ListNode reverseKGroup(ListNode head, int k) {

        // 创建一个虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p0 = dummy;
        int count = 1;
        while (head.next != null) {
            count++;
            head = head.next;
        }

        while (count >= k) {
            count = count - k;
            ListNode current = p0.next;
            ListNode pre = null;
            ListNode next;
            for (int i = 0; i < k; i++) {
                next = current.next;
                current.next = pre;
                pre = current;
                current = next;
            }
            next = p0.next;
            p0.next.next = current;
            p0.next = pre;
            p0 = next;
        }

        return dummy.next; // 返回反转后的链表
    }


    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode right = new ListNode(-1);
        ListNode left = new ListNode(-1);
        right.next = head;
        left.next = head;
        ListNode dummy = left;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = new ListNode(-1);
        cur.next = head;
        ListNode dummy = cur;
        while (cur.next != null && cur.next.next != null) {
            int val = cur.next.val;
            if (cur.next.next.val == val) {
                while (cur.next != null && cur.next.val == val) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }


    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null) {
            return head;
        }
        ListNode cur = new ListNode(-1);
        ListNode cur2 = new ListNode(-1);
        cur.next = head;
        cur2.next = head;
        ListNode dummy = cur;
        int count = 0;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }
        k = k % count;
        for (int i = 0; i < count - k; i++) {
            cur2 = cur2.next;
        }
        cur.next = dummy.next;
        dummy.next = cur2.next;
        cur2.next = null;
        return dummy.next;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p != null && q != null) {
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = null;
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            this.invertTree(root.left);
            this.invertTree(root.right);
        }
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root != null) {
            return root.left.val == root.right.val;
        }
        return isSymmetric(root.left) && isSymmetric(root.right);
    }

    public void flatten(TreeNode root) {
        if (root != null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode tempRight = root.right;
        root.right = root.left;
        root.left = null;
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = tempRight;
    }

    public List<Integer> preorderTraversal1(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Stack<Integer> ints = new Stack<>();
        ints.add(root.val);
        addToStack(root, ints);
        return ints.stream().toList();
    }

    public void addToStack(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            stack.add(root.left.val);
        }
        addToStack(root.left, stack);
        if (root.right != null) {
            stack.add(root.right.val);
        }
        addToStack(root.right, stack);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
    }

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<Integer> sumList = new ArrayList<>();
        int sum = 0;
        return cal(root, sum, sumList);
    }

    public int cal(TreeNode root, int sum, ArrayList<Integer> sumList) {
        if (root.left == null && root.right == null) {
            sumList.add(sum * 10 + root.val);
        }
        if (root.left != null) {
            cal(root.left, sum * 10 + root.val, sumList);
        }
        if (root.right != null) {
            cal(root.right, sum * 10 + root.val, sumList);
        }
        return sumList.stream().mapToInt(Integer::intValue).sum();
    }

    public int countNodes(TreeNode root) {
        return count(root);
    }


    public int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + this.count(root.left) + this.count(root.right);
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        } else {
            return right;
        }
    }


    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0; // 如果没有房屋，返回 0
        }
        if (nums.length == 1) {
            return nums[0]; // 如果只有一个房屋，返回该房屋的金额
        }

        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && words.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];


    }

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, 0, result);
        return result;
    }

    private void dfs(TreeNode root, int depth, List<Integer> result) {
        if (root == null) {
            return;
        }
        if (depth == result.size()) {
            result.add(root.val);
        }
        this.dfs(root.right, depth + 1, result);
        this.dfs(root.left, depth + 1, result);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> nodeLinkedList = new LinkedList<>();
        nodeLinkedList.offer(root);
        while (!nodeLinkedList.isEmpty()) {
            int size = nodeLinkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeLinkedList.poll();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    nodeLinkedList.add(node.left);
                }
                if (node.left != null) {
                    nodeLinkedList.add(node.right);
                }
            }
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> nodeLinkedList = new LinkedList<>();
        nodeLinkedList.offer(root);
        while (!nodeLinkedList.isEmpty()) {
            int size = nodeLinkedList.size();
            int sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeLinkedList.poll();
                sum = sum + node.val;
                if (i == size - 1) {
                    result.add((double) sum / size);
                }
                if (node.left != null) {
                    nodeLinkedList.offer(node.left);
                }
                if (node.right != null) {
                    nodeLinkedList.offer(node.right);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> nodeLinkedList = new LinkedList<>();
        ArrayList<Integer> tempList = new ArrayList<>();
        nodeLinkedList.offer(root);
        tempList.add(root.val);
        result.add(tempList);
        while (!nodeLinkedList.isEmpty()) {
            tempList = new ArrayList<>();
            int size = nodeLinkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeLinkedList.poll();
                tempList.add(node.val);
                if (i == size - 1) {
                    result.add(tempList);
                }
                if (node.left != null) {
                    nodeLinkedList.offer(node.left);
                }
                if (node.right != null) {
                    nodeLinkedList.offer(node.right);
                }
            }
        }
        return result;
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> nums = new ArrayList<>();
        this.inOrder(root, nums);
        return nums.get(k - 1);
    }

    private void inOrder(TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }

        // 中序遍历左子树
        inOrder(node.left, list);

        // 处理当前节点
        list.add(node.val);
        // 中序遍历右子树
        inOrder(node.right, list);
    }
}

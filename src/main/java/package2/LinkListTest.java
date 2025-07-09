package package2;

import org.example.domain.MaxProfit2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LinkListTest {
    public static void main(String[] args) {

    }

    public class ListNode {
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

    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode listNode = new ListNode();
        ListNode temp = listNode;
        while (list1 != null || list2 != null) {
            if (list1 == null) {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
                temp = temp.next;
            } else if (list2 == null) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
                temp = temp.next;
            } else {
                if (list1.val < list2.val) {
                    temp.next = new ListNode(list1.val);
                    list1 = list1.next;
                } else {
                    temp.next = new ListNode(list2.val);
                    list2 = list2.next;
                }
                temp = temp.next;
            }
        }
        return listNode.next;

    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val < list2.val) {
            list1.next = mergeTwoLists1(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists1(list1, list2.next);
            return list2;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int point1 = 0;
        int point2 = 0;
        int[] newArray = new int[m + n];
        int temp;
        while (point1 < m || point2 < n) {
            if (point1 == m) {
                temp = nums2[point2++];
            } else if (point2 == n) {
                temp = nums1[point1++];
            } else {
                if (nums1[point1] < nums2[point2]) {
                    temp = nums1[point1++];
                } else {
                    temp = nums2[point2++];
                }
            }
            newArray[point1 + point2 - 1] = temp;
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = newArray[i];
        }
    }

    public int removeElement(int[] nums, int val) {
        int p0 = 0;
        int p1 = nums.length - 1;
        int temp;
        while (p0 <= p1) {
            if (nums[p0] == val) {
                temp = nums[p0];
                nums[p0] = nums[p1];
                nums[p1] = temp;
                p1--;
            } else {
                p0++;
            }
        }
        return p1 + 1;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q <= nums.length - 1) {
            if (nums[p] != nums[q]) {
                nums[p + 1] = nums[q];
                p++;
            }
            q++;
        }
        return p + 1;
    }

    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int p = 2;
        int q = 2;
        while (q < n) {
            if (nums[p - 2] != nums[q]) {
                nums[p] = nums[q];
                p++;
            }
            q++;
        }
        return p;

    }

    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer i1 = map.get(nums[i]);
                map.put(nums[i], ++i1);
                if (i1 >= Math.ceil(nums.length / 2.0)) {
                    return nums[i];
                }
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }


    public int lengthOfLastWord(String s) {
        int len = s.length();
        int n = 0;
        for (int i = len - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c != ' ') {
                n++;
            } else if (n != 0) {
                break;
            }
        }
        return n;

    }

    public String longestCommonPrefix(String[] strs) {
        int n = strs.length;
        String finalS = "";
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            strBuilder.append(strs[0].charAt(i));
            int count = 0;
            while (count < n) {
                if (!strs[count].startsWith(strBuilder.toString())) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == n) {
                finalS = strBuilder.toString();
            }
        }
        return finalS;
    }

    public int strStr(String haystack, String needle) {
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                i = i - j + 1;
                j = 0;
            } else {
                i++;
                j++;
            }
            if (j == needle.length()) {
                return i - j;
            }
        }
        return -1;

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummyNode = head;
        ListNode temp = head.next;
        dummyNode.next = null;
        while (temp != null) {
            ListNode temp2 = temp.next;
            temp.next = dummyNode;
            dummyNode = temp;
            temp = temp2;
        }
        return dummyNode;


    }

    public ListNode reverseList2(ListNode head) {
        ListNode dummyNode = null;
        ListNode p0 = head;
        while (p0 != null) {
            ListNode next = p0.next;
            p0.next = dummyNode;
            dummyNode = p0;
            p0 = next;
        }
        return dummyNode;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA.next != null ? pA.next : headB;
            pB = pB.next != null ? pB.next : headA;
        }
        return pA;

    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;


    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public ListNode reverseList3(ListNode head) {
        ListNode dummyNode = null;
        ListNode p0 = head;
        while (p0 != null) {
            ListNode temp = p0.next;
            p0.next = dummyNode;
            dummyNode = p0;
            p0 = temp;
        }
        return dummyNode;
    }

    public ListNode mergeTwoLists3(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            list1.next = mergeTwoLists3(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists3(list1, list2.next);
            return list2;
        }
    }

    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<>();
        ListNode currentNode = head;
        while (currentNode != null) {
            nums.add(currentNode.val);
            currentNode = currentNode.next;
        }

        int front = 0;
        int back = nums.size() - 1;
        while (front < back) {
            if (nums.get(front) != nums.get(back)) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    public void inOrder(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);

    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            TreeNode temp = null;
            temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    private int maxDiameter = 0; // 全局变量，用于记录最大直径

    public int diameterOfBinaryTree(TreeNode root) {
        calculateDepth(root);
        return maxDiameter;
    }

    private int calculateDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = calculateDepth(node.left);
        int rightDepth = calculateDepth(node.right);
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildBST(nums, 0, nums.length - 1);
    }

    private TreeNode buildBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = buildBST(nums, left, middle - 1);
        root.right = buildBST(nums, middle + 1, right);
        return root;
    }

    public int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < nums.length; i++) {
            int i1 = target - nums[i];
            if (map.containsKey(i1)) {
                return new int[]{map.get(i1), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }




}
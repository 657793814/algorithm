package com.liuzd.soft.algorithm.acm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.liuzd.soft.algorithm.utils.PrintUtils.printList;

/**
 * 遍历二叉树
 *
 * @author: liuzd
 * @date: 2025/9/13
 * @email: liuzd2025@qq.com
 * @desc
 */
public class BinaryTree {
    public static class TreeNode {
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

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        middleSearchTree(list, root);
        return list;
    }

    /**
     * 中序遍历 左中右
     * 前：根-左-右
     * 中：左-根-右
     * 后：左-右-根
     *
     * @param list
     * @param node
     */
    public static void middleSearchTree(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            middleSearchTree(list, node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            middleSearchTree(list, node.right);
        }
    }

    /**
     * 层序遍历
     *
     * @param list
     * @param levelNodes
     */
    public static void levelSearchTree(List<List<Integer>> list, List<TreeNode> levelNodes) {
        if (levelNodes == null || levelNodes.size() == 0) {
            return;
        }
        List<Integer> levelValues = new ArrayList<>();
        List<TreeNode> nextLevelNodes = new ArrayList<>();
        levelNodes.stream().forEach(node -> {
            if (node == null) {
                return;
            }
            levelValues.add(node.val);
            if (node.left != null) {
                nextLevelNodes.add(node.left);
            }
            if (node.right != null) {
                nextLevelNodes.add(node.right);
            }
        });
        if (levelValues == null || levelValues.size() == 0) {
            return;
        }
        list.add(levelValues);
        levelSearchTree(list, nextLevelNodes);
    }

    /**
     * 层序遍历 (使用队列实现)
     *
     * @param node
     * @return
     */
    public static List<Integer> levelSearchTree(TreeNode node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            list.add(n.val);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
        return list;
    }

    public static int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = maxDepth(node.left);
        int right = maxDepth(node.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 二叉树最小深度
     * 每个根节点记录自己的最小深度
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        //这里需要左右子节点判空
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;

    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(1);
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        printList(inorderTraversal(node), 1);

        List<List<Integer>> list = new ArrayList<>();
        List<TreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(node);
        levelSearchTree(list, levelNodes);
        printList(list, 1);

        printList(levelSearchTree(node), 1);


    }
}

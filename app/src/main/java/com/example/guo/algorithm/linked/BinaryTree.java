package com.example.guo.algorithm.linked;

public class BinaryTree {

    public static void main(String[] args) {
        new BinaryTree();
    }

    public BinaryTree() {
        createBinaryTree();
    }

    /**
     * 前序 中左右
     */
    public void preOrderTraveral(Node root) {

        if (root == null) {
            return;
        }

        System.out.print(root.value);
        preOrderTraveral(root.left);
        preOrderTraveral(root.right);
    }

    /**
     * 中序 左中右
     */
    public void midOrderTraveral(Node root) {

        if (root == null) {
            return;
        }

        preOrderTraveral(root.left);
        System.out.print(root.value);
        preOrderTraveral(root.right);
    }

    /**
     * 后序 左右中
     */
    public void postOrderTraveral(Node root) {

        if (root == null) {
            return;
        }

        preOrderTraveral(root.left);
        preOrderTraveral(root.right);
        System.out.print(root.value);
    }

    public void createBinaryTree(){
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");

        nodeA.left = nodeB;
        nodeA.right = nodeC;
        nodeB.left = nodeD;
        nodeB.right = nodeE;
        nodeC.left = nodeF;
        nodeC.right = nodeG;

        preOrderTraveral(nodeA);
        System.out.println();
        midOrderTraveral(nodeA);
        System.out.println();
        postOrderTraveral(nodeA);

    }

    private static class Node {
        public String value;
        public Node left;
        public Node right;

        public Node(String value) {
            this.value = value;
        }
    }
}

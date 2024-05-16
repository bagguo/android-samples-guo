package com.example.android_lesson.algorithm.linked;

public class TraversalLinkedList {

    public static void main(String[] args) {
        new TraversalLinkedList();
    }

    public TraversalLinkedList() {
        createNode();
    }

    /**
     * 循环遍历
     */
    public void getDataByLoop(Node node) {
        while (node != null) {
            System.out.println(node.getData());
            node = node.getNext();
        }
    }

    /**
     * 递归遍历
     */

    public void getDataByRecursion(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.getData());
        getDataByRecursion(node.getNext());
    }

    public void createNode() {
        Node root = new Node("头结点");
        Node node1 = new Node("子节点1");
        Node node2 = new Node("子节点2");

        root.setNext(node1);
        node1.setNext(node2);

        getDataByLoop(root);

        getDataByRecursion(root);
    }

    static class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public String getData() {
            return data;
        }
    }
}

package com.example.guo.algorithm.traverse_reverse;

public class MyLinkedList {

    public Node head;
    public Node current;

    public void add(int data) {

        //如果头结点为空,为头结点
        if(head == null) {
            head = new Node(data);
            current = head;
        } else {
            current.next = new Node(data);
            current = current.next;
        }
    }

    //打印链表
    public void print(Node node) {
        if(node == null) {
            return;
        }

        current = node;
        while(current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    //初始化链表,并且返回表头
    public Node init() {
        for(int i=0; i<10; i++) {
            this.add(i);
        }
        return head;
    }

    //求链表长度
    public int get_length(Node head) {
        if (head == null) {
            return -1;
        }

        int length = 0;
        current = head;
        while(current != null) {
            length++;
            current = current.next;
        }

        return length;
    }

}
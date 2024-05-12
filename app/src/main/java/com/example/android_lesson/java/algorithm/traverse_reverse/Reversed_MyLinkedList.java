package com.example.android_lesson.java.algorithm.traverse_reverse;

public class Reversed_MyLinkedList {

    public static Node reversed_linkedlist() {
        MyLinkedList list = new MyLinkedList();
        Node head = list.init();

        if(head == null || head.next == null) {
            return head;
        }

        //使用三个节指针
        Node current = head;
        Node newHead = null;
        Node next = null;

        while(current != null) {
            //先将当前节点的下个节点保存
            next = current.next;

            current.next = newHead; //将原来的链表断链,将current的下一个结点指向新链表的头结点
            newHead = current; //将current设为新表头

            current = next; //将之前保存的next设为下一个节点
        }
        return newHead;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        Node head = reversed_linkedlist();
        System.out.println("After reversed, the list is: ");
        list.print(head);
    }
}
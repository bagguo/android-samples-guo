package com.example.guo.algorithm.creat_traverse;

public class TestLinked {
    public static void main(String[] args) {  
        MyNode root = new MyNode("头节点");
        MyNode node1 = new MyNode("子节点1");
        MyNode node2 = new MyNode("子节点2");

        root.setNext(node1);
        node1.setNext(node2);
        getDataByLoop(root);
        
    }  

    public static void getDataByLoop(MyNode node){
        while(node != null) {
            System.out.print(MyNode.data);
            node = node.getNext();
        }
    }

    static class MyNode{
        static String data = null;
        static MyNode next = null;
    
        public MyNode (String data){
            MyNode.data = data;
        }
    
        public void setNext(MyNode next){
            MyNode.next = next;
    
        }
    
        public MyNode getNext(){
            return next;
        }
    
        public String getData(){
            return data;
        }
    }
}  
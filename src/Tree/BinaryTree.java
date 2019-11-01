package Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    Node root=new Node(-999,"默认根节点");

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void build(List<Node> nodes){
    }

    //中左右
    public void preOrder(){
        this.root.preOrder();
    }
    public Node preOrder(int id){
        return this.root.preOrder(id);
    }


    //左中右
    public void infixOrder(){
        this.root.infixOrder();
    }
    public Node infixOrder(int id){
        return this.root.infixOrder(id);
    }

    //左右中
    public void postOrder(){
        this.root.postOrder();
    }
    public Node postOrder(int id){
        return this.root.postOrder(id);
    }



}

class BinaryTest{
    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        Node node1 =new Node(1,"0");
        Node node2 =new Node(2,"1");
        Node node3 =new Node(3,"2");
        Node node4 =new Node(4,"3");
        Node node5 =new Node(5,"4");

        //手动构建
        binaryTree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node5);
        node3.setRight(node4);

        System.out.println("###########");
//        Node theNode = binaryTree.preOrder(2);
        Node theNode = binaryTree.postOrder(5);
        System.out.println(theNode);


    }
}
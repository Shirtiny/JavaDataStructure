package LinkedList;

public class SingleCircleLinkedList {
    private Node first=null;//指向第一个节点
    private Node temp=first;//遍历用的节点，初始为第一个节点


    public void addNode(Node node){
        temp=first;
        while (true){
        if (first==null) {//第一个节点为空，说明链表中没有节点
            node.setNext(node);//单节点环形
            this.first = node;
            this.temp = first;//初始化first和temp
            }else{
            temp=temp.next;//temp后移
            }
            if (temp.next==first){//temp移动后，如果temp.next为first，则说明链表已经遍历到尾部
                temp.setNext(node);
                node.setNext(first);//构造环形
                break;
            }

        }
    }

    public void show(){//打印链表
        if (first==null){
            System.out.println("链表为空");
        }else {
            temp=first;//初始化temp
            while (true){
                System.out.println(temp);
                if (temp.next==first){
                    break;
                }
                temp=temp.next;//temp后移
            }
        }

    }


}

//测试
class SingleCircleLinkedListTest{
    public static void main(String[] args) {
        SingleCircleLinkedList singleCircleLinkedList=new SingleCircleLinkedList();
        singleCircleLinkedList.addNode(new Node(1,"1hao"));
        singleCircleLinkedList.addNode(new Node(3,"3hao"));
        singleCircleLinkedList.addNode(new Node(4,"4hao"));
        singleCircleLinkedList.addNode(new Node(6,"6hao"));

        singleCircleLinkedList.show();
    }
}
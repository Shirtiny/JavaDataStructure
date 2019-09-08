package LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DoublyLinkedListTest {//双向链表的测试类

    public static void main(String[] args) {
        DoublyLinkedList linkedList=new DoublyLinkedList();
        linkedList.addNodeByOrder(new Node(1,"1hao"));
        linkedList.addNodeByOrder(new Node(3,"3hao"));
        linkedList.addNodeByOrder(new Node(2,"2hao"));
        linkedList.addNodeByOrder(new Node(4,"4hao"));
//        linkedList.show();
        List<Node> nodes = getNodes(linkedList);
        DoublyLinkedList list=new DoublyLinkedList();
        list.addNodesByOrder(nodes);
        list.show();


    }



    //反转单向链表
    public static void  reverseLinkedList(DoublyLinkedList list){
        Node headNode = list.getHeadNode();
        Node temp;
        if (headNode.next==null||headNode.next.next==null){
            System.out.println("当前链表为空，或只有一个节点，不需要反转");
            return;
        }

        temp=headNode.next;//初始化temp为第一个有效节点
        Node nextNode;//存储当前节点的下一个节点
        Node newHead =new Node();//新的头结点
        while (true){
            if (temp==null){//循环到temp为空
                break;
            }
            nextNode=temp.next;//把当前节点的下一个节点存起来
            temp.next=newHead.next;//把新head的后一位节点，交给当前节点的next域
            newHead.next=temp;//把当前节点，交给新head的next域
            temp=nextNode;//让temp回到原链表上，后移一位
        }
        headNode.next=newHead.next;//把新合成的链表的第一个有效节点，交给原来的head的next域

    }

    //逆序打印单向链表
    public static void reversePrintList(DoublyLinkedList list){
        Node headNode = list.getHeadNode();
        Node temp=headNode.next;
        if (list.isNonNode()){
            System.out.println("链表为空");
        }else {
            Stack<Node> nodeStack=new Stack<>();//创建栈对象
            while (true){
                if (temp==null){
                    break;
                }
                nodeStack.push(temp);//把节点压入栈
                temp=temp.next;//节点后移
            }
            while (nodeStack.size()>0){
                System.out.println(nodeStack.pop());//从栈中取出节点并打印
            }

        }
    }

    //合并两个有序的单链表，使之仍然有序
    public static DoublyLinkedList compound2LinkedList(DoublyLinkedList list1,DoublyLinkedList list2){
        Node temp1= list1.temp=list1.headNode.next;//第一个有效节点
        Node temp2 =list2.temp=list2.headNode;//头结点
        //把链1放入链2中
        Node head2= list2.headNode;//2链的head，没用到
        Node next1;//1链的临时存储节点
        while (true){
            if (temp1==null&&temp2.next==null){
                break;
            }
            if (temp1!=null){
                next1=temp1.next;//临时保存1链当前节点的下一个节点
                if (temp2.next==null){//temp2.next走到头，也没发现大于temp1的数
                    System.out.println("此后的链2一直小于链1的某个数，由于是有序列表，所以此后链2一直小于链1，直接将把链1放在链2的后面即可");
                    temp2.next=temp1;//把当前temp1的值，挂在temp2的尾部
                    break;
                }
                if (temp2.next.id>=temp1.id){
                    temp1.next=temp2.next;
                    temp2.next=temp1;
                    temp1=next1;//直到temp2符合条件，temp1才后移
                }
            }
            temp2=temp2.next;//temp2始终后移，注意，此时的temp2这条链应该为部分list1与全部list2的混合

        }

        return list2;
    }



    //取节点集合
    public static List<Node> getNodes(DoublyLinkedList list){
        Node temp=list.temp=list.headNode.next;//初始化temp为该链表的第一个有效节点
        List<Node> nodeList=new ArrayList<>();//新建一个node集合
        Node next=new Node();//初始一个next对象用于存储temp.next
        while (true){
            if (next==null){//当next为空时，退出循环
                break;
            }
            next=temp.next;//将temp的下一个节点临时保存，即保存temp节点以后的链表
            temp.next=null;//将temp的next域设为null
            temp.pre=null;
            nodeList.add(temp);//将temp加入节点集合
            temp=next;//将temp重新回到链表上
        }
        return nodeList;
    }



    //将一个无序链表转为有序链表
    public static DoublyLinkedList formatLinkedList(DoublyLinkedList list1){
        List<Node> nodes = getNodes(list1);
        DoublyLinkedList list2=new DoublyLinkedList();
        list2.addNodesByOrder(nodes);
        return list2;
    }


}
//********************************************

    //双向链表类
 class DoublyLinkedList {

        Node headNode=new Node();//头节点
        Node temp;//设一个过渡节点，初始为第一个有效节点



        //获取头节点
        public Node getHeadNode(){
            return headNode;
        }

        public boolean isNonNode(){
            return headNode.next == null;
        }

        //添加Node(不考虑排序)
        public void addNode(Node node){

            temp=headNode;//初始化temp
            //找到尾节点
            while (true){
                //判断过渡节点的next域是否为空
                if (temp.next==null){
                    //将node加入链表
                    temp.setNext(node);
                    node.setPre(temp);

                    break;//找到next域为空的节点，便退出循环
                }else {
                    //否则temp后移一个节点
                    temp=temp.getNext();
                }
            }

        }

        //添加Node，考虑排序
        public void addNodeByOrder(Node node){
            temp=headNode;//初始化temp
            while(true){
                if (temp.next==null){//此时链表为空或已到链表末尾
                    //直接添加节点
                    temp.setNext(node);
                    node.setPre(temp);
                    break;
                }else if (temp.next.id>node.id) {//此时temp与temp.next之间的位置，便是node应该插入的位置
                    node.setPre(temp);//先让node的pre连到temp
                    node.setNext(temp.next);//node的next连到temp.next
                    temp.next.setPre(node);//然后先把temp.next的pre连到node
                    temp.setNext(node);//最后把temp的next连到node

                    break;
                }
                temp=temp.next;//以上都不满足，后移temp

            }



        }

        //按顺序批量添加节点
        public  void addNodesByOrder(List<Node> nodes){
            temp=headNode;//初始化temp
            for (Node node :nodes) {
                addNodeByOrder(node);//调用排序添加单个node的方法
            }

        }


        //删除节点
        public void deleteNode(int id){
            temp=headNode.getNext();//初始化temp为第一个有效节点
            while (true){
                if (temp==null){
                    System.out.println("链表中没找到要删除的节点");
                    break;
                }else if (temp.id==id){
                    if (temp.next==null){
                        temp.pre.setNext(null);//如果节点在最尾部
                        System.out.println("删除"+temp.id+"号节点，该节点是尾节点");
                        break;
                    }
                    temp.next.setPre(temp.pre);//把temp.next的pre连到temp.pre，中间是temp
                    temp.pre.setNext(temp.next);//把temp.pre的next连到temp.next，中间是temp
                    //此时，没有任何节点的next或pre指向temp，不被引用的对象，会被JVM垃圾回收机制自动回收
                    System.out.println("删除"+temp.id+"号节点");
                    break;
                }
                temp=temp.next;//此次循环未找到符合条件的节点，后移
            }
        }

        //修改节点
        public void updateNode(Node newNode){
            temp=headNode;
            while (true){
                if (temp.next==null){
                    System.out.println("链表中没找到要修改的节点");
                    break;
                }else if (temp.next.id==newNode.id){
                    temp.next.name=newNode.name;
                    break;
                }
                temp=temp.next;
            }
        }


        //打印链表
        public void show(){
            //当链表为空时，直接提示链表为空
            if (isNonNode()){
                System.out.println("当前单链表为空");
            }else {
                temp=headNode;//初始化temp
                while (true){
                    temp=temp.next;//temp后移
                    if (temp==null){//到尾节点的next时结束
                        break;
                    }
                    System.out.println(temp);//输出此时temp的值
                }
            }


        }

        //获取链表有效节点个数
        public int getSize(){
            temp=headNode.next;//初始化temp，注意，这次初始temp的值为头节点的下一个节点，即第一个有效节点
            int size=0;
            while (true){
                if (temp==null){
                    break;
                }
                size++;
                temp=temp.next;

            }
            return size;
        }

        //查找到倒数第n个节点
        public Node getLastIndexOf(int n){
            int size=getSize();
            if (isNonNode()||n<=0||n>size){
                System.out.println("没有找到该编号的节点");
                return null;
            }
            temp=headNode;
            int i=0;
            while (true){
                i++;
                temp=temp.next;
                if (i==(size-n+1)){//第一次循环时，i与temp为0和head，第二次时i与temp为1和第一个有效节点，所以i可以作为有效节点的坐标
                    return temp;
                }
            }
        }
        //或使用for循环
    /*
    temp=headNode.next;//这里把temp设为了第一个有效节点，i= size - index时结束循环。当然，也可以同上面while一样设为head，i= size - index+1时结束循环。
		for(int i =0; i< size - index; i++) {
        cur = cur.next;
    }
		return cur;
    */

    }







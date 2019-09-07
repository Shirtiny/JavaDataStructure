package LinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SingleLinkedList {
     Node headNode=new Node();//头结点
     Node temp;//设一个过渡节点，初始为头结点



    //获取头结点
    public Node getHeadNode(){
        return this.headNode;
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
                temp.next=node;
                break;//找到next域为空的节点，便退出循环
            }else {
                //否则temp后移一个节点
                temp=temp.next;
            }
        }

    }

    //添加Node，考虑排序
    public void addNodeByOrder(Node node){
        temp=headNode;//初始化temp
        while(true){
            if (temp.next==null){//此时链表为空或已到链表末尾
                //直接添加节点
                temp.next=node;
                break;
            }else if (temp.next.id>node.id) {//此时temp与temp.next之间的位置，便是node应该插入的位置
                node.next = temp.next;//将temp.next，变成node的后一个节点
                temp.next = node;//将node，作为temp的后一个节点
                break;
            }
//            }else if (temp.next.id==node.id){
//                System.out.println("该编号的节点已经存在");
//                break;
//            }
            temp=temp.next;//以上都不满足，后移temp

        }



    }


    //删除节点
    public void deleteNode(int id){
        temp=headNode;//初始化temp
        while (true){
            if (temp.next==null){
                System.out.println("链表中没找到要删除的节点");
                break;
            }else if (temp.next.id==id){
                temp.next=temp.next.next;//用要删除节点的后一个节点，替换要删除的节点
                //不被引用的对象，会被JVM垃圾回收机制自动回收
                System.out.println("删除"+id+"号节点");
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

    //反转单向链表
    public void  reverseLinkedList(){
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
    public void reversePrintList(){
        temp=headNode.next;
        if (isNonNode()){
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
    public SingleLinkedList compound2LinkedList(SingleLinkedList list1,SingleLinkedList list2){
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
    public List<Node> getNodes(SingleLinkedList list){
        Node temp=list.temp=list.headNode.next;//初始化temp为该链表的第一个有效节点
        List<Node> nodeList=new ArrayList<>();//新建一个node集合
        Node next=new Node();//初始一个next对象用于存储temp.next
        while (true){
            if (next==null){//当next为空时，退出循环
                break;
            }
            next=temp.next;//将temp的下一个节点临时保存，即保存temp节点以后的链表
            temp.next=null;//将temp的next域设为null
            nodeList.add(temp);//将temp加入节点集合
            temp=next;//将temp重新回到链表上
        }
        return nodeList;
    }

    //按顺序批量添加节点
    public  void addNodesByOrder(List<Node> nodes){
        temp=headNode;//初始化temp
        for (Node node :nodes) {
            addNodeByOrder(node);//调用排序添加单个node的方法
        }

    }

    //将一个无序链表转为有序链表
    public SingleLinkedList formatLinkedList(SingleLinkedList list1){
        List<Node> nodes = getNodes(list1);
        SingleLinkedList list2=new SingleLinkedList();
        list2.addNodesByOrder(nodes);
        return list2;
    }



    public static void main(String[] args) {
        //生成无序列表1
        SingleLinkedList singleLinkedList1=new SingleLinkedList();
        singleLinkedList1.addNode(new  Node(1,"1号节点"));
        singleLinkedList1.addNode(new  Node(9,"9号节点"));
        singleLinkedList1.addNode(new  Node(2,"2号节点"));
        singleLinkedList1.addNode(new  Node(4,"4号节点"));
        System.out.println("**********无序链表1");
        singleLinkedList1.show();

        //序列化无序链表1
        SingleLinkedList linkedList1 = singleLinkedList1.formatLinkedList(singleLinkedList1);
        System.out.println("**********有序链表1");
        linkedList1.show();

        //生成无序列表2
        SingleLinkedList singleLinkedList2=new SingleLinkedList();
        singleLinkedList2.addNode(new  Node(2,"2号节点"));
        singleLinkedList2.addNode(new  Node(9,"9号节点"));
        singleLinkedList2.addNode(new  Node(6,"6号节点"));
        singleLinkedList2.addNode(new  Node(1,"1号节点"));
        System.out.println("**********无序链表2");
        singleLinkedList2.show();

        //序列化无序链表2
        SingleLinkedList linkedList2 = singleLinkedList2.formatLinkedList(singleLinkedList2);
        System.out.println("**********有序链表2");
        linkedList2.show();


        //合并两个有序列表，用哪个对象都行
        SingleLinkedList list = linkedList1.compound2LinkedList(linkedList1, linkedList2);
        System.out.println("**************合并后的链表");
        list.show();


//        System.out.println(nodes);
//        SingleLinkedList format = singleLinkedList.format(nodes);
//        format.show();
//        List<Node> formatNodes = format.getNodes(format);

//        singleLinkedList.addNodeByOrder(new  Node(4,"4号节点"));
//        singleLinkedList.addNodeByOrder(new  Node(3,"3号节点"));
//        singleLinkedList.addNodeByOrder(new  Node(2,"2号节点"));
//        singleLinkedList.addNodeByOrder(new  Node(0,"1号节点"));
//        singleLinkedList.show();
//        singleLinkedList.deleteNode(1);
//        singleLinkedList.deleteNode(4);
//        singleLinkedList.deleteNode(3);
//        singleLinkedList.updateNode(new Node(3,"sanhao"));
//        singleLinkedList.show();
//        int size = singleLinkedList.getSize();
//        System.out.println("链表共"+size+"个有效节点");
//        int n=4;
//        System.out.println("倒数第"+n+"个节点为"+singleLinkedList.getLastIndexOf(n));
//        singleLinkedList.reverseLinkedList();
//        singleLinkedList.show();
//        singleLinkedList.reversePrintList();

//        SingleLinkedList list2=new SingleLinkedList();
//        list2.addNodeByOrder(new Node(1,"yi号"));
//        list2.addNodeByOrder(new Node(1,"er号"));
//        list2.addNodeByOrder(new Node(1,"ba号"));
//        list2.addNodeByOrder(new Node(1,"jiu号"));
//        list2.addNode(new Node(1,"yi"));
//        list2.addNode(new Node(2,"yi"));
//        list2.addNode(new Node(2,"yi"));
//        list2.addNode(new Node(3,"yi"));

//        System.out.println("********链表1");
//        singleLinkedList.show();
//
//        System.out.println("********链表2");
//        list2.show();
//
//        SingleLinkedList newlist = singleLinkedList.compound2LinkedList(singleLinkedList, list2);
//        System.out.println("*******合成后的链表");
//        newlist.show();

//

//



    }

}


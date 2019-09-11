package LinkedList;

import java.util.List;

public class SingleCircleLinkedList {//基础链表
    private Node first = null;//指向第一个节点
    private Node temp = first;//遍历用的节点，初始为第一个节点


    //判断链表是否为空
    public boolean isNon() {
        return first == null;
    }


    public void addNode(Node node) {//加入单个节点
        temp = first;
        while (true) {
            if (isNon()) {//第一个节点为空，说明链表中没有节点
                node.setNext(node);//单节点环形
                this.first = node;
                this.temp = first;//初始化first和temp
            } else {
                temp = temp.next;//temp后移
            }
            if (temp.next == first) {//temp移动后，如果temp.next为first，则说明链表已经遍历到尾部
                temp.setNext(node);
                node.setNext(first);//构造环形
                break;
            }
        }
    }


    public void deleteNode(int id) {//根据指定的id 删除该节点
        if (isNon()) {
            System.out.println("链表为空");
        } else {
            temp = first;//初始化
            while (true) {
                if (temp.next.id == id) {
                    if (temp.next == first) {//删除的节点为第一个节点
                        //更新first
                        first = temp.next.next;
                    }
                    temp.setNext(temp.next.next);
                    System.out.println("删除节点:" + id);
                    break;
                }
                temp = temp.next;//temp后移
                if (temp == first) {
                    System.out.println("没找到要删除的节点");
                    break;
                }

            }
        }

    }

    //根据约瑟夫问题删除节点
    public void deleteByJ(int m) {//每次循环中，第m个节点被删除
        if (isNon()) {
            System.out.println("链表为空");
        } else {
            temp = first;//初始为第一个节点
            int i = 1;//计数变量
            while (true) {
                if (temp.next == temp) {//链表中只有一个节点
                    System.out.println("最后一个节点为：" + temp);
                    break;
                }
                if (i == m - 1) {//当数到m号节点的前一个时，删除m节点
                    System.out.println(temp.next.id + "号节点出列");
                    temp.setNext(temp.next.next);//删除节点
                    first = temp.next;//把被删除节点的后一个节点作为第一个节点
                    i = 1;//重置i
                    temp = first;//重置temp为新的first
                } else {
                    temp = temp.next;//temp后移
                    i++;//计数+1
                }
            }
        }
    }


    public void show() {//打印链表
        if (first == null) {
            System.out.println("链表为空");
        } else {
            temp = first;//初始化temp
            while (true) {
                System.out.println(temp);
                if (temp.next == first) {
                    break;
                }
                temp = temp.next;//temp后移
            }
        }

    }


}

//约瑟夫环
class Josephus {
    private SingleCircleLinkedList JosephusList = new SingleCircleLinkedList();//初始队列
    private int N;//总人数
    private int M;//数到M的人出列

    public Josephus() {

    }

    public SingleCircleLinkedList getJosephusList() {
        return JosephusList;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    //给定节点集合，批量加入
    public void addNodes(List<Node> nodes) {
        int i = 1;//每次循环自增，给节点编号
        for (Node node : nodes) {
            node.setId(i++);
            JosephusList.addNode(node);
        }
    }


    //给定节点总数n，自动编号，组成一个环形
    public void autoAdd(int n) {
        N = n;
        //生成
        for (int i = 1; i <= n; i++) {
            Node node = new Node();
            node.setId(i);
            JosephusList.addNode(node);
        }
    }

    //给定数字m，从1号开始数，每次循环数到m的节点出列（删除），直到剩最后一个
    public void run(int m) {
        M = m;
        JosephusList.deleteByJ(m);
    }

    public void show() {//打印链表
        JosephusList.show();
    }


}


//测试
class SingleCircleLinkedListTest {
    public static void main(String[] args) {
        Josephus josephus = new Josephus();
        josephus.autoAdd(41);//建立一个总数41的环形链表
        josephus.run(3);//默认从1开始数，数到3的节点出列

    }
}
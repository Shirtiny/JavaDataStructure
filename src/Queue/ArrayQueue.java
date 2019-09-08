package Queue;

import java.util.Scanner;

public class ArrayQueue {

    private int MaxSize;
    private int front;
    private int rear;
    private int[] array;

    //初始化数组
    ArrayQueue(int MaxSize){

        this.MaxSize=MaxSize;
        array=new int[MaxSize];
        front=-1;
        rear=-1;

    }

    //判断队列是否满
     boolean isFull(){
        return rear==MaxSize-1;
    }

    //判断队列是否为空
    boolean isEmpty(){
        return rear==front;
    }

    //向队列增加数据
    void add(int data){

        if (isFull()){//判断队列是否已满
            System.out.println("队列已满，无法存");
        }else {
            rear++;//尾指针后移
            array[rear]=data;//赋值
            System.out.println("尾指针+1："+rear);
        }

    }

    //从队列取数据
    int get(){

       if (isEmpty()){//判断队列是否为空
           throw new RuntimeException("队列为空，无法取");
       }else {
           front++;
           System.out.println("头指针+1："+front);
           
           return array[front];//取值
       }

    }

    //显示整个队列
    void show(){
        if (isEmpty()){//判断队列是否为空
            throw new RuntimeException("队列空，无数据");
        }else {
            for (int data:array){//打印数组
             System.out.print(data+"\t");
            }
            System.out.println();
        }

    }

    //展示队列头数据
    int showFront(){
        if (isEmpty()){//判断队列是否为空
            throw new RuntimeException("队列空，无数据");
        }else {
           return array[front+1];//返回头数据，指针不变
            }
        }


    //展示队列尾数据
    int showRear(){
        if (isEmpty()){//判断队列是否为空
            throw new RuntimeException("队列空，无数据");
        }else {
            return array[rear];//返回尾数据，指针不变
        }

    }

    //测试队列
    public static void main(String[] args) {
        //创建一个队列对象，容量为3
        ArrayQueue queue = new ArrayQueue(3);
        char key;//接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            System.out.println("r(rear): 查看队列尾的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.showFront();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'r': //查看队列头的数据
                    try {
                        int rear = queue.showRear();
                        System.out.printf("队列尾的数据是%d\n", rear);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
}
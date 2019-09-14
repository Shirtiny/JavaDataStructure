package Stack;

import java.util.Scanner;

/**
 * 栈的数组方式实现
 */
public class ArrayStack {

    int MaxSize = 0;//最大容量
    int top = -1;//栈顶指针
    int base = -1;//栈底
    int[] array;//数组

    public ArrayStack(int maxSize) {//初始化栈
        MaxSize = maxSize;
        array = new int[MaxSize];
    }

    //判断栈滿
    public boolean isFull() {
        return top == MaxSize - 1;
    }

    //判断栈空
    public boolean isEmpty() {
        return top == base;
    }

    //元素入栈
    public void push(int node) {
        if (isFull()) {
            System.out.println("栈滿");
        } else {
            top++;//top上移
            array[top] = node;
        }
    }


    //元素出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        } else {
            int value = array[top];
            top--;
            return value;
        }
    }

    //显示当前的栈顶元素
    public int showTop() {
        if (isEmpty()) {
            throw new RuntimeException("为空");
        } else {
            return array[top];
        }
    }

    //打印栈
    public void show() {
        if (isEmpty()) {
            System.out.println("为空");
        } else {
            for (int i = 0; i <= top; i++) {//从0遍历到top即可
                System.out.println(array[i]);
            }
        }
    }

}

class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("showTop: 表示显示当前栈顶的数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "showTop":
                    try {
                        int res = stack.showTop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");
    }


}

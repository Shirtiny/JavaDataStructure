package Search;

import java.util.Arrays;

//斐波那契查找
public class FibonacciSearch {

    //斐波那契查找是利用斐波那契数列，进行查找
    //斐波那契查找就是利用斐波那契数列中的数作为新的mid分割点
    //这是封装后的方法
    public void fibonacciSearch(int[] array, int goal) {
        //在斐波那契数列找一个等于略大于查找表中元素个数的数F[n]
        //创建一个斐波那契数列
        int[] arrayF = createFibonacciArray(20);
        //扩展数组，以及搜索
        extendsArrayAndSearch(array, arrayF, goal);
    }


    //扩展数组，然后搜索
    public void extendsArrayAndSearch(int[] array, int[] arrayF, int goal) {
        //准备扩展数组，使array[length-1]的值为斐波那契数列中的值
        //right为array.length-1
        //扩展后的数组的rightP
        int newRightP = array.length - 1;
        //标识newRight的值是否达标
        boolean flag = true;
        //存储达标时斐波那契数列对应的下标
        int indexF = -1;
        while (true) {
            //遍历斐波那契，把newRightP和每个数都比一下
            for (int i = 0; i < arrayF.length; i++) {
                if (newRightP == arrayF[i]) {
                    flag = false;
                    indexF = i;
                    break;
                }
            }
            //达标则跳出while
            if (!flag) break;
            //自增
            newRightP++;
        }
        //拷贝数组到新数组中（扩展数组），array改变了，新数组的长度为newRightP-1
        array = Arrays.copyOf(array, newRightP);
        search(array, 0, array.length - 1, goal, arrayF, indexF);
    }


    //也就是说每次寻找到的mid满足斐波那契数列，而斐波那契数列用来描述原数组的下标
    //这是搜索方法
    public void search(int[] array, int leftP, int rightP, int goal, int[] arrayF, int indexF) {
        //找不到则返回
        if (rightP < leftP) {
            System.out.println("找不到 a");
            return;
        }
        if (indexF < 0) {
            System.out.println("找不到 f");
            return;
        }
        //此时数组的mid就是数组right对应斐波那契数列值的前一个再-1，注意此时midP的值会受leftP的影响
        int midP = leftP+arrayF[indexF - 1]-1;
        indexF=indexF-1;
        if (goal < array[midP]) {
            search(array, leftP, midP-1, goal, arrayF, indexF);
        } else if (goal > array[midP]) {
            search(array, midP+1, array[indexF+1], goal, arrayF, indexF);
        } else {
            System.out.println(goal + "的下标为" + midP + "；验证是否等于：");
            System.out.println(array[midP] == goal);
        }
    }


    //斐波那契数列是一个从第3个数开始，以后的数都等于前两个数的和，并且相邻数的比值接近0.618黄金分割点
    //比如1,1,2,3,5,8,13,21,34
    //构建一个斐波那契数列
    public int[] createFibonacciArray(int length) {
        int[] arrayF = new int[length];
        arrayF[0] = 1;
        arrayF[1] = 1;
        for (int i = 2; i < arrayF.length; i++) {
            //从第3个数开始，每个数都等于前两个数的和
            arrayF[i] = arrayF[i - 1] + arrayF[i - 2];
        }
        return arrayF;
    }


}

class FibonacciSearchTest {
    public static void main(String[] args) {
        FibonacciSearch fibonacciSearch = new FibonacciSearch();
        int[] array = {1, 2, 11, 23, 34, 78, 123, 234};
        fibonacciSearch.fibonacciSearch(array, 123);
    }
}
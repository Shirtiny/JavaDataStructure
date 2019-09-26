package Sort;

import java.util.Arrays;
import java.util.Date;

public class InsertSorting {

    //无序部分的一个数
    int disOrderNum;
    //中间量
    int temp;


    public int[] insertSort1(int[] array) {
        //从array[0]到array[n]是有序部分
        //从array[n+1]到末尾，是无序部分
        for (int n = 0; n < array.length - 1; n++) {
            //取出一个无序部分头部的数，注意，此时该位置的数据可以视为“空”
            disOrderNum = array[n + 1];
            //遍历有序部分进行比较和执行插入
            for (int i = 0; i < n + 1; i++) {
                //若取出的无序数比某个有序数小，把它放在这个有序数的前面
                if (disOrderNum < array[i]) {
                    //把有序部分最末尾的数后移1位 （相当与，此时把有序的末尾，放在无序的头部）
                    array[n + 1] = array[n];
                    //把i以后的有序数后移1位
                    for (int j = n; j > i; j--) {
                        array[j] = array[j - 1];
                    }
                    //插入无序数
                    array[i] = disOrderNum;
                    //进入下一轮循环
                    break;
                }
            }
        }
//        System.out.println(Arrays.toString(array));
        return array;
    }

    //优化
    public int[] insertSort2(int[] array) {
        //n是一个分割标识
        //从array[0]到array[n]是有序部分
        //从array[n+1]到末尾，是无序部分
        for (int n = 0; n < array.length - 1; n++) {
            //取出一个无序部分头部的数，注意，此时该位置的数据可以视为“空”
            disOrderNum = array[n + 1];
            //遍历有序部分进行比较和执行插入
            int index;
            //指针，从有序部分尾部开始遍历，此时n+1的位置是可以被有序部分利用的空间，可以视为有序部分末尾
            index = n + 1;
            while (true) {
                //当index到达有序部分头部，或待插入数找到合适位置时
                if (index == 0 || disOrderNum > array[index - 1]) {
                    //从后向前遍历，与下一个值比较，插入到当前位置
                    array[index] = disOrderNum;
                    break;
                }
                //把有序数的位置后移
                array[index] = array[index - 1];
                index--;
            }
        }
//        System.out.println(Arrays.toString(array));
        return array;
    }

    //把10万个随机数排序，测试耗时
    public void ohtTest() {

        int[] ohtArray = new int[100000];
        for (int i=0;i<ohtArray.length;i++) {
            ohtArray[i] = (int) (Math.random()* 100000) ;
        }
        System.out.println(new Date());
        insertSort2(ohtArray);
        System.out.println(new Date());
    }
}

class InsertSortingTest {
    public static void main(String[] args) {
        int[] array = {5, 7, 4, 6, 3, 1, 2, 9, 8};
        InsertSorting insertSorting = new InsertSorting();
        insertSorting.insertSort2(array);
        insertSorting.ohtTest();
    }
}
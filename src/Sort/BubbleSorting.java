package Sort;

import java.util.Arrays;
import java.util.Date;

public class BubbleSorting {

    public int[] bubbleSort(int[] array) {
        int temp;
        //标识此次排序是否发生改变
        boolean isChanged = false;
        //开始排序
        for (int n = 0; n < array.length - 1; n++) {
                //把最大的数浮动到末尾，i循环到array.length-1即可，因为i为倒数第二时，会比较array[i]和array[i+1]，
                //此时倒数第一的数已经比较过了，也防止数组越界
                for (int i = 0; i < array.length - 1 - n; i++) {
                    //开始本次冒泡前，把isChanged置为false
                    //如果前面的数比后面的数大，交换位置
                    if (array[i] > array[i + 1]) {
                        temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        //位置发生变化，把isChanged置为true
                        isChanged = true;
                    }
                }
                //for检查一遍数组后，发现isChanged的值未变化
                if (!isChanged){
                    //退出循环
                    break;
                }else {
                    //把isChanged改回来
                    isChanged=false;
                }
//                System.out.println(Arrays.toString(array));

        }
        return array;
    }


    //把10万个随机数排序，测试耗时
    public void ohtTest() {

        int[] ohtArray = new int[100000];
        for (int i=0;i<ohtArray.length;i++) {
            ohtArray[i] = (int) (Math.random()* 100000) ;
        }
        System.out.println(new Date());
        bubbleSort(ohtArray);
        System.out.println(new Date());
    }
}

class BubbleSortingTest {
    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 6, 3, 1, 2, 9, 8};
        BubbleSorting bubbleSorting = new BubbleSorting();
        int[] sorted = bubbleSorting.bubbleSort(arr);
        bubbleSorting.ohtTest();

    }
}
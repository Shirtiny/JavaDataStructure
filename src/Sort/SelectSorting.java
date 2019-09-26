package Sort;

import java.util.Arrays;
import java.util.Date;

public class SelectSorting {

    //一轮遍历中的最小值
    private int minValue;
    //最小值的下标
    private int minIndex;
    //交换位置时，需要的中间量
    private int temp;
    //标识最小数是否发生改变、
    private boolean minIsChanged=false;

    public int[] SelectSort(int[] array) {

        //遍历n轮
        for (int n = 0; n < array.length; n++) {
            //取当前轮次的数组第一个数为最小数，并记录其下标
            minIndex = n;
            minValue = array[minIndex];
            //一轮中的遍历，i从当前轮次数组的第二个数开始
            for (int i = n + 1; i < array.length ; i++) {
                //若数组中有比minValue还小的数，把它赋给minValue，并记录下标，然后标识出最小值已经改变
                if (array[i] < minValue) {
                    minIndex = i;
                    minValue = array[minIndex];
                    minIsChanged=true;
                }
            }
            //一轮遍历完毕，将得到的最小数与当前轮数组的第一个数交换位置
            //若最小值没有变化，即minIsChanged为false时，不需要交换
            if (minIsChanged){
                temp = array[minIndex];
                array[minIndex] = array[n];
                array[n] = temp;
            }
        }
        //排序完成后打印数组
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
        SelectSort(ohtArray);
        System.out.println(new Date());
    }

}
class SelectSortingTest{
    public static void main(String[] args) {
        int[] array={5, 7, 4, 6, 3, 1, 2, 9, 8};
        SelectSorting selectSorting=new SelectSorting();
        selectSorting.SelectSort(array);
        selectSorting.ohtTest();
    }
}
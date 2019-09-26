package Sort;

import java.util.Arrays;

public class QuickSorting {

    //快指针，始终移动
    int quickP = 0;
    //慢指针，当快指针满足条件时移动
    int slowP;
    //基准数、中间点
    int pivot;
    //基准数所在位置
    int pivotIndex;
    //temp交换的中间变量
    int temp;


    /**
     * 用于分割数组
     *
     * @param array 待分割数组
     * @param left  数组左端点
     * @param right 数组右端点
     */
    public int partition(int[] array, int left, int right) {
        //慢指针初始位置是左端点的前一个点
        slowP = left - 1;
        //设数组最后一个数为基准数
        pivot = array[right];
        pivotIndex = right;
        //在数组头到基准数之间遍历
        for (quickP = left; quickP < pivotIndex; quickP++) {
            //若发现当前数比基准数小
            if (array[quickP] < pivot) {
                //慢指针后移
                slowP += 1;
                //把快指针的数与慢指针的数交换位置
                temp = array[quickP];
                array[quickP] = array[slowP];
                array[slowP] = temp;
            }
        }
        //退出循环后，交换slowP+1和基准数，这样通过slowP+1把数组分为了两部分
        temp = array[slowP + 1];
        array[slowP + 1] = array[pivotIndex];
        array[pivotIndex] = temp;
        return slowP + 1;
        //于是数组分成的两部分为：[left,slowP+1-1]、[slowP+1+1,right]
    }

    /**
     *递归分割来完成排序
     * @param array 待分割数组
     * @param left  数组左端点
     * @param right 数组右端点
     */
    public void quickSort(int[] array, int left, int right) {
        //不停分割数组，直到最后分成的数组左端点=右端点
        if (left >= right) {
            return;
        }
        //接收分割完成后的中点下标，中点不再参与分割和排序
        pivotIndex = partition(array, left, right);
        //左边的数组：[left,pivotIndex-1]
        quickSort(array, left, pivotIndex - 1);
        //右边的数组：[pivotIndex+1,right]
        quickSort(array, pivotIndex + 1, right);

    }

}

//测试
class QuickSortingTest {
    public static void main(String[] args) {
        int[] array = {0, 7, 4, 6, 3, 1, 2, 9, 8, 5};
        QuickSorting quickSorting = new QuickSorting();
        quickSorting.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));

    }


}
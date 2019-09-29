package Sort;

import java.util.Arrays;

public class MergeSorting {


    /**
     * 合并方法1，排序后数据写入temp数组，然后再写回原数组
     * @param array 拆分后的原数组
     * @param temp 临时数组
     * @param left 原数组左端点
     * @param pivot 原数组中间点
     * @param right 原数组右端点
     * */
    public void merge1(int[] array, int[] temp, int left, int pivot, int right) {
        //temp的指针
        int t = 0;
        //这样原数组就分为两个有序部分，[left,pivot],[pivot+1,right]
        //指向前部分的头部
        int leftP1 = left;
        //指向后部分的头部
        int leftP2 = pivot + 1;
        //直到有一部分的指针已经走到尾部
        while (leftP1 <= pivot && leftP2 <= right) {
            //若前部分的指针，所在位置的值比另外一个指针的小，把对应值存到temp数组中，
            // 然后前部分的指针和temp的指针后移
            if (array[leftP1] <= array[leftP2]) {
                temp[t] = array[leftP1];
                leftP1++;
                t++;
                //否则就是后部分的指针的值小
            } else {
                temp[t] = array[leftP2];
                leftP2++;
                t++;
            }
        }
        //把后部分指针的剩余数，全放入temp
        while (leftP1 <= pivot) {
            temp[t] = array[leftP1];
            t++;
            leftP1++;
        }
        //把前部分指针的剩余数，全放入temp
        while (leftP2 <= right) {
            temp[t] = array[leftP2];
            t++;
            leftP2++;
        }
        //把temp的值导入array
        t = 0;
        for (int leftP = left; leftP <= right; leftP++) {
            array[leftP] = temp[t];
            t++;
        }

    }

    /**
     * 拆分+合并，排序方法1
     * @param array 拆分后的原数组
     * @param temp 临时数组
     * @param left 原数组左端点
     * @param right 原数组右端点
     * */
    public void mergeSort1(int[] array, int[] temp, int left, int right) {
        //当left<right的时候就一直拆分
        if (left < right) {
            int pivot = (left + right) / 2;
            mergeSort1(array, temp, left, pivot);
            mergeSort1(array, temp, pivot + 1, right);
            merge1(array, temp, left, pivot, right);
        }

    }

    /**
     * 合并方法2，拷贝原数组得到两个临时数组，排序后数据直接写入原数组
     * @param array 拆分后的原数组
     * @param left 左端点
     * @param left 中间点
     * @param right 右端点
     * */
    public void merge2(int[] array, int left, int pivot, int right) {
        //这样就得到了pivot的左右两部分
        int[] arrayLeft = Arrays.copyOfRange(array, left, pivot + 1);
        int[] arrayRight = Arrays.copyOfRange(array, pivot + 1, right + 1);
        //这两个数组的指针
        int leftP = 0;
        int rightP = 0;
        //原数组的指针arrayP
        int arrayP = left;
        //当两数组有任何一个走到头时
        while (leftP < arrayLeft.length && rightP < arrayRight.length) {
            //把两数组比较后的较小数，放入原数组，较小数的数组指针后移，原数组指针后移
            if (arrayLeft[leftP] <= arrayRight[rightP]) {
                array[arrayP] = arrayLeft[leftP];
                leftP++;
                arrayP++;
            } else {
                array[arrayP] = arrayRight[rightP];
                rightP++;
                arrayP++;
            }
        }
        //如果左部分还有数据
        while (leftP < arrayLeft.length) {
            array[arrayP] = arrayLeft[leftP];
            leftP++;
            arrayP++;
        }
        //如果右部分还有数据
        while (rightP < arrayRight.length) {
            array[arrayP] = arrayRight[rightP];
            rightP++;
            arrayP++;
        }
    }

    /**
     * 拆分+合并，排序方法2
     * @param array 拆分后的原数组
     * @param left 原数组左端点
     * @param right 原数组右端点
     * */
    public void mergeSort2(int[] array,int left,int right){
        if (left<right){
            int pivot=(left+right)/2;
            mergeSort2(array,left,pivot);
            mergeSort2(array,pivot+1,right);
            merge2_1(array,left,pivot,right);
        }
    }


    /**
     * 合并方法2的变体
     * @param array 拆分后的原数组
     * @param left 左端点
     * @param left 中间点
     * @param right 右端点
     * */
    public void merge2_1(int[] array, int left, int pivot, int right) {
        //这样就得到了pivot的左右两部分
        int[] arrayLeft = Arrays.copyOfRange(array, left, pivot + 1);
        int[] arrayRight = Arrays.copyOfRange(array, pivot + 1, right + 1);
        //这两个数组的指针
        int leftP = 0;
        int rightP = 0;
        //原数组的指针arrayP
        int arrayP;
        //遍历原数组的同时
        for (arrayP=left;arrayP<=right;arrayP++){
            if (rightP==arrayRight.length){
                array[arrayP] = arrayLeft[leftP];
                leftP++;
                continue;
            }
            if (leftP==arrayLeft.length){
                array[arrayP] = arrayRight[rightP];
                rightP++;
                continue;
            }
            if (arrayLeft[leftP] <= arrayRight[rightP]) {
                array[arrayP] = arrayLeft[leftP];
                leftP++;
            } else {
                array[arrayP] = arrayRight[rightP];
                rightP++;
            }
        }

    }


}

class MergeSortingTest {
    public static void main(String[] args) {
        MergeSorting mergeSorting = new MergeSorting();
        int[] array = {5, 7, 4, 6, 3, 1, 2, 9, 8};
        mergeSorting.mergeSort2(array, 0,array.length - 1);
//        mergeSorting.merge2_1(array,0,(array.length-1)/2,array.length-1);
        System.out.println(Arrays.toString(array));
    }
}
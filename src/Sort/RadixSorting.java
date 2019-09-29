package Sort;

import java.util.Arrays;

public class RadixSorting {

    //10行数组
    int[][] buckets = new int[10][];
    //每个数组的指针，用一个一维数组来表示，10个指针初始均为-1，下标刚好对应桶的序号
    int[] bucketPs = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
    //原数组指针
    int arrayP;
    //变量n，用于控制位数切换，通过 (对象/n*10)%10 来拿到当前位的值，初始n为1，最大为
    int n;
    //标识是否已经排完了最高位，初始为false
    boolean flag;

    public void radixSort(int[] array) {
        if (array == null) {
            System.out.println("待排数组不能为空");
            return;
        }
        //初始化这10个数组
        for (int i = 0; i < 10; i++) {
            buckets[i] = new int[array.length];
        }

        int n = 1;
        //标识是否已经排完了最高位，初始为false
        boolean flag = false;
        while (true) {
            //n递增
            n=n*10;
            //遍历原数组
            for (int value : array) {
                //得到当前数，当前位的值，index在我的文档里是cur
                int index = (value / (n * 10)) % 10;
                //放到值对应的数组里,第index号桶，第index号桶的指针自增
                buckets[index][++bucketPs[index]] = value;
                //当发现把待排数全部都放入了一个桶中，也就是这个桶的指针为待排数组的长度-1时，这表示最高位的数已经被排完或者不用再排了
                if (bucketPs[index]==array.length-1) flag=true;
            }
            //如果发现已经排完，则退出循环
            if (flag) break;
            //分配完成后，把桶里的数覆盖回原数组，注意要让桶指针从头部到尾的顺序输出，否则会使上一次的排序逆序
            //重置原数组的指针
            arrayP = 0;
            int tempP;
            //从0号桶开始，一个个遍历这10个桶,buckets.length直接写成10也没问题
            for (int i = 0; i < buckets.length; i++) {
                //记录当前桶指针的位置
                tempP = bucketPs[i];
                //把桶指针置为头部
                bucketPs[i] = 0;
                //开始循环
                while (arrayP < array.length) {
                    //当当前桶的指针小于0时，或者大于循环前的位置时，退出当前桶的循环，换到下一个桶
                    if (bucketPs[i] < 0 || bucketPs[i] > tempP) {
                        //此时当前桶的指针为-1，刚好为初始状态
//                   bucketPs[i] = -1;
                        break;
                    }
                    //把当前桶的数一一拿出，赋给原数组
                    array[arrayP] = buckets[i][bucketPs[i]];
                    //把已经被取出的数据置为0方便debug
                    buckets[i][bucketPs[i]] = 0;
                    //桶指针后移
                    bucketPs[i]++;
                    //原数组指针后移
                    arrayP++;
                }

            }
            //一轮分配完成，重置桶的指针
            bucketPs = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
            System.out.println();

        }
    }

}

class RarixSortingTest {
    public static void main(String[] args) {
        int[] array = {1, 131, 2342, 312, 4987, 525, 6453, 732, 89};
        RadixSorting radixSorting = new RadixSorting();
        radixSorting.radixSort(array);
        System.out.println(Arrays.toString(array));
    }
}
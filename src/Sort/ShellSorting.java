package Sort;

import java.util.Arrays;

public class ShellSorting {
    //照例的中间变量
    int temp;
    //待插入数
    int disOrderNum;


    //排序2
    public void shellSort2(int[] array) {

        //用于改变增量的for循环
        for (int gap = array.length / 2; gap >= 1; gap = gap / 2) {
            //使用增量对数据分组，显然有gap个需要处理的组，这是遍历出每组的for循环
            //比如array[1]和array[1+gap]、array[1+2*gap]是一组的
            //比如array[2]和array[2+gap]、array[2+2*gap]是另外组的
            for (int i = 0; i < gap; i++) {
                //对当前所在的组进行插入排序，我们这里先采用交换的方式
                //显然，如果把j每轮递增的值设为gap，那么就可以用array[j]来表示当前分组中数
                for (int j = i + gap; j < array.length; j = j + gap) {
                    //临时保存当前值
                    int temp = array[j];
                    //指针，初始值为当前数的前一个数的位置，递减
                    int preIndex = j - gap;
                    //下面的代码会把array[preIndex]和array[j]交换
                    while (true) {
                        if (preIndex < 0) {
                            break;
                        }
                        if (array[preIndex] < temp) {
                            break;
                        }
                        //把当前指向的值赋给后一位
                        array[preIndex + gap] = array[preIndex];
                        //指针前移，只会移动1次
                        preIndex = preIndex - gap;
                    }
                    //当退出循环时，preIndex会在一开始的array[preIndex]之前
                    array[preIndex + gap] = temp;

                }
            }
        }
//        System.out.println(Arrays.toString(array));
    }

    //排序1
    public void shellSort1(int[] array) {

        //用于改变增量的for循环
        for (int gap = array.length / 2; gap >= 1; gap = gap / 2) {
            //n从0开始，array[n+gap]是第n个分组的第二个元素，即第一个待插入元素，插入到array[n]那一部分中
            for (int i = gap; i < array.length; i++) {
                //所以这里i初始=0+gap意为第1个分组的第2个元素，随着i的增加，i就=1+gap、2+gap....n+gap，会遍历所有分组的第一个元素以后的所有元素。
                // 所以这个for循环是为了拿到所有待插入元素
                int j = i;
                //存储待插入元素，array[j]表示各个分组待插入到有序部分的元素
                int temp = array[j];
                //下面的代码会移动分组中的元素位置
                //使用j来倒序遍历各个分组的有序部分
                while (true) {
                    //当待插入数大于前一个数，或到达头部时，找到插入的位置，插入后，退出循环
                    if (j - gap < 0 || temp > array[j - gap]) {
                        array[j] = temp;
                        break;
                    }
                    //没找到位置时，将分组中的数后移
                    array[j] = array[j - gap];
                    //j以gap为间隔，以此来遍历分组
                    j -= gap;
                }
            }
        }
//        System.out.println(Arrays.toString(array));
    }




}

class ShellSortingTest {
    public static void main(String[] args) {
        int[] array = {5, 7, 4, 6, 3, 1, 2, 9, 8, 0};
        ShellSorting shellSorting = new ShellSorting();
        shellSorting.shellSort1(array);
        System.out.println(Arrays.toString(array));
    }
}
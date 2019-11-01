package Sort;

import java.util.Arrays;

public class HeapSorting {


    /**
     * 交换两个结点的位置
     *
     * @param treeArray 一个数组
     * @param index1    数组位置1
     * @param index2    数组位置2
     */
    public void swap(int[] treeArray, int index1, int index2) {
        //临时变量
        int temp = treeArray[index1];
        treeArray[index1] = treeArray[index2];
        treeArray[index2] = temp;
    }


    /**
     * 堆化核心方法 堆化子树 大顶堆 对一个节点做heapify的时候，必须保证它的所有子树都已经是堆。
     *
     * @param treeArray 数组形式的树
     * @param index     从index号位开始堆化
     * @param n         堆中结点数
     */
    public void heapify(int[] treeArray, int index, int n) {
        //递归出口 以及简单校验
        if (index < 0 || treeArray == null || index >= n) {
            return;
        }
        //当前节点的左子结点
        int indexLeft = 2 * index + 1;
        //当前节点的右子节点
        int indexRight = 2 * index + 2;
        //假设传入的当前节点（父结点）是最大的
        int maxIndex = index;

        //两次比较都在数组不越界的情况下运行
        //比较左子结点和父结点的值
        if (indexLeft < n && treeArray[indexLeft] > treeArray[maxIndex]) {
            //如果左子结点的值比较大，替换最大结点
            maxIndex = indexLeft;
        }
        //比较右子结点和父结点的值
        if (indexRight < n && treeArray[indexRight] > treeArray[maxIndex]) {
            //如果右子结点的值比较大，替换最大结点
            maxIndex = indexRight;
        }
        //经过这两次比较后，maxIndex会成为真正的最大
        //交换最大的节点与父结点的位置 如果最大节点依然是初始的index 就不交换
        if (maxIndex != index) {
            swap(treeArray, index, maxIndex);
            //注意这里的递归 注意这里index是maxIndex 因为交换后，maxIndex位置的子结点会发生改变 这会破坏子结点所在子树的堆特性。
            heapify(treeArray, maxIndex, n);
        }
    }

    /**
     * 堆化 堆化一颗无序树 大顶堆
     *
     * @param treeArray 数组形式的树
     * @param n         堆中结点数
     */
    public void buildHeap(int[] treeArray, int n) {
        //拿到最后一个节点的下标
        int lastNodeIndex = n - 1;
        //找到最后一个节点的父结点 从此节点开始向上堆化即可
        int lastParentIndex = (lastNodeIndex - 1) / 2;
        //开始堆化 对每个非叶子节点所在的子树进行堆化
        for (int i = lastParentIndex; i >= 0; i--) {
            //调用堆化子树的方法
            heapify(treeArray, i, n);
        }
    }

    /**
     * 堆排序
     *
     * @param treeArray 数组形式的树
     */
    public void HeapSort(int[] treeArray) {
        //n的初始值为堆的节点总数
        int n = treeArray.length;
        //对无序数组堆化
        buildHeap(treeArray, n);
        //堆排序 lastNodeIndex为最后一个节点的下标 递减
        for (int lastNodeIndex = n - 1; lastNodeIndex > 0; lastNodeIndex--) {
            //交换堆中第一个结点和最后一个结点
            swap(treeArray, 0, lastNodeIndex);
            //每轮从根节点进行子树堆化即可 此时每轮模拟的结点数 刚好等于lastNodeIndex
            heapify(treeArray, 0, lastNodeIndex);
        }
    }
}

//堆排序测试
class HeapSortingTest {
    public static void main(String[] args) {
        int[] treeArray = {0, 7, 4, 6, 3, 1, 2, 9, 8, 5};
        HeapSorting heapSorting = new HeapSorting();
        heapSorting.HeapSort(treeArray);
        System.out.println(Arrays.toString(treeArray));
    }
}

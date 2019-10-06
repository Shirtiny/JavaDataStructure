package Search;

//二分查找
public class BinarySearch {


    //需要查找的数组必须有序
    public void search(int[] array, int leftP, int rightP, int goal) {
        //找不到则返回
        if (leftP>rightP){
            System.out.println("未找到");
            return;
        }
        //midP=(leftP+rightP)/2
        int midP = (leftP + rightP) / 2;
        //如果待查找的数大于midP
        if (goal > array[midP]) {
            //向右递归
            search(array, midP + 1, rightP, goal);
        } else if (goal < array[midP]) {
            //向左递归
            search(array, leftP, midP, goal);
            //最后是等于
        } else {
            System.out.println(goal+"的下标为"+midP+"；验证是否等于：");
            System.out.println(array[midP]==goal);
        }
    }

    //参数封装
    public void binarySearch(int[] array, int goal){
        search(array,0,array.length-1,goal);
    }

}
class BinarySearchTest{
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8};

        BinarySearch binarySearch=new BinarySearch();
        binarySearch.binarySearch(array,6);

    }
}
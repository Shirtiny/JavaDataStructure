package Search;

//插值查找
public class InterpolationSearch {

    //插值查找是对二分查找的优化，对midP进行自适应处理，
    // 因为数组是有序的嘛，因此可以大概确定待查元素所在的位置
    //midP=leftP+(rightP-leftP)*(goal-left)/(right-left)
    public void search(int[] array,int leftP,int rightP,int goal){
        //未找到则返回
        if (rightP<leftP){
            System.out.println("未找到");
            return;
        }
        if (goal>array[rightP]){
            System.out.println("目标数大于数组的最大数");
            return;
        }
        if (goal<array[leftP]){
            System.out.println("目标数小于数组的最小数");
        }
        //只是改变了midP的算法
        //注意这里的midP的值会收到goal的影响，可能会越界，所以先判断数在不在数组内
        int midP=leftP+(rightP-leftP)*(goal-array[leftP])/(array[rightP]-array[leftP]);
        if (goal>array[midP]){
            search(array,midP+1,rightP,goal);
        }else if(goal<array[midP]){
            search(array,leftP,midP,goal);
        }else {
            System.out.println(goal+"的下标为"+midP+"；验证是否等于：");
            System.out.println(array[midP]==goal);
        }
    }

    //参数封装
    public void interpolationSearch(int[] array,int goal){
        search(array,0,array.length-1,goal);
    }

}
class InterpolationSearchTest{
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,8};
        InterpolationSearch interpolationSearch=new InterpolationSearch();
        interpolationSearch.interpolationSearch(array,3);
    }
}
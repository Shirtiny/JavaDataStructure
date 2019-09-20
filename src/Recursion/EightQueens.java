package Recursion;

import java.util.Arrays;

public class EightQueens {

    //初始化一个数组
    private int[] array = new int[8];
    //对有效结果进行计数
    private int count = 0;

    //使用n表示棋盘的第n+1行，初始为0，表示棋盘第1行，即array[n]


    /**
     * 往棋盘中放入皇后（给数组赋值）
     * @param n 表示当前行号，范围从0到7
     */
    public void addQueen(int n) {

        //当n==8时，说明一个8*8的棋盘每行都放了，此时是要放第9个了
        if (n == 8) {
            count++;
            System.out.println("第" + count + "次的棋子已经放完："+showArrayString());
            return;
        }

        //在当前行放棋子，从0号位放到7号位
        for (int i = 0; i < array.length; i++) {
            //放入棋子
            array[n] = i;
            //每放一个，判断棋子的位置与放过的棋子是否冲突
            //若不冲突
            if (check(n)) {
                //用递归，换到下一行做重复的事情
                addQueen(n + 1);
            }
            //没进入递归，就说明该棋子位置冲突，不用做什么，继续for循环改变棋子位置即可
        }
    }

    //判断是否冲突的方法
    public boolean check(int n) {
        //遍历棋盘的每一行
        for (int pastN = 0; pastN < n; pastN++) {
            //若与当前行的棋子在同列或同一斜线
            if (isSameColumn(n, pastN) || isSameSlant(n, pastN)) {
                return false;
            }
        }
        //循环完毕后能执行到这里，说明不与以前的棋子在同列同斜线
        return true;
    }


    /**
     * 判断是否在同一列
     *
     * @param n     表示当前行
     * @param pastN 表示n之前的任意行
     */
    public boolean isSameColumn(int n, int pastN) {

        return array[n] == array[pastN];
    }

    //判断是否在同一行
    /*这个无需判断，因为肯定不在同一行*/


    //判断是否在同一斜线，使用绝对值
    public boolean isSameSlant(int n, int pastN) {
        return Math.abs(array[n] - array[pastN]) == Math.abs(n - pastN);
    }

    //返回数组的字符串
    public String showArrayString() {
        return Arrays.toString(array);
    }

    //展示数组
    public void show(){
        System.out.println(showArrayString());
    }


}

class eightQueenTest {
    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.addQueen(0);
    }
}
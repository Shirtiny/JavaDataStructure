package Recursion;

import java.io.*;

public class Maze {

    //用二维数组表示迷宫地图，约定1为墙壁，0为通路，2为足迹，3为重复走的路
    int[][] mazeMap;
    //出口坐标
    int goalI;
    int goalJ;

    //以文件方式构造地图数组,这样好设计地图
    public Maze(File mapFile) throws IOException {
        loadMapFile(mapFile);
    }

    //读取地图文件中的二维数组
    public void loadMapFile(File mapFile) throws IOException {
        /*
         * 拿到文件中二维数组的行数列数
         * */
        Reader reader = new FileReader(mapFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        //查看数组行数
        int rowI = 0;
        //数组列数
        int rowJ = 0;
        //文件每行数据
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            //得到数组行数
            rowI++;
            //每行以两个空格分割
            String[] splits = line.split(" {2}");
            //得到列数
            rowJ = splits.length;

        }
        System.out.println("该地图的二维数组行数：" + rowI);
        System.out.println("该地图的二维数组列数：" + rowJ);
        //初始化对应行列的数组
        mazeMap = new int[rowI][rowJ];
        System.out.println("地图数组初始化完成");

        /*
         * 载入文件中的数组数据
         * */
        //关闭流
        bufferedReader.close();
        //新建流
        reader = new FileReader(mapFile);
        bufferedReader = new BufferedReader(reader);

        //开始读取每行数据
        //数组第i行
        int i = 0;
        while (i < rowI) {
            //读取每行数据
            line = bufferedReader.readLine();
            //以两个空格分割
            String[] splits = line.split(" {2}");
            //处理当前行的数据
            for (int j = 0; j < splits.length; j++) {
                //[打印]每行数据，不换行
                System.out.print(splits[j] + "\t");
                //把当前值转为int，然后赋给二维数组
                mazeMap[i][j] = Integer.parseInt(splits[j]);
            }
            //行数自增
            i++;
            //[打印]每行打印后换行
            System.out.println();
        }
        System.out.println("地图数组数据载入完成！");
    }

    //读取稀疏数组文件
    /*
     * 暂时不需要，地图文件大的话可以转成稀疏数组存放
     * */

    //打印数组
    public void show(){
        //打印数组
        for (int[] valuex : mazeMap) {
            for (int valuey : valuex) {
                System.out.print(valuey + "\t");
            }
            System.out.println();
        }
    }


    //自动寻找迷宫路线，指定一个入口的坐标(x,y)，再指定一个出口的坐标(goalI,goalJ)
    public void autoFind(int x, int y, int goalI, int goalJ) {
        if (mazeMap == null) {
            System.out.println("地图无数据，请先初始化");
        } else {
            //判断输入值是否越界，是否有效
            if (x >= mazeMap[0].length || y >= mazeMap.length || mazeMap[y][x] == 1) {
                System.out.println("该位置不在地图内，或者该位置不能做为入口");
            } else {
                //指定出口坐标
                this.goalI = goalI;
                this.goalJ = goalJ;
                //开始寻路
                System.out.println("开始寻路");
                startFind(x, y);
                //打印数组
                show();
            }
        }
    }

    //递归寻路
    public boolean startFind(int x, int y) {
        //若到达出口
        if (x == goalI && y == goalJ) {
            System.out.println("找到出口");
            return true;
        }

        //若能走通
        if (mazeMap[y][x] == 0) {
            mazeMap[y][x] = 2;
            System.out.println("找到一个点");
            show();
            //接着向上走
            if (startFind(x, y - 1)) {
                return true;
                //向右走
            } else if (startFind(x + 1, y)) {
                return true;
                //向下走
            } else if (startFind(x, y + 1)) {
                return true;
                //向左走
            } else if (startFind(x - 1, y)) {
                return true;
            } else {
                //都走不通
                System.out.println(" ");
                return false;
            }
        }else if (mazeMap[y][x] == 2){
            System.out.println("回溯");
            mazeMap[y][x] = 3;
            show();
            return false;
        }else {
            System.out.println("碰到墙");
            return false;
        }
    }
}


class mapTest {
    public static void main(String[] args) throws IOException {
        //源文件
        File mapFile = new File("D:\\aria2\\mazeMap.data");
        Maze maze = new Maze(mapFile);
        //起始坐标(0,2)，终点坐标(1,5)
        maze.autoFind(0, 2, 1, 5);
    }
}
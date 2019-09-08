package SparseArray;

import java.io.*;
import java.util.Arrays;

public class SparseArray {

    public static void main(String[] args) throws IOException {

        //1. 新建一个二维数组
        int[][] array1 = new int[4][5];

        //2. 给数组赋一些值
        array1[1][0]=1;
        array1[1][2]=2;
        array1[3][3]=1;

        //3. 格式化输出数组
        for (int i=0;i<array1.length;i++){//array1.length是行数
            for (int j=0;j<array1[0].length;j++){//array1[0].length是第0行的列数
//            System.out.print(array1[i][j]+"\t");
            }
//            System.out.println();
        }

        //4. 更好的输出方式foreach
        System.out.println("##############这是原数组1");
        for (int[] row:array1){
            for (int value:row){
                System.out.print(value+"\t");
            }
            System.out.println();
        }

        //5. 转为稀疏数组，首先需要得到原数组中的有效值个数
        System.out.println("##############");
        int num=0;
        for (int[] row:array1){
            for(int value:row){
                if (value!=0){
                    num++;
                }
            }
        }
        System.out.println("原数组1有："+num+"个有效值");

        //6. 创建稀疏数组，因为稀疏数组第一行是对原数组大小的描述，其他行都是对有效值的描述，
        //所以，稀疏数组行数是 原数组有效值个数+1。
        int [][] sparseArray=new int[num+1][3];

        //7. 对稀疏数组的第一行赋值，对应原数组的行数、列数、有效值个数
        sparseArray[0][0]=4;
        sparseArray[0][1]=5;
        sparseArray[0][2]=3;

        //8. 为稀疏数组赋值，对原数组进行遍历，需要明确第几行第几列
        int n=1;
        for (int i=0;i<array1.length;i++){
            for (int j=0;j<array1[0].length;j++){
                if (array1[i][j]!=0){
                    sparseArray[n][0]=i;
                    sparseArray[n][1]=j;
                    sparseArray[n][2]=array1[i][j];
                    n++;
                }
            }
        }

        //9. 输出稀疏数组
        System.out.println("##############这是转换后的稀疏数组");
        for (int[] row:sparseArray){
            for (int value:row){
                System.out.print(value+"\t");
            }
            System.out.println();
        }

        //10. 再恢复成原数组
        // 创建一个原数组2，原数组2的行数，是稀疏数组的第一行第一列的值
        // 原数组2的列数，是稀疏数组的第一行第二列的值
        int[][] array2=new int[sparseArray[0][0]][sparseArray[0][1]];

        //11. 把稀疏数组中的有效值读出，赋给原数组2，
        // 只需付给原数组2有效值，i从1开始，也就是第二行开始，
        // 稀疏数组第i行，第1列是有效值的行数，第2列是有效值的列数，第3列是有效值的值
        // 因为稀疏数组固定只有3列，一个循环即可
        for (int i=1;i<sparseArray.length;i++){
            array2[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }

        //12. 输出回复后的原数组2
        System.out.println("##############这是恢复后的原数组2");
        for (int[] row:array2){
            for (int value:row){
                System.out.print(value+"\t");
            }
            System.out.println();
        }

        //13. 将稀疏数组存入本地文件
        File file =new File("D:\\aria2\\SparseArray.data");
        Writer writer=new FileWriter(file);
        for (int[] row:sparseArray){
            for (int value:row){
                writer.write(value+"\t");
            }
            writer.write("\r\n");
        }
        writer.close();


//14. 从本地文件读取稀疏数组
        System.out.println("##############这是从文件恢复的稀疏数组2");
        //1.创建源
        File src = new File("D:\\aria2\\SparseArray.data");
        //2.选择流
        BufferedReader in = new BufferedReader(new FileReader(src));
        //3.1进行数据的搬移，但是数组首要考虑的事情是数组要多大？
        int row =0;//用于创建要创建的二维稀疏数组的大小确定
        String line; //一行数据
        //逐行读取，并将每个数组放入到数组中
        while ((line = in.readLine()) != null) {
            row++;
        }
        int sparseArr2[][] = new int [row][3];
        //3.2文本数据转移到稀疏数组中
        int rowtmp = 0;
        //由于读取完毕整个文本文档，重启流
        in.close();
        in = new BufferedReader(new FileReader(src));
        while ((line = in.readLine()) != null) {
            String[] temp = line.split("\t");
            for (int j = 0; j < temp.length; j++) {
                sparseArr2[rowtmp][j]=Integer.parseInt(temp[j]);
            }
            rowtmp++;
        }
        //4.关闭流
        in.close();
        //验证文件读取是否正确
        for(int[]temp1:sparseArr2) {
            for (int temp2 : temp1) {
                System.out.printf("%d\t", temp2);
            }
            System.out.println();
        }

    }

}
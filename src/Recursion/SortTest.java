package Recursion;

//排列组合
public class SortTest {
    public static void main(String[] args) {

        char[] array = {'A', 'B', 'C'};
        arrange(array, 0, array.length);
    }

    public static void arrange(char[] array, int start, int len) {
        if (start == len - 1) {
            for (int i = 0; i < array.length; i++)
                System.out.print(array[i]);
            System.out.println();
            return;
        }
        //213 231
        for (int i = start; i < len; i++) {
            char temp = array[start];
            array[start] = array[i];
            array[i] = temp;
            arrange(array, start + 1, len);
            temp = array[start];
            array[start] = array[i];
            array[i] = temp;
        }
    }
}
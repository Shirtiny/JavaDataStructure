package Recursion;

public class Test {


   public static int num1(int a){

       if (a<=1){
           return 1;
       }else {
           return  num1(a-1)*a;
       }

   }

    public static void main(String[] args) {
       int a=3;
        a=num1(a);
        System.out.println(a);
    }


}

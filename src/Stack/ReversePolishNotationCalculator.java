package Stack;

import com.sun.org.apache.xpath.internal.operations.Operation;

import java.util.*;

public class ReversePolishNotationCalculator {//关于逆波兰表达式的计算器


    //运算符栈
    Stack stack = new Stack();
    //输出集合
    List<String> list = new ArrayList<>();

    //自定义运算符优先级
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>() {
        {
            //用map把运算符对应一个int值，模拟优先级
            put("+", 0);
            put("-", 0);
            put("*", 1);
            put("/", 1);
            //战术性把左括号当成优先级最低的运算符
            put("(", -1);

        }
    };

    //获取运算符优先级，（自定义的）
    public int getOperatorPrecedence(String operator) {
        return hashMap.get(operator);
    }

    //匹配字母,判断是否是字母的方法,这样可以转换a*(b+c)-d这样的表达式
    public boolean isABC(String value) {
        return value.matches("[a-zA-Z]+");
    }

    //匹配字母，比较ascii码的方式
    public boolean isABC(int value) {
        return  97<=value && value<=122 || 65 <= value && value <= 90;
    }

    //匹配数字,查看value是不是匹配数字
    public boolean isNum(String value) {
        return value.matches("\\d+");
    }

    //匹配数字，比较ascii码的方式
    public boolean isNum(int value) {
        return 48 <= value && value <= 57;
    }

    //确认是不是+-*/这4个运算符
    public boolean isOperator(String value){
        return "+".equals(value) || "-".equals(value) || "*".equals(value) || "/".equals(value);
    }


    //循环比较当前运算符与栈顶运算符的优先级
    public void loopCompare(String curOper) {
        //拿到当前运算符的优先级数
        int curP = getOperatorPrecedence(curOper);
        int stackP;
        String pop;
        while (true) {
            if (stack.empty()) {
                stack.push(curOper);
                break;
            }
            //获取栈顶元素的优先级数
            stackP = getOperatorPrecedence((String) stack.peek());
            //如果该运算符的优先级高，将该运算符入栈，结束循环
            if (curP > stackP) {
                stack.push(curOper);
                break;
                //若该运算符优先级低
            } else {
                //将栈中的一个元素pop出，然后加入集合
                pop = (String) stack.pop();
                list.add(pop);
            }

        }
    }

    //将字符串每个字符间添加空格，注意有多位数
    public String addBlank(String expression) {
        //把字符串转成int数组
        int[] array = expression.codePoints().toArray();
        //全局用的字符串构建器
        StringBuilder sb = new StringBuilder();
        //临时用的
        StringBuilder sbNum = null;
        //临时字符串
        String sbNumString="";
        //从int型的ASCII值转成字符型
        for (int i = 0; i < array.length; i++) {
            //如果当前值是数字或字母
            if (isNum(array[i])||isABC(array[i])) {
                //新建一个临时数字字符串构建器
                sbNum=new StringBuilder();
                //临时变量
                int j = i;
                //查看下一个是不是也是数字或字母
                while (true) {
                    //已经到数组最大长度时（说明一直到最后都是数字和字母），把最后一个数字\字母拼进来，让i遍历完成
                    if (j == array.length - 1){
                        sbNum.append((char)array[j]);
                        i=j+1;
                        break;
                    }
                    //当前值不是数字也不是字母时，把i改为j的值，跳出循环
                    if (!isNum(array[j])&&!isABC(array[j])){
                        i=j-1;
                        break;
                    }
                    //临时拼接
                    sbNum.append((char)array[j]);
                    j++;
                }
                //结束循环后，说明一个完整的数字\字母已经拼接完成
                sbNumString = sbNum.toString();
                //拼入全局字符串
                sb.append(sbNumString + " ");
            }
            else {
                //如果不是数字或字母，直接拼接
                sb.append((char) array[i] + " ");
            }

        }
        return sb.toString();
    }


    //把输入的中缀表达式字符串，转为逆波兰表达式，这是表达式中字符有空格隔开的情况
    public String convertorWithBlank(String expression) {
        //先判断字符串是否为空
        if (expression == null || expression.length() == 0) {
            return null;
        }
        //去除字符串头尾的空格
        String expressionTrim = expression.trim();
        //按空格拆分出一个字符串数组
        String[] strings = expressionTrim.split(" ");

        //不进行运算，不用转int
        for (String value : strings) {

            if (isOperator(value)) {
                //是运算符的话，去循环比较优先级
                loopCompare(value);
                //如果是左括号
            } else if ("(".equals(value)) {
                //压入栈
                stack.push(value);
                //如果是右括号
            } else if (")".equals(value)) {
                while (true) {
                    String pop = (String) stack.pop();
                    //当pop出左括号时结束循环
                    if ("(".equals(pop)) {
                        break;
                    }
                    //把不是(的运算符加入集合
                    list.add(pop);
                }
                //既不是运算符，也不是括号（设表达式中不包括大括号、中括号、以及除了+—*/以外的运算符等）
            } else {
                //是数字或字母或数字+字母，加入集合
                list.add(value);
            }
        }

        //最后，将栈中剩余的元素加入集合
        while (!stack.empty()) {
            list.add((String) stack.pop());
        }
        //结束后，得到一个包含全部字符的list集合
        //使用StringBuilder构建出字符串
        StringBuilder sb = new StringBuilder();
        for (String m : list) {
            sb.append(m+" ");
        }
        //返回一个和字符串构建器内容相同的字符串
        return sb.toString();
    }


    //把输入的中缀表达式字符串，转为逆波兰表达式，这是不带空格的情况
    public String convertor(String expression){
        //转成有空格的字符串
        String expressionWithBlank = addBlank(expression);
        //转成后缀表达式（逆波兰表达式）
        return convertorWithBlank(expressionWithBlank);
    }





    public static void main(String[] args) {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        //转换支持字母和数字的组合，运算符只支持+-*/，数字暂时只考虑正整数
        String expression1 = "3A*(2sd+5acsa+c)-3d";

        String expression2 = "aa*(s+aad+c)-d";

        String expression3 = "22*(9324+6/3321-5213213)+4";

        String converteds = calculator.convertor(expression1);
        System.out.println(converteds);

    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {
        // (30+4)*5-6 => 30 4 + 5 * 6 - => 164
        // 4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / + => 76
//        String s = "30 4 + 5 * 6 -";
//        String s = "4 5 * 8 - 60 + 8 2 / +";
        String s = "1+((2+3)*40)-5/1";
        System.out.println("计算结果为：" + result(transform(s)));

    }

    /**
     * 将中缀表达式字符串转换为后缀表达式集合
     *
     * @param s 中缀表达式字符串
     * @return 后缀表达式集合
     */
    public static List<String> transform(String s) {
        // 定义一个后缀表达式集合
        List<String> suffixList = new ArrayList<>();
        // 定义一个数栈
        Stack<String> numberStack = new Stack<>();
        // 定义一个运算符栈
        Stack<String> operatorStack = new Stack<>();
        // 扫描字符串
        String s1 = "";
        // 扫描数字(用来拼接数字，可能多位数，入70)
        String numStr = "";
        // 从左到右扫描字符串
        for (int i = 0; i < s.length(); i++) {
            s1 = s.substring(i, i + 1);
            // 判断是否为运算符
            if (isOperator(s1)) {
                while (true) {
                    // 如果为运算符，判断运算符栈顶是否为空
                    if (operatorStack.isEmpty()) {
                        // 如果运算符栈顶为空，扫描运算符直接入运算符栈
                        operatorStack.push(s1);
                        break;
                    }
                    // 如果运算符栈顶不为空，判断运算符栈顶是否为左括号
                    if ("(".equals(operatorStack.peek())) {
                        // 如果运算符栈顶为左括号，扫描运算符直接入运算符栈
                        operatorStack.push(s1);
                        break;
                    }
                    // 如果运算符栈顶不为左括号，判断运算符栈顶与扫描运算符优先级高低
                    if (operatorPriority(s1) > operatorPriority(operatorStack.peek())) {
                        // 如果扫描运算符优先级高于运算符栈顶优先级，扫描运算符直接入运算符栈
                        operatorStack.push(s1);
                        break;
                    }
                    // 如果如果扫描运算符优先级不高于运算符栈顶优先级，将运算符栈顶的运算符弹出压入数栈，再循环判断新的运算符栈顶与扫描运算符
                    numberStack.push(operatorStack.pop());
                }
            } else {
                // 如果不为运算符，判断是否为括号
                if ("(".equals(s1)) {
                    // 如果为左括号，直接入运算符栈
                    operatorStack.push(s1);
                } else if (")".equals(s1)) {
                    // 如果为右括号，判断运算符栈顶是否为左括号
                    while (!"(".equals(operatorStack.peek())) {
                        // 如果不为左括号，将运算符栈顶的运算符弹出压入数栈，再循环判断新的运算符栈顶是否为左括号(直到遇到左括号为止)
                        numberStack.push(operatorStack.pop());
                    }
                    // 遇到左括号，将左括号从运算符栈弹出(相当于左右括号丢弃)
                    operatorStack.pop();
                } else if (isNumber(s1.charAt(0))) {
                    // 如果为数字，需要拼接数字(可能数字不是一位，如70)
                    numStr += s1;
                    // 防止越界
                    if (i == s.length() - 1) {
                        numberStack.push(numStr);
                    } else {
                        // 判断下一位是否为数字(可能是运算符，也可能是括号所以条件为判断数字)
                        if (!isNumber(s.substring(i + 1, i + 2).charAt(0))) {
                            // 如果不为数字，就代表numStr为一个完整的数字，将完整数字压入数栈
                            numberStack.push(numStr);
                            // 将numStr中间量重置为空
                            numStr = "";
                        }
                    }
                }
            }
        }
        // 将运算符栈剩余的运算符压入数栈
        while (!operatorStack.isEmpty()) {
            numberStack.push(operatorStack.pop());
        }
        // 将数栈元素加入到list集合，注意！！！此时list逆序的输出才是正常的后缀表达式
        while (!numberStack.isEmpty()) {
            suffixList.add(numberStack.pop());
        }
        return suffixList;
    }

    /**
     * 根据后缀表达式集合获取结果值
     *
     * @param suffixList 后缀表达式集合
     * @return 结果值
     */
    public static int result(List<String> suffixList) {
        if (suffixList == null || suffixList.size() <= 0) {
            return 0;
        }
        // 存放数栈
        Stack<String> numberStack = new Stack<>();
        // 集合遍历的元素
        String s = "";
        // list逆序才是正确的后缀表达式，所以要逆序打印！！！
        for (int i = suffixList.size(); i > 0; i--) {
            s = suffixList.get(i - 1);
            // 如果为运算符直接从数栈中取两个数进行运算再存入数栈
            if (isOperator(s)) {
                numberStack.push(String.valueOf(calculator(Integer.parseInt(numberStack.pop()), Integer.parseInt(numberStack.pop()), s)));
            } else {
                // 如果为数直接存入数栈
                numberStack.push(s);
            }
        }
        // 最后数栈只会有一个元素，也就是栈顶的值就是计算的结果
        return Integer.parseInt(numberStack.pop());
    }

    // 判断是否为运算符
    public static boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    // 判断是否为数字
    public static boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }

    // 运算 注意因为栈是先进后出所以计算的时候要：后出 计算(+-*/) 先出
    public static int calculator(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num2 + num1;
            case "-":
                return num2 - num1;
            case "*":
                return num2 * num1;
            case "/":
                return num2 / num1;
            default:
                throw new RuntimeException("操作符有误");
        }
    }

    // 判断运算符优先级
    public static int operatorPriority(String c) {
        switch (c) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            default:
                return -1;
        }
    }
}

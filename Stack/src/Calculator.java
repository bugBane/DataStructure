public class Calculator {
    public static void main(String[] args) {
        // 计算字符串
        String s = "70+6*5*4-3-2/1";
        // 数栈
        ArrayStack numberStack = new ArrayStack(10);
        // 运算符栈
        ArrayStack operatorStack = new ArrayStack(10);
        // 当前字符
        char c;
        // 如果数字不是个位数就得继续往后截取
        String numStr = "";
        for (int i = 0; i < s.length(); i++) {
            // 截取字符
            c = s.substring(i, i + 1).charAt(0);
            // 判断是否为运算符
            if (isOperator(c)) {
                // 如果截取字符为运算符，判断运算符栈是否为空
                if (!operatorStack.isEmpty()) {
                    // 运算符栈不为空，判断当前运算符的优先级和运算符栈中的栈顶优先级大小
                    if (operatorPriority(c) <= operatorPriority((char) operatorStack.getTop())) {
                        // 如果优先级小于或者等于，那么就从数栈中取出两个数、从运算符栈中取出栈顶运算符，将这两个数用取出的运算符计算后再将计算结果存入数栈中
                        numberStack.push(calculator(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
                        // 然后才把这个截取的运算符存入运算符栈中
                        operatorStack.push(c);
                    } else {
                        // 如果优先级大于运算符栈的栈顶运算符，就直接将截取运算符入运算符栈中
                        operatorStack.push(c);
                    }
                } else {
                    // 运算符栈为空 直接将截取运算符入运算符栈中
                    operatorStack.push(c);
                }
                // 如果为数，那么就直接将数入数栈中
            } else {
                // 添加多位数
                numStr += c;
                // 最后一位就不再截取，否则会有数组越界异常
                if (i == s.length() - 1) {
                    numberStack.push(Integer.parseInt(numStr));
                } else {
                    if (isOperator(s.substring(i + 1, i + 2).charAt(0))) {
                        // 一个值入70加入数栈中
                        numberStack.push(Integer.parseInt(numStr));
                        // 中间量再设置为“”
                        numStr = "";
                    }
                }
            }
        }
        // 此时运算符栈中优先级一定是从大到小(先入后出:进来的优先级>以前的优先级)，所以只需要从数栈拿两个数再从运算符中取栈顶运算符计算再存入数栈，依此类推运算符栈为空那么数栈就只剩一个数据
        while (!operatorStack.isEmpty()) {
            numberStack.push(calculator(numberStack.pop(), numberStack.pop(), operatorStack.pop()));
        }
        System.out.println("计算结果为：" + numberStack.pop());
    }

    // 判断是否为运算符
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // 判断运算符优先级
    public static int operatorPriority(char c) {
        switch (c) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    // 运算 注意因为栈是先进后出所以计算的时候要：后出 计算(+-*/) 先出
    public static int calculator(int num1, int num2, int operator) {
        switch (operator) {
            case '+':
                return num2 + num1;
            case '-':
                return num2 - num1;
            case '*':
                return num2 * num1;
            case '/':
                return num2 / num1;
            default:
                return 0;
        }
    }
}
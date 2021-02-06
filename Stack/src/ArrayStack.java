import java.util.Scanner;

public class ArrayStack {
    // 栈最大容量
    private int maxSize;
    // 栈顶
    private int top;
    // 存储数据的数组
    private int[] array;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        // 栈顶初始化为-1
        top = -1;
        array = new int[maxSize];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 判断栈是否已满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 获取栈有效个数
    public int size() {
        return top + 1;
    }

    // 压栈
    public void push(int i) {
        if (isFull()) {
            throw new RuntimeException("栈已满，无法压入");
        }
        array[++top] = i;
        System.out.println(i + "入栈");
    }

    // 弹栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法弹出");
        }
        System.out.println(array[top] + "出栈");
        return array[top--];
    }

    // 查栈顶
    public int getTop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，无法查栈顶");
        }
        System.out.println(array[top]);
        return array[top];
    }

    // 展示栈数据
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空，请入栈数据后再试");
        }
        // 栈的特征：先进后出
        for (int i = size() - 1; i > -1; i--) {
            System.out.println(array[i]);
        }
    }

    /**
     * 测试栈
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        System.out.println("欢迎使用栈：请操作");
        System.out.println("s(show)展示栈");
        System.out.println("l(lookHead)查栈顶");
        System.out.println("a(push)压栈");
        System.out.println("g(pop)弹栈");
        System.out.println("e(exit)退出操作");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            char c = scanner.next().charAt(0);
            switch (c) {
                case 's':
                    arrayStack.show();
                    break;
                case 'l':
                    try {
                        arrayStack.getTop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.println("请输入要入栈的数据");
                        arrayStack.push(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        arrayStack.pop();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }

    }
}

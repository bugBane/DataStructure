package arrayqueue;

import java.util.Scanner;

public class ArrayQueue {
    // 队列最大容量
    private int maxSize;
    // 队列头数据指针
    private int front;
    // 队列尾数据指针
    private int rear;
    // 存放队列数据的数组
    private int[] array;

    /**
     * 构造方法
     *
     * @param maxSize
     */
    public ArrayQueue(int maxSize) {
        // 设置队列最大容量值
        this.maxSize = maxSize;
        // 初始化数组
        array = new int[maxSize];
        // front初始化指向队列头数据的前一个位置
        front = -1;
        // rear初始化指向队列尾数据位置
        rear = -1;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 判断队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /**
     * 入列
     *
     * @param m
     */
    public void addQueue(int m) {
        if (isFull()) {
            throw new RuntimeException("队列已满，无法继续入队");
        }
        array[++rear] = m;
    }

    /**
     * 出队
     */
    public void getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法继续出队");
        }
        System.out.println(array[++front] + "出队");
    }

    /**
     * 查头数据
     */
    public void lookHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看头数据");
        }
        System.out.println("队列头数据为：" + array[front + 1]);
    }

    /**
     * 展示队列
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < rear; i++) {
            System.out.println("队列内容" + "[" + (i + 1) + "]：" + array[i + 1]);
        }
    }

    /**
     * 测试队列
     *
     * @param args
     */
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(5);
        System.out.println("欢迎使用队列：请操作");
        System.out.println("s(show)展示队列");
        System.out.println("l(lookHead)查队列头数据");
        System.out.println("a(addQueue)入队");
        System.out.println("g(getQueue)出队");
        System.out.println("e(exit)退出操作");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            char c = scanner.next().charAt(0);
            switch (c) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'l':
                    try {
                        arrayQueue.lookHead();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    try {
                        System.out.println("请输入要入队的数据");
                        arrayQueue.addQueue(scanner.nextInt());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        arrayQueue.getQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    scanner.close();
                    flag = false;
                    break;
            }
        }

    }
}

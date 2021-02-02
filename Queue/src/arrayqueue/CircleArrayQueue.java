package arrayqueue;

import java.util.Scanner;

public class CircleArrayQueue {
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
    public CircleArrayQueue(int maxSize) {
        // 设置队列最大容量值
        this.maxSize = maxSize;
        // 初始化数组
        array = new int[maxSize];
        // front初始化指向队列头数据,对象默认初始化0
//        front = 0;
        // rear初始化指向队列尾数据位置的后一个位置(此时数组没有数据,但是rear为0)
//        rear = 0;
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
        // 用rear的后一个位置是否为front来判断队列还能否加入数据
        return front == (rear + 1) % maxSize;
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
        // rear是当前尾数据的后一个位置，所以需要先填充当前rear位置，再rear=(rear+1)%MaxSize
        array[rear] = m;
        rear = (rear + 1) % maxSize;
        System.out.println(m + "入队");
    }

    /**
     * 出队
     */
    public void getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法继续出队");
        }
        // front是当前头数据位置，所以先取出当前front位置，front=(front+1)%MaxSize
        System.out.println(array[front] + "出队");
        front = (front + 1) % maxSize;
    }

    /**
     * 查头数据
     */
    public void lookHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法查看头数据");
        }
        System.out.println("队列头数据为：" + array[front]);
    }

    /**
     * 展示队列
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.println("队列内容" + "[" + i % maxSize + "]：" + array[i % maxSize]);
        }
    }

    /**
     * 有效个数
     *
     * @return
     */
    public int size() {
        // 队列满的最大有效个数为MaxSize-1(因为占了rear后一个位置用来判断，所以无法存值)
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 测试队列
     *
     * @param args
     */
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
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

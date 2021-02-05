public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.add(5);
        circleSingleLinkedList.show();
        System.out.println("-----------------------");
        circleSingleLinkedList.josephu(1, 2, 5);
    }
}

class CircleSingleLinkedList {
    private BoyNode first;

    public CircleSingleLinkedList() {
    }

    public void add(int nums) {
        if (nums <= 0) {
            return;
        }
        // 临时量用来帮助批量加入链表，指向链表末尾(非null)
        BoyNode tail = null;
        for (int i = 1; i <= nums; i++) {
            BoyNode boyNode = new BoyNode(i);
            // 第一个节点为first
            if (i == 1) {
                first = boyNode;
                first.setNext(first);
                tail = first;
            } else {
                // 链表添加一个节点
                tail.setNext(boyNode);
                // 新添加的节点再指向头形成闭环
                boyNode.setNext(first);
                // 临时量向后移动，指向指针末尾
                tail = boyNode;
            }
        }
    }

    public void show() {
        if (first == null) {
            System.out.println("单循环链表为空");
        }
        BoyNode temp = first;
        // 因为闭环所以得判断是否完成一圈
        while (temp.getNext() != first) {
            System.out.println(temp);
            temp = temp.getNext();
        }
        System.out.println(temp);
    }

    // 约瑟夫
    public void josephu(int startNo, int countNum, int nums) {
        if (first == null || startNo > nums || countNum < 1 || startNo < 1 || nums <= 0) {
            return;
        }
        // 指向链表最后一个节点，用来删除节点时
        BoyNode tail = first;
        while (tail.getNext() != first) {
            tail = tail.getNext();
        }
        // 按照初始位置对first进行移动(重定位)，方便后面移动，tail同理
        for (int i = 1; i < startNo; i++) {
            first = first.getNext();
            tail = tail.getNext();
        }
        // 判断链表是否只有一个节点
        while (tail != first) {
            // 报数移动countNum-1次
            for (int i = 1; i < countNum; i++) {
                first = first.getNext();
                tail = tail.getNext();
            }
            // 此时的first就是要删除的节点
            System.out.println(first + "出圈");
            // 删除节点
            first = first.getNext();
            // 完成闭环
            tail.setNext(first);

        }
        System.out.println(tail + "出圈");
    }
}

class BoyNode {
    private int no;

    private BoyNode next;

    public BoyNode(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public BoyNode getNext() {
        return next;
    }

    public void setNext(BoyNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
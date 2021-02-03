import java.util.Stack;

public class SingleLinkedList {
    // 头节点
    private HeroNode head;

    public SingleLinkedList() {
        this.head = new HeroNode();
    }

    public HeroNode getHead() {
        return head;
    }

    // 1.不考虑当前节点编号
    // 找到当前链表的最后一个节点
    // 将这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        // 因为头节点不能动，我们可以通过一个辅助指针（变量）来帮助找到添加的位置
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    // 2.根据排名将英雄插入到指定位置（如果有这个排名将添加失败并且给出错误提示）
    // 从head向next寻找链表的节点判断rank大小，相同提示报错
    // 找到要插入的位置使用temp变量来插入
    public void add2(HeroNode heroNode) {
        HeroNode temp = head;
        // 添加的编号是否存在，默认为false
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.rank > heroNode.rank) {
                break;
            } else if (temp.next.rank == heroNode.rank) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            throw new RuntimeException("已经有相同的排名好汉，请检查后再插入");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点信息，根据rank编号来修改，即rank不会改变。
    public void update(HeroNode heroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        HeroNode temp = head.next;
        // 修改的编号是否存在，默认为false
        boolean flag = false;
        while (temp != null) {
            if (temp.rank == heroNode.rank) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            throw new RuntimeException("没有相同的排名好汉，请检查后再修改");
        }
    }

    //删除节点信息，根据rank编号来删除。
    public void remove(int rank) {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        HeroNode temp = head;
        // 删除的编号是否存在，默认为false
        boolean flag = false;
        while (temp.next != null) {
            if (temp.next.rank == rank) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            throw new RuntimeException("没有相同的排名好汉，请检查后再删除");
        }
    }

    /**
     * 展示链表
     */
    public void show() {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 获取head有效节点
     *
     * @param head
     * @return
     */
    public int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        HeroNode temp = head.next;
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 获取head链表倒数第k个节点
     *
     * @param head
     * @param k
     * @return
     */
    public HeroNode getReciprocalNode(HeroNode head, int k) {
        int length = getLength(head);
        if (length == 0 || k <= 0 || length < k) {
            return null;
        }
        HeroNode temp = head.next;
        for (int i = 0; i < length - k; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public void reversal(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 辅助指针
        HeroNode headTemp = head.next;
        // 指向当前节点headTemp的下一个节点
        HeroNode next = null;
        HeroNode reversal = new HeroNode();
        while (headTemp != null) {
            // 暂时存放当前节点的下一个节点
            next = headTemp.next;
            // 将headTemp的下一个节点指向新的链表的最前端
            headTemp.next = reversal.next;
            // 将headTemp连接到新的链表上
            reversal.next = headTemp;
            // 让headTemp后移
            headTemp = next;
        }
        // 将head.next指向reversal.next,实现链表的反转
        head.next = reversal.next;

        // 冒泡算法是错误的，时间复杂度n2
//        HeroNode reversalTemp = reversal.next;
//        while (true) {
//            HeroNode headTemp = head.next;
//            while (true) {
//                if (headTemp.next == null) {
//                    reversalTemp = headTemp;
//                    break;
//                } else {
//                    headTemp = headTemp.next;
//                }
//            }
//            if (reversalTemp == head.next) {
//                break;
//            }
//            System.out.println(reversalTemp);
//            reversalTemp = reversalTemp.next;
//        }
    }

    // 逆序打印
    // 方式1：先反转后遍历，不推荐会破坏原链表结构
    // 方式2：可以利用栈这个数据结构，将各个节点压入栈中，利用栈先进后出的特点，实现逆序打印
    public void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        // 创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.next;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序的单链表，合并之后链表依然有序
    public void mergeLinkedList(HeroNode head1, HeroNode head2) {
        if (head1.next == null || head2.next == null) {
            return;
        }
        HeroNode mergeHead = new HeroNode();
        HeroNode tempHead1 = head1.next;
        HeroNode tempHead2 = head2.next;
        // 记录新链表的末尾位置，这样就不用来回循环去增加到末尾了(add需要每次都遍历一次，在此时没有必要因为是有序的)
        HeroNode next = mergeHead;
        while (tempHead1 != null && tempHead2 != null) {
            if (tempHead1.rank <= tempHead2.rank) {
                // 将mergeHead指向要插入的节点，此时节点插入
                next.next = tempHead1;
                // 推动next往后移一个节点
                next = tempHead1;
                // 遍历head1的中间量tempHead1往后移一个节点
                tempHead1 = tempHead1.next;
            } else {
                next.next = tempHead2;
                next = tempHead2;
                tempHead1 = tempHead2.next;
            }
        }
        //将原始表剩余的节点放到新链表上
        while (null != tempHead1) {
            next.next = tempHead1;
            next = tempHead1;
            tempHead1 = tempHead1.next;
        }
        while (null != tempHead2) {
            next.next = tempHead2;
            next = tempHead2;
            tempHead2 = tempHead2.next;
        }
        //将链表头指向新的链表的下一个(替换新链表的头部位置)
        head1.next = mergeHead.next;
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode("宋江", "及时雨", 1);
        HeroNode heroNode2 = new HeroNode("卢俊义", "玉麒麟", 2);
        HeroNode heroNode3 = new HeroNode("吴用", "智多星", 3);
        HeroNode heroNode4 = new HeroNode("公孙胜", "入云龙", 4);
//        HeroNode heroNode5 = new HeroNode("吴用", "智多星", 3);
        System.out.println("-----------------------");
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.add2(heroNode4);
        singleLinkedList2.add2(heroNode2);
        singleLinkedList2.add2(heroNode1);
        singleLinkedList2.add2(heroNode3);
        singleLinkedList2.show();
        System.out.println("-----------------------");
        heroNode3.nickName = "无用";
        singleLinkedList2.update(heroNode3);
        singleLinkedList2.show();
        System.out.println("-----------------------");
        singleLinkedList2.remove(3);
        singleLinkedList2.show();
        System.out.println("-----------------------");
        System.out.println(singleLinkedList2.getLength(singleLinkedList2.getHead()));
        System.out.println(singleLinkedList2.getReciprocalNode(singleLinkedList2.getHead(), 1));
        System.out.println(singleLinkedList2.getReciprocalNode(singleLinkedList2.getHead(), 2));
        System.out.println("-----------------------");
        singleLinkedList2.show();
        singleLinkedList2.reversal(singleLinkedList2.getHead());
        singleLinkedList2.show();
    }

}

class HeroNode {
    // 名字
    public String name;
    // 外号
    public String nickName;
    // 排名
    public int rank;
    // 指向下一个节点
    public HeroNode next;

    public HeroNode() {
    }

    public HeroNode(String name, String nickName, int rank) {
        this.name = name;
        this.nickName = nickName;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", rank=" + rank +
                '}';
    }
}

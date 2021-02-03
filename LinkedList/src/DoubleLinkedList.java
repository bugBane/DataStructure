public class DoubleLinkedList {
    // 头节点
    private HeroNode2 head;

    public DoubleLinkedList() {
        this.head = new HeroNode2();
    }

    public HeroNode2 getHead() {
        return head;
    }

    // 1.不考虑当前节点编号
    // 找到当前链表的最后一个节点
    // 将这个节点的next指向新的节点
    public void add(HeroNode2 heroNode) {
        // 因为头节点不能动，我们可以通过一个辅助指针（变量）来帮助找到添加的位置
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    // 2.根据排名将英雄插入到指定位置（如果有这个排名将添加失败并且给出错误提示）
    // 从head向next寻找链表的节点判断rank大小，相同提示报错
    // 找到要插入的位置使用temp变量来插入
    public void add2(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            heroNode.prev = temp;
            if (heroNode.next != null) {
                heroNode.next.prev = heroNode;
            }
        }

    }

    //修改节点信息，根据rank编号来修改，即rank不会改变。
    public void update(HeroNode2 heroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        HeroNode2 temp = head.next;
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

    //删除节点信息，根据rank编号来删除。与单链表有点不同，可以不借助上一个节点就能自删。
    public void remove(int rank) {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        // 双向链表自身节点就可以实现删除，所以temp不必等于删除节点的上一个节点
        HeroNode2 temp = head.next;
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
            temp.prev.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
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
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        HeroNode2 heroNode1 = new HeroNode2("宋江", "及时雨", 1);
        HeroNode2 heroNode2 = new HeroNode2("卢俊义", "玉麒麟", 2);
        HeroNode2 heroNode3 = new HeroNode2("吴用", "智多星", 3);
        HeroNode2 heroNode4 = new HeroNode2("公孙胜", "入云龙", 4);
//        HeroNode heroNode5 = new HeroNode("吴用", "智多星", 3);
        System.out.println("-----------------------");
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add2(heroNode4);
        doubleLinkedList.add2(heroNode2);
        doubleLinkedList.add2(heroNode1);
        doubleLinkedList.add2(heroNode3);
        doubleLinkedList.show();
        System.out.println("-----------------------");
        heroNode3.nickName = "无用";
        doubleLinkedList.update(heroNode3);
        doubleLinkedList.show();
        System.out.println("-----------------------");
        doubleLinkedList.remove(3);
        doubleLinkedList.show();
    }
}

class HeroNode2 {
    // 名字
    public String name;
    // 外号
    public String nickName;
    // 排名
    public int rank;
    // 指向下一个节点
    public HeroNode2 next;
    // 指向前一个节点
    public HeroNode2 prev;

    public HeroNode2() {
    }

    public HeroNode2(String name, String nickName, int rank) {
        this.name = name;
        this.nickName = nickName;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", rank=" + rank +
                '}';
    }
}
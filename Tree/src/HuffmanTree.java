import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        List<HuffmanNode> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new HuffmanNode(arr[i]));
        }
//        System.out.println(list);
        prevOrder(createHuffmanTree(list));
    }

    public static void prevOrder(HuffmanNode huffmanNode) {
        if (huffmanNode != null) {
            huffmanNode.prevOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    // 创建赫夫曼树
    public static HuffmanNode createHuffmanTree(List<HuffmanNode> list) {
        // 循环1-2-3过程
        while (list.size() > 1) {
            // 1.排序
            // 从小到大 this.value-o.value
            Collections.sort(list);
            // 2.创建新节点带权路径长度=两个最小子节点带权路径长度之和
            HuffmanNode leftNode = list.get(0);
            HuffmanNode rightNode = list.get(1);
            HuffmanNode parentNode = new HuffmanNode(leftNode.getValue() + rightNode.getValue());
            parentNode.setLeft(leftNode);
            parentNode.setRight(rightNode);
            // 3.将一颗赫夫曼二叉树加入，将之前节点删除
            list.remove(leftNode);
            list.remove(rightNode);
            list.add(parentNode);
        }
        return list.get(0);
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    // 权值
    private int value;
    // 左子节点
    private HuffmanNode left;
    // 右子节点
    private HuffmanNode right;

    public HuffmanNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                '}';
    }

    // 前序遍历
    public void prevOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.prevOrder();
        }
        if (this.right != null) {
            this.right.prevOrder();
        }
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.value - o.value;
    }
}

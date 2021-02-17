import java.util.Stack;

public class ThreadedBinaryTree {
    private ThreadedTreeNode root;
    // 中序遍历尾节点，相当于约瑟夫问题的tail,协助线索化二叉树,默认为null
    private ThreadedTreeNode temp;

    public ThreadedTreeNode getRoot() {
        return root;
    }

    public void setRoot(ThreadedTreeNode root) {
        this.root = root;
    }

    // 中序线索化子树
    public void threadedNodes(ThreadedTreeNode threadedTreeNode) {
        if (threadedTreeNode == null) {
            return;
        }
        // 线索化左子树
        this.threadedNodes(threadedTreeNode.getLeft());
        // 线索化当前树
        // 给当前节点设置前驱节点(遍历尾节点)
        if (threadedTreeNode.getLeft() == null) {
            threadedTreeNode.setLeft(temp);
            threadedTreeNode.setLeftType(1);
        }
        // 给遍历尾节点设置后继节点(当前节点)
        if (temp != null && temp.getRight() == null) {
            temp.setRight(threadedTreeNode);
            temp.setRightType(1);
        }
        // 将当前节点设置为遍历尾节点
        temp = threadedTreeNode;
        // 线索化右子树
        this.threadedNodes(threadedTreeNode.getRight());
    }

    // 中序遍历：线索化树不需要使用递归和root就可以
    public void threadedOrder(ThreadedTreeNode threadedTreeNode) {
        if (threadedTreeNode == null) {
            return;
        }
        while (threadedTreeNode != null) {
            // 左子树找到左叶子节点(中序遍历的第一个节点)
            while (threadedTreeNode.getLeftType() == 0) {
                threadedTreeNode = threadedTreeNode.getLeft();
            }
            // 打印左叶子节点
            System.out.println(threadedTreeNode);
            // 从左叶子节点到父节点(一直找到父节点为止，要不会无法打印右子节点，比如例子中去掉了5用if就会死循环，1会不断的循环)
            //                      1
            //                2           3
            //            4           6       7
            while (threadedTreeNode.getRightType() == 1) {
                threadedTreeNode = threadedTreeNode.getRight();
                // 打印节点
                System.out.println(threadedTreeNode);
            }
            // 父节点rightType为0，需要设置threadedTreeNode继续往下遍历(右子节点)
            threadedTreeNode = threadedTreeNode.getRight();
        }
    }

    // 非递归(但也是栈思想)中序遍历 左父右
    public void infixOrder(ThreadedTreeNode threadedTreeNode) {
        if (threadedTreeNode == null) {
            return;
        }
        Stack<ThreadedTreeNode> stack = new Stack<>();
        stack.push(threadedTreeNode);
        // 如果栈不为空
        while (!stack.isEmpty()) {
            // 找到threadedTreeNode节点的左叶子节点
            while (stack.peek().getLeft() != null) {
                // 将左叶子节点压入栈中
                stack.push(stack.peek().getLeft());
            }
            // 打印左叶子节点和右叶子节点
            System.out.println(stack.pop());
            // 如果栈不为空
            while (!stack.isEmpty()) {
                // 中间量父节点
                threadedTreeNode = stack.peek();
                // 打印父节点
                System.out.println(stack.pop());
                // 如果父节点有右子节点
                if (threadedTreeNode.getRight() != null) {
                    // 压入右子节点
                    stack.push(threadedTreeNode.getRight());
                    break;
                }
            }

        }
    }

    // 非递归(但也是栈思想)先序遍历 父左右
    public void prevOrder(ThreadedTreeNode threadedTreeNode) {
        if (threadedTreeNode == null) {
            return;
        }
        Stack<ThreadedTreeNode> stack = new Stack<>();
        stack.push(threadedTreeNode);
        // 如果栈不为空
        while (!stack.isEmpty()) {
            // 找到threadedTreeNode节点的左叶子节点
            while (stack.peek().getLeft() != null) {
                // // 打印父节点
                System.out.println(stack.peek());
                // 将左叶子节点压入栈中
                stack.push(stack.peek().getLeft());
            }
            // 打印左叶子节点和右叶子节点
            System.out.println(stack.pop());
            // 如果栈不为空
            while (!stack.isEmpty()) {
                // 中间量父节点
                threadedTreeNode = stack.peek();
                stack.pop();
                // 如果父节点有右子节点
                if (threadedTreeNode.getRight() != null) {
                    // 压入右子节点
                    stack.push(threadedTreeNode.getRight());
                    break;
                }
            }

        }
    }

    public static void main(String[] args) {
        ThreadedTreeNode treeNode1 = new ThreadedTreeNode(1, "宋江");
        ThreadedTreeNode treeNode2 = new ThreadedTreeNode(2, "卢俊义");
        ThreadedTreeNode treeNode3 = new ThreadedTreeNode(3, "吴用");
        ThreadedTreeNode treeNode4 = new ThreadedTreeNode(4, "公孙胜");
        ThreadedTreeNode treeNode5 = new ThreadedTreeNode(5, "关胜");
        ThreadedTreeNode treeNode6 = new ThreadedTreeNode(6, "林冲");
        ThreadedTreeNode treeNode7 = new ThreadedTreeNode(7, "秦明");

        ThreadedBinaryTree binaryTree = new ThreadedBinaryTree();
        binaryTree.setRoot(treeNode1);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode2.setLeft(treeNode4);
//        treeNode2.setRight(treeNode5);
        treeNode3.setLeft(treeNode6);
        treeNode3.setRight(treeNode7);

        // 中序遍历：4 2 5 1 6 3 7
        // 线索化二叉树
//        binaryTree.threadedNodes(treeNode1);
        // 遍历线索二叉树
//        binaryTree.threadedOrder(treeNode1);
        System.out.println("--------------------");
        // 使用非递归中序遍历
        binaryTree.infixOrder(treeNode1);
        // 使用非递归前序遍历有问题
//        binaryTree.prevOrder(treeNode1);

    }

}

class ThreadedTreeNode {
    private int value;
    private String name;
    // 指向左子节点或者前驱节点
    private ThreadedTreeNode left;
    // 指向右子节点或者后继节点
    private ThreadedTreeNode right;
    // 节点标记：0代表左子节点，1代表前驱节点，默认为0
    private int leftType;
    // 节点标记：0代表右子节点，1代表后继节点，默认为0
    private int rightType;

    public ThreadedTreeNode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ThreadedTreeNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedTreeNode left) {
        this.left = left;
    }

    public ThreadedTreeNode getRight() {
        return right;
    }

    public void setRight(ThreadedTreeNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "ThreadedTreeNode{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}
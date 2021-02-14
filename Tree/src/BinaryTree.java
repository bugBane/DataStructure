public class BinaryTree {
    private TreeNode root;

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    // 前序遍历
    public void prevOrder() {
        if (this.root != null) {
            this.root.prevOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序查找
    public TreeNode prevOrderSearch(int value) {
        if (this.root != null) {
            return this.root.prevOrderSearch(value);
        } else {
            return null;
        }
    }

    // 中序查找
    public TreeNode infixOrderSearch(int value) {
        if (this.root != null) {
            return this.root.infixOrderSearch(value);
        } else {
            return null;
        }
    }

    // 后序查找
    public TreeNode postOrderSearch(int value) {
        if (this.root != null) {
            return this.root.postOrderSearch(value);
        } else {
            return null;
        }
    }

    // 删除
    public TreeNode remove(int value) {
        TreeNode node = null;
        if (this.root != null) {
            if (this.root.getValue() == value) {
                node = this.root;
                this.root = null;
                return node;
            }
            return this.root.remove(value);
        }
        return node;
    }

    // 测试
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1, "宋江");
        TreeNode treeNode2 = new TreeNode(2, "卢俊义");
        TreeNode treeNode3 = new TreeNode(3, "吴用");
        TreeNode treeNode4 = new TreeNode(4, "公孙胜");
        TreeNode treeNode5 = new TreeNode(5, "关胜");

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(treeNode1);
        treeNode1.setLeft(treeNode2);
        treeNode1.setRight(treeNode3);
        treeNode3.setLeft(treeNode4);
        treeNode3.setRight(treeNode5);

        // 前序：1 2 3 4 5
        System.out.println("--------前序遍历---------");
        binaryTree.prevOrder();
        // 中序：2 1 4 3 5
        System.out.println("--------中序遍历---------");
        binaryTree.infixOrder();
        // 后序：2 4 5 3 1
        System.out.println("--------后序遍历---------");
        binaryTree.postOrder();

        System.out.println("--------前序查找---------");
        System.out.println(binaryTree.prevOrderSearch(3));
        System.out.println("--------中序查找---------");
        System.out.println(binaryTree.infixOrderSearch(3));
        System.out.println("--------后序查找---------");
        System.out.println(binaryTree.postOrderSearch(3));

        System.out.println("--------删除2---------");
        System.out.println(binaryTree.remove(2));
        System.out.println("--------前序遍历---------");
        binaryTree.prevOrder();
    }
}

class TreeNode {
    private int value;
    private String name;
    private TreeNode left;
    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int value, String name) {
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

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", name='" + name + '\'' +
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

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    // 前序查找
    public TreeNode prevOrderSearch(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.left != null) {
            TreeNode left = this.left.prevOrderSearch(value);
            if (left != null) {
                return left;
            }
        }
        if (this.right != null) {
            TreeNode right = this.right.prevOrderSearch(value);
            if (right != null) {
                return right;
            }
        }
        return null;
    }

    // 中序查找
    public TreeNode infixOrderSearch(int value) {
        if (this.left != null) {
            TreeNode left = this.left.infixOrderSearch(value);
            if (left != null) {
                return left;
            }
        }
        if (this.value == value) {
            return this;
        }
        if (this.right != null) {
            TreeNode right = this.right.infixOrderSearch(value);
            if (right != null) {
                return right;
            }
        }
        return null;
    }

    // 后序查找
    public TreeNode postOrderSearch(int value) {
        if (this.left != null) {
            TreeNode left = this.left.postOrderSearch(value);
            if (left != null) {
                return left;
            }
        }
        if (this.right != null) {
            TreeNode right = this.right.postOrderSearch(value);
            if (right != null) {
                return right;
            }
        }
        if (this.value == value) {
            return this;
        }
        return null;
    }

    // 删除
    public TreeNode remove(int value) {
        // 单链表删除节点需要借助上一个节点
        TreeNode node = null;
        if (this.left != null) {
            if (this.left.value == value) {
                node = this.left;
                this.left = null;
                return node;
            }
            node = this.left.remove(value);
            if (node != null) {
                return node;
            }
        }
        if (this.right != null) {
            if (this.right.value == value) {
                node = this.right;
                this.right = null;
                return node;
            }
            node = this.right.remove(value);
            if (node != null) {
                return node;
            }
        }
        return node;
    }

}

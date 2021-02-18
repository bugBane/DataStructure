public class BinarySortTree {
    // 根节点
    private BinarySortNode root;

    // 添加
    public void add(BinarySortNode binarySortNode) {
        if (root == null) {
            root = binarySortNode;
            return;
        }
        root.add(binarySortNode);
    }

    // 返回以node为根节点的二叉排序树最小节点的值
    // 注意：二叉排序树不会出现
    //          4
    //              5
    //           4
    public int delRightTreeMin(BinarySortNode binarySortNode) {
        BinarySortNode temp = binarySortNode;
        while (temp.getLeft() != null) {
            temp = temp.getLeft();
        }
        // 因为二叉排序树不会存在相等值的节点，所以直接按照value删除就行
        del(temp.getValue());
        return temp.getValue();
    }

    public void del(int value) {
        // root为空不删除
        if (root == null) {
            return;
        }
        // 二叉排序树没有该节点，不删除
        BinarySortNode binarySortNode = search(value);
        if (binarySortNode == null) {
            return;
        }
        // 只有根节点
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        // 查找父节点
        BinarySortNode parent = searchParent(value);
        // 删除叶子节点 找到叶子节点就可以直接删除
        if (binarySortNode.getLeft() == null && binarySortNode.getRight() == null) {
            if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                parent.setLeft(null);
                return;
            }
            if (parent.getRight() != null && parent.getRight().getValue() == value) {
                parent.setRight(null);
                return;
            }
        }
        // 该节点既有左节点又有右节点
        if (binarySortNode.getLeft() != null && binarySortNode.getRight() != null) {
            // 注意：因为不会存在该节点的右子节点下的最小值节点小于该值，所以只需要将该节点的值改为右子节点下的最小节点的值(相当于替换了)，将最小节点的值删除，此时还是二叉排序树
            int min = delRightTreeMin(binarySortNode.getRight());
            binarySortNode.setValue(min);
            return;
        }
        // 此时只剩下一种情况：左节点不为空或者右节点不为空  删除子节点 节点只有一个子节点
        // 如果只有左子节点
        if (binarySortNode.getLeft() != null) {
            // 如果根节点就挂了一个左节点，此时删除根节点的时候根节点父类为空会报空指针异常
            if (parent != null) {
                // 将父节点的左节点直接连接该节点的左节点
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    parent.setLeft(binarySortNode.getLeft());
                    return;
                }
                // 将父节点的右节点直接连接该节点的左节点
                // 注意：不会存在该节点的左子节点下的最大值节点大于该值，因为是添加的时候就进行判断了，所以可以直接连接
                if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    parent.setRight(binarySortNode.getLeft());
                    return;
                }
            } else {
                root = binarySortNode.getLeft();
            }
            // 只有右节点
        } else {
            // 如果根节点就挂了一个右节点，此时删除根节点的时候根节点父类为空会报空指针异常
            if (parent != null) {
                // 将父节点的左节点直接连接该节点的右节点
                // 注意：不会存在该节点的右子节点下的最小值节点小于该值，因为是添加的时候就进行判断了，所以可以直接连接
                if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                    parent.setLeft(binarySortNode.getRight());
                    return;
                }
                // 将父节点的右节点直接连接该节点的右节点
                if (parent.getRight() != null && parent.getRight().getValue() == value) {
                    parent.setRight(binarySortNode.getRight());
                    return;
                }
            } else {
                root = binarySortNode.getRight();
            }
        }
    }

    // 查找
    public BinarySortNode search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找父节点
    public BinarySortNode searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    // 中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空");
            return;
        }
        root.infixOrder();
    }

    // 测试
    public static void main(String[] args) {
        // 最后的4节点是加不进去的，因为二叉排序树不允许重复
        int[] arr = {10, 8, 12, 4, 2, 5, 1, 9, 6, 4};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new BinarySortNode(arr[i]));
        }
        binarySortTree.infixOrder();
//        System.out.println(binarySortTree.search(5));
//        System.out.println(binarySortTree.searchParent(5));
        System.out.println("-----------------------");
        // 删除叶子节点
//        binarySortTree.del(12);
        // 删除子节点单个
//        binarySortTree.del(2);
        // 删除子节点树
        binarySortTree.del(4);
        binarySortTree.infixOrder();
    }

}

class BinarySortNode {
    // 值
    private int value;
    // 左子节点
    private BinarySortNode left;
    // 右子节点
    private BinarySortNode right;

    public BinarySortNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinarySortNode getLeft() {
        return left;
    }

    public void setLeft(BinarySortNode left) {
        this.left = left;
    }

    public BinarySortNode getRight() {
        return right;
    }

    public void setRight(BinarySortNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinarySortNode{" +
                "value=" + value +
                '}';
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

    // 添加
    public void add(BinarySortNode binarySortNode) {
        // 二叉排序树如果添加的时候已经存在相等的值，那么就对其进行修改，不添加。例如HashMap的 数组+红黑树 put方法，key就是不可重复的
        // 因为只有value值，所以就不用做处理了
        if (this.value == binarySortNode.value) {
            return;
        }
        // 大于放在右边
        if (this.value < binarySortNode.value) {
            if (this.right != null) {
                this.right.add(binarySortNode);
            } else {
                this.right = binarySortNode;
            }
            // 小于在左边
        } else {
            if (this.left != null) {
                this.left.add(binarySortNode);
            } else {
                this.left = binarySortNode;
            }
        }
    }

    // 查找
    public BinarySortNode search(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value > value) {
            // 因为是有序的，所以一次就可以找到；如果是普通二叉树需要返回left进行非空判断
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    // 查找父类
    public BinarySortNode searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        if (this.value > value) {
            if (this.left != null) {
                return this.left.searchParent(value);
            }
            return null;
        } else {
            if (this.right != null) {
                return this.right.searchParent(value);
            }
            return null;
        }
    }
}

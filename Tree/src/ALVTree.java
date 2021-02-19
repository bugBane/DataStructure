public class ALVTree {
    // 根节点
    private ALVNode root;

    // 添加
    public void add(ALVNode alvNode) {
        if (root == null) {
            root = alvNode;
            return;
        }
        root.add(alvNode);
    }

    // 树的高度
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return root.getHeight();
    }

    // 左子树高度
    public int getLeftHeight(ALVNode alvNode) {
        if (root == null) {
            return 0;
        }
        return alvNode.getLeftHeight();
    }

    // 右子树高度
    public int getRightHeight(ALVNode alvNode) {
        if (root == null) {
            return 0;
        }
        return alvNode.getRightHeight();
    }

    // 中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空");
            return;
        }
        root.infixOrder();
    }

    public int delRightTreeMin(ALVNode alvNode) {
        ALVNode temp = alvNode;
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
        ALVNode alvNode = search(value);
        if (alvNode == null) {
            return;
        }
        // 只有根节点，根节点就是要删除的值
        if (root.getLeft() == null && root.getRight() == null) {
            root = null;
            return;
        }
        // 查找父节点
        ALVNode parent = searchParent(value);
        // 删除叶子节点 找到叶子节点就可以直接删除
        if (alvNode.getLeft() == null && alvNode.getRight() == null) {
            // 如果为左叶子节点
            if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                parent.setLeft(null);
            } else {
                // 右叶子节点
                parent.setRight(null);
            }
            // 该节点既有左节点又有右节点
        } else if (alvNode.getLeft() != null && alvNode.getRight() != null) {
            // 注意：因为不会存在该节点的右子节点下的最小值节点小于该值，所以只需要将该节点的值改为右子节点下的最小节点的值(相当于替换了)，将最小节点的值删除，此时还是二叉排序树
            int min = delRightTreeMin(alvNode.getRight());
            alvNode.setValue(min);
        } else {
            // 此时只剩下一种情况：左节点不为空或者右节点不为空  删除子节点 节点只有一个子节点
            // 如果只有左子节点
            if (alvNode.getLeft() != null) {
                // 如果根节点就挂了一个左节点，此时删除根节点的时候根节点父类为空会报空指针异常
                if (parent != null) {
                    // 将父节点的左节点直接连接该节点的左节点
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(alvNode.getLeft());
                    } else {
                        // 将父节点的右节点直接连接该节点的左节点
                        // 注意：不会存在该节点的左子节点下的最大值节点大于该值，因为是添加的时候就进行判断了，所以可以直接连接
                        parent.setRight(alvNode.getLeft());
                    }
                } else {
                    // 只有根节点不需要平衡
                    root = alvNode.getLeft();
                    return;
                }
            } else {
                // 只有右节点
                // 如果根节点就挂了一个右节点，此时删除根节点的时候根节点父类为空会报空指针异常
                if (parent != null) {
                    // 将父节点的左节点直接连接该节点的右节点
                    // 注意：不会存在该节点的右子节点下的最小值节点小于该值，因为是添加的时候就进行判断了，所以可以直接连接
                    if (parent.getLeft() != null && parent.getLeft().getValue() == value) {
                        parent.setLeft(alvNode.getRight());
                    } else {
                        // 将父节点的右节点直接连接该节点的右节点
                        parent.setRight(alvNode.getRight());
                    }
                } else {
                    // 只有根节点不需要平衡
                    root = alvNode.getRight();
                    return;
                }
            }
        }
        // 删除完元素之后进行平衡
        root.balanceALVTree();
    }

    // 查找
    public ALVNode search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    // 查找父节点
    public ALVNode searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    public static void main(String[] args) {
        // 需要左旋
//        int[] arr = {4, 3, 6, 5, 7, 8};
        // 需要右旋
//        int[] arr = {10, 11, 8, 9, 7, 6, 5};
        // 需要双旋(其实是两种情况，添加的时候要左旋或者右旋前提是要左旋的右节点是平衡的或者要右旋的左节点是平衡的，如果不平衡那么旋转之后还是不平衡)
        // 如下图：添加9的时候很明显左子树高度高，但是如果只是右旋并不能平衡，需要右旋前先把7节点左旋平衡之后再对10进行右旋
        //                 10
        //      7                  11
        //  6       8
        //              9
        int[] arr = {10, 11, 7, 6, 8, 9};
        ALVTree alvTree = new ALVTree();
        for (int i = 0; i < arr.length; i++) {
            alvTree.add(new ALVNode(arr[i]));
        }
//        alvTree.infixOrder();
        // 树高度
        System.out.println(alvTree.getHeight());
        // 树左节点高度
        System.out.println(alvTree.getLeftHeight(alvTree.root));
        // 树右节点高度
        System.out.println(alvTree.getRightHeight(alvTree.root));
        System.out.println("-----------删除----------");
        alvTree.del(6);
        alvTree.del(7);
        alvTree.del(11);
        // 树高度
        System.out.println(alvTree.getHeight());
        // 树左节点高度
        System.out.println(alvTree.getLeftHeight(alvTree.root));
        // 树右节点高度
        System.out.println(alvTree.getRightHeight(alvTree.root));
    }
}

class ALVNode {
    // 值
    private int value;
    // 左子节点
    private ALVNode left;
    // 右子节点
    private ALVNode right;

    public ALVNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ALVNode getLeft() {
        return left;
    }

    public void setLeft(ALVNode left) {
        this.left = left;
    }

    public ALVNode getRight() {
        return right;
    }

    public void setRight(ALVNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "ALVNode{" +
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
    public void add(ALVNode alvNode) {
        // 二叉排序树如果添加的时候已经存在相等的值，那么就对其进行修改，不添加。例如HashMap的 数组+红黑树 put方法，key就是不可重复的
        // 因为只有value值，所以就不用做处理了
        if (this.value == alvNode.value) {
            return;
        }
        // 大于放在右边
        if (this.value < alvNode.value) {
            if (this.right != null) {
                this.right.add(alvNode);
            } else {
                this.right = alvNode;
            }
            // 小于在左边
        } else {
            if (this.left != null) {
                this.left.add(alvNode);
            } else {
                this.left = alvNode;
            }
        }
        // 添加完一个节点进行平衡
        // 因为是递归的原因，添加完一个节点以后，从该节点往父节点一层一层平衡，一直到根节点(root.add)
        balanceALVTree();
    }

    // 平衡二叉树
    public void balanceALVTree() {
        // (要左旋或者右旋前提是要左旋的右节点是平衡的或者要右旋的左节点是平衡的，如果不平衡那么旋转之后还是不平衡)
        if (getRightHeight() - getLeftHeight() > 1) {
            // 左旋时如果右节点不平衡(右节点的左节点高于右节点的左节点，先右旋右节点保持右节点平衡)
            if (this.right != null && this.right.getLeftHeight() > this.right.getRightHeight()) {
                this.right.dextroRotation();
            }
            // 左旋
            levoRotation();
            // 添加是一个一个的添加，此时处理完了之后就证明这一个添加完毕了
            return;
        }
        if (getLeftHeight() - getRightHeight() > 1) {
            // 右旋时如果左节点不平衡(左节点的右节点高于左节点的左节点，先左旋左节点保持左节点平衡)
            if (this.left != null && this.left.getRightHeight() > this.left.getLeftHeight()) {
                this.left.levoRotation();
            }
            // 右旋
            dextroRotation();
        }
    }

    // 返回左子树的高度
    public int getLeftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.getHeight();
    }

    // 返回右子树的高度
    public int getRightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.getHeight();
    }

    // 返回树节点的高度
    public int getHeight() {
        int left = 0, right = 0;
        if (this.left != null) {
            left = this.left.getHeight();
        }
        if (this.right != null) {
            right = this.right.getHeight();
        }
        if (left < right) {
            return right + 1;
        } else {
            return left + 1;
        }
//        return Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight()) + 1;
    }

    // 左旋
    public void levoRotation() {
        // 定义一个中间节点
        ALVNode temp = new ALVNode(this.value);
        // 中间节点的左节点为原节点的左节点
        temp.left = this.left;
        // 中间节点的右节点为原节点的右节点的左节点
        temp.right = this.right.left;
        // 此时已经构建好了一个temp节点，只需要将原节点左移(只是修改了值，原节点的结构没有发生变化，需要将原结构重新调整)
        this.value = this.right.value;
        // 新左移节点连接左节点(使用原节点)
        this.left = temp;
        // 新左移点节点连接右节点(使用原节点)
        this.right = this.right.right;
        // 执行完之后就相当于将原节点重新构建成一个新的左移节点
        // 原节点左移过程中只是修改值，所以左移之前的原节点的右节点还存在，但是没有任何节点指向它，会被gc回收(虽然它连接其它节点但是可达性分析并不能找到)
    }

    // 右旋
    public void dextroRotation() {
        // 定义一个中间节点
        ALVNode temp = new ALVNode(this.value);
        // 中间节点的右节点为原节点的右节点
        temp.right = this.right;
        // 中间节点的左节点为原节点的左节点的右节点
        temp.left = this.left.right;
        // 此时已经构建好了一个temp节点，只需要将原节点右移(只是修改了值，原节点的结构没有发生变化，需要将原结构重新调整)
        this.value = this.left.value;
        // 新右移节点连接左节点
        this.right = temp;
        // 新右移点节点连接右节点(原节点结构没有发生变化)
        this.left = this.left.left;
        // 执行完之后就相当于将原节点重新构建成一个新的左移节点
        // 原节点右移过程中只是修改值，所以右移之前的原节点的左节点还存在，但是没有任何节点指向它，会被gc回收(虽然它连接其它节点但是可达性分析并不能找到)
    }

    // 查找
    public ALVNode search(int value) {
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
    public ALVNode searchParent(int value) {
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

public class ArrayBinaryTree {
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        // 前序遍历：1 2 4 5 3 6 7
        System.out.println("--------前序遍历---------");
        arrayBinaryTree.prevOrder();
        // 中序遍历：4 2 5 1 6 3 7
        System.out.println("--------中序遍历---------");
        arrayBinaryTree.infixOrder();
        // 后序遍历：4 5 2 6 7 3 1
        System.out.println("--------后序遍历---------");
        arrayBinaryTree.postOrder();
    }

    // 前序遍历
    public void prevOrder() {
        prevOrder(0);
    }

    /**
     * @param index 数组下标
     */
    private void prevOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，没有元素");
            return;
        }
        System.out.println(arr[index]);
        int leftIndex = 2 * index + 1;
        if (leftIndex < arr.length) {
            this.prevOrder(leftIndex);
        }
        int rightIndex = 2 * index + 2;
        if (rightIndex < arr.length) {
            this.prevOrder(rightIndex);
        }
    }

    // 中序遍历
    public void infixOrder() {
        infixOrder(0);
    }

    /**
     * @param index 数组下标
     */
    private void infixOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，没有元素");
            return;
        }
        int leftIndex = 2 * index + 1;
        if (leftIndex < arr.length) {
            this.infixOrder(leftIndex);
        }
        System.out.println(arr[index]);
        int rightIndex = 2 * index + 2;
        if (rightIndex < arr.length) {
            this.infixOrder(rightIndex);
        }
    }

    // 后序遍历
    public void postOrder() {
        postOrder(0);
    }

    /**
     * @param index 数组下标
     */
    private void postOrder(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，没有元素");
            return;
        }
        int leftIndex = 2 * index + 1;
        if (leftIndex < arr.length) {
            this.postOrder(leftIndex);
        }
        int rightIndex = 2 * index + 2;
        if (rightIndex < arr.length) {
            this.postOrder(rightIndex);
        }
        System.out.println(arr[index]);
    }
}

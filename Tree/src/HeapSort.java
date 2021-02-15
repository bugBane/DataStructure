import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {40, 25, 50, 10, 45, 15, 30, 20, 35};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
        long before = System.currentTimeMillis();
        // 1000万数据：3491ms nlogn
        headSort(arr);
        long later = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    // 堆排序
    public static void headSort(int[] arr) {
        // 因为结构为顺序二叉树，所以最后一个子节点为arr.length/2-1
        // i--为倒着设置节点，一直到根节点，就成为一个稳定的大顶堆结构，此时根节点为最大值。
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 大顶堆结构为{50, 45, 40, 35, 25, 15, 30, 20, 10}
        //          50
        //      45      40
        //   35   25 15    30
        // 20  10
//        System.out.println(Arrays.toString(arr));
        int temp = 0;
        // i需要逐步调整
        for (int i = arr.length - 1; i >= 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 因为已经为大顶堆，所以只需要调整i节点就可以(只需要一次就可以再次成为大顶堆)
            //          10
            //      45      40
            //   35   25 15    30
            // 20  50
            adjustHeap(arr, 0, i);
            // 调整之后为：(再次循环调整)
            //          45
            //      35      40
            //   20   25 15    30
            // 10  50
        }
    }

    // 设置i节点为大顶堆结构
    private static void adjustHeap(int[] arr, int i, int length) {
        // 中间量存放当前节点值用来交换值
        int temp = arr[i];
        // 比较i父节点、左右子节点大小，使i父节点为最大数
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 先比较i节点的左右子节点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 右子节点值大
                k++;
            }
            // 比较i节点与最大子节点
            if (temp < arr[k]) {
                // 将arr[i]设置为最大数
                arr[i] = arr[k];
                arr[k] = temp;
                // 往下循环用来设置节点值
                i = k;
            } else {
                // 如果i节点本身就大，就不用调整了(大顶堆结构)
                break;
            }
        }
    }
}

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 1, 2};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
        long before = System.currentTimeMillis();
        // 1000万数据：2664ms
        // 将中间量tempArr提出来，递归会额外增加时间消耗
        merge(arr, new int[arr.length], 0, arr.length - 1);
        long later = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    private static void merge(int[] arr, int[] tempArr, int left, int right) {
        // 归并排序核心思想是分治算法，先分在治
        // 分：一直折半分
        // 治：将分开的每一个元素，两两比较按照从小到大再合并
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左分
            merge(arr, tempArr, left, mid);
            // 向右分
            merge(arr, tempArr, mid + 1, right);
            // 治
            govern(arr, tempArr, left, right, mid);
        }
    }

    // 将分开的元素按照大小合并，相当于合并两个有序链表
    private static void govern(int[] arr, int[] tempArr, int left, int right, int mid) {
        // 遍历左数组中间量索引
        int temp1 = left;
        // 遍历右数组中间量索引
        int temp2 = mid + 1;
        // 中间数组temp中间量索引
        int next = 0;
        // 中间量数组
        // int[] temp = new int[right-left+1];
        while (temp1 <= mid && temp2 <= right) {
            if (arr[temp1] >= arr[temp2]) {
                tempArr[next] = arr[temp2];
                next++;
                temp2++;
            } else {
                tempArr[next] = arr[temp1];
                next++;
                temp1++;
            }
        }
        while (temp1 <= mid) {
            tempArr[next] = arr[temp1];
            next++;
            temp1++;
        }
        while (temp2 <= right) {
            tempArr[next] = arr[temp2];
            next++;
            temp2++;
        }
        // 将tempArr拷贝到arr(链表只需要head.next=tempHead.next)
        next = 0;
        for (int tempLeft = left; tempLeft <= right; tempLeft++) {
            arr[tempLeft] = tempArr[next];
            next++;
        }
    }
}

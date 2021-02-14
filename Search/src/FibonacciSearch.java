import java.util.Arrays;

public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = fibonacciSearch(arr, 0, arr.length - 1, 0);
        if (index < 0) {
            System.out.println("没找到");
        } else {
            System.out.println("下标为" + index);
        }
    }

    private static int fibonacciSearch(int[] arr, int left, int right, int value) {
        // 斐波那契数组
        int[] fibArr = fib();
        // 接近黄金分割的数组长度
        int k = 0;
        while (arr.length > fibArr[k] - 1) {
            k++;
        }
        // 扩充当前数组长度为斐波那契数组长度
        // {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0};
        int[] temp = Arrays.copyOf(arr, fibArr[k]);
        // {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9};
        for (int i = arr.length; i < temp.length; i++) {
            temp[i] = temp[arr.length - 1];
        }
        while (left <= right) {
            int mid = left + fibArr[k - 1] - 1;
            // 全部元素= 前+后 即 fibArr[k] = fibArr[k-1] + fibArr[k-2]
            if (value < temp[mid]) {
                // 小于那么需要向前查找，先排除mid：right = mid-1
                right = mid - 1;
                // 然后左移fibArr[k-1]=fibArr[k-2]+fibArr[k-3]
                k--;
            } else if (value > temp[mid]) {
                //大于那么需要向后查找，先排除mid：left = mid+1
                left = mid + 1;
                // 然后右移fibArr[k-2]=fibArr[k-3]+fibArr[k-4]
                k -= 2;
            } else {
                // 因为扩充过所以要对mid进行判断
                if (mid <= arr.length - 1) {
                    return mid;
                } else {
                    return arr.length - 1;
                }
            }
        }
        return -1;
    }

    public static int[] fib() {
        int[] fibArr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            if (i == 0 || i == 1) {
                fibArr[i] = 1;
            } else {
                fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
            }
        }
        return fibArr;
    }
}

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 4, 1, 2};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
        long before = System.currentTimeMillis();
        // 100万数据：325ms
        // 1000万数据：1773ms
        quick(arr, 0, arr.length - 1);
        long later = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    private static void quick(int[] arr, int left, int right) {
        // 快速排序就是冒泡排序的升级版，将数组通过中间量进行排序，然后继续细化对半排序
        // 5, 3, 4, 4, 1, 2 =4为中轴值> 2, 3, 1, 4, 4, 5
        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴值
        int pivot = arr[(left + right) / 2];
        // 用来交换的中间量
        int temp;
        // while循环的目的是让比pivot小的值放入左边
        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果某一边到了中轴或者与中轴相等，如2, 3, 1, 4, 4, 5：arr[l]=4,arr[r]=4,l=3<r=4,不管怎么换下去如果不考虑相等情况无法跳出while循环，经过下面判断l=4>r=3,结束循环
            // 此时第一次排序已经结束，结果为{2, 3, 1, 4, 4, 5}
            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }
        }
        // 如果l与r相等，如果不将l与r错开，那么递归到最小数组为left=1，l=1，right=2，r=1,
        //      此时因为right>l会继续往下递归，l=1,r=right=2 =while循环一次> l=1,r=1 =结束while循环> 此时，left=1，l=1，right=2，r=1 无限递归下去造成死鬼(递归必须有结束条件！！！)
        // 如果将l与r错开，此时left=1，l=2，right=2，r=0 => left>r,right=l => 结束递归（因为是最小数组不需要继续分割了，left=1，right=2）
        // 递归的结束条件：每次递归确定一个中值，然后排除后(所以l与r不能相等，否则中值无法排除)继续确认，直到数组都确定完毕。
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quick(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quick(arr, l, right);
        }
    }
}

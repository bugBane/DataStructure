import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 1, 2};
        int[] arr = new int[1000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000000);
        }
        long before = System.currentTimeMillis();
//         16742ms
//        shellSwap(arr);
        // 10万数据：172ms
        // 30万数据：264ms
        // 100万数据：645ms
        shellInsert(arr);
        long later = System.currentTimeMillis();
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    // 希尔排序-交换方式
    private static void shellSwap(int[] arr) {
        int temp;
//        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            for (int i = gap; i < arr.length; i++) {
//                for (int j = i - gap; j >= gap; j -= gap) {
//                    if (arr[j] > arr[j + gap]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + gap];
//                        arr[j + gap] = temp;
//                    }
//                }
//            }
//        }
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < arr.length - gap; i++) {
                for (int j = i + gap; j < arr.length - gap; j += gap) {
                    if (arr[i] > arr[j]) {
                        temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }

    // 希尔排序-插入方式
    private static void shellInsert(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                if (arr[i] < arr[i - gap]) {
                    int j = i;
                    temp = arr[j];
                    while (j > gap && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}

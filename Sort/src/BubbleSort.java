import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 1, 2};
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        long before = System.currentTimeMillis();
        // 25765ms
        bubble(arr);
        long later = System.currentTimeMillis();
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }


    private static void bubble(int[] arr) {
        // 冒泡排序的核心在于两两比较，如果按照从小到大来排序，那么每一次遍历都将最大的值排到后面
        int temp;
        // 冒泡排序的优化，如果为true就代表发生过交换就需要再继续遍历交换，如果为false则代表已经有序不再需要继续排序
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 没有发生过交换就不需要继续排序
            if (!flag) {
                break;
            } else {
                // 发生过交换重置，继续遍历
                flag = false;
            }
        }
//        Arrays.toString(arr);
    }
}

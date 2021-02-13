import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 1, 2};
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100000);
        }
        long before = System.currentTimeMillis();
        // 20706ms
        select(arr);
        long later = System.currentTimeMillis();
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }


    private static void select(int[] arr) {
        // 选择排序的核心在于选择一点去跟其他元素比较，如果按照从小到大来排序，那么选择的元素即第一个元素为最小值
        int temp;
        // 选择排序的优化，如果为true就代表发生过交换就需要再继续遍历交换，如果为false则代表已经有序不再需要继续排序
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length - 1; j++) {
                if (arr[i] > arr[j + 1]) {
                    flag = true;
                    temp = arr[i];
                    arr[i] = arr[j + 1];
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
//        System.out.println(Arrays.toString(arr));
    }
}

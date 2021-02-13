import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {5, 3, 4, 1, 2};
        int[] arr = new int[300000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 300000);
        }
        long before = System.currentTimeMillis();
        // 10万数据：1638ms
        // 30万数据：10459ms
        insert(arr);
        long later = System.currentTimeMillis();
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    private static void insert(int[] arr) {
        // 插入排序的核心在于将原无序数组看为两个数组：原无序和新有序，如果按照从小到大来排序，那么将原无序数组的元素遍历时拿出一个元素插入新有序数组时进行比较，插入到适当位置
        // 原数组 = 新有序数组 + 原无序数组 例{()+(5, 3, 4, 1, 2)} => {(5)+(3,4,1,2)} => {(3,5)+(4,1,2)} => ... => {(1,2,3,4,5)+()}即为有序数组
        // 插入排序很像单链表-水浒里边的那个add2，每次插入时比较，只要插入到适当位置，原无序数组遍历插入完，新的有序数组就完成了
        int temp;
        // 默认0已经插入有序数组，只需要从1开始就可以
        for (int i = 1; i < arr.length; i++) {
            // 优化
            if (arr[i] < arr[i - 1]) {
                int j = i;
                temp = arr[j];
                // 重中之重！！！核心在于判断到必须要插入时，轮流用自己替换掉上一个，循环完成就代表自己就是要插入的那个位置
                while (j > 1 && temp < arr[j - 1]) {
                    arr[j] = arr[j - 1];
                    j -= 1;
                }
                // 将之前的值插入到自己位置
                arr[j] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

}

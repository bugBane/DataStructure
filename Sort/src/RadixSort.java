import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args) {
        //int[] arr = {501, 30, 4, 1011, 2};
        int[] arr = new int[10000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
        long before = System.currentTimeMillis();
        // 1000万数据：1177ms
        radix(arr);
        long later = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("排序时间为：" + String.valueOf(later - before));
    }

    private static void radix(int[] arr) {
        // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        // 1.二维数组包含10个一维数组
        // 2.为了防止放入数的时候数据溢出，则每个一维数组(桶)大小定为arr.length
        // 3.基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中实际存放了多少个数据，定义一个一维数组来记录每个桶中每次放入的数据个数
        // 比如：bucketElementCounts[0]，记录的就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];
        // 先取最大值
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        // 再取最大值的位数
        int maxLength = String.valueOf(max).length();
        // 针对每个元素的位进行排序处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的位的值
                int temp = arr[j] / n % 10;
                // 放到对应的桶中
                bucket[temp][bucketElementCounts[temp]] = arr[j];
                // 数据个数初始化为0，个数+1
                bucketElementCounts[temp]++;
            }
            // 按照桶的顺序(一维数组的下标依次取出数据，放入原来的数组)
            int index = 0;
            // 遍历每一桶，并将桶中数据放入数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据才会放入原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                    // 第i+1轮处理后，需要将每个bucketElementCounts[k]=0!!!
                    bucketElementCounts[k] = 0;
                }
            }
        }

    }
}

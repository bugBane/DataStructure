import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 6, 7, 8, 9};
        int index = binarySearch(arr, 0, arr.length-1, 0);
//        List<Integer> indexList = binarySearch2(arr, 0, arr.length, 5);
        if (index < 0) {
            System.out.println("没找到");
        } else {
            System.out.println("下标为" + index);
        }
//        System.out.println(indexList);
    }

    // 可以查询到指定value值但是无法找出所有为value的索引
    private static int binarySearch(int[] arr, int left, int right, int value) {
        System.out.println("递归一次");
        if (left <= right && value >= arr[0] && value <= arr[arr.length - 1]) {
            int mid = (left + right) / 2;
            // mid+-1和快速排序思路类似，排除掉mid值
            if (arr[mid] > value) {
                return binarySearch(arr, left, mid - 1, value);
            } else if (arr[mid] < value) {
                return binarySearch(arr, mid + 1, right, value);
            } else {
                return mid;
            }
        } else {
            return -1;
        }
    }

    // 改进：返回所有value值的索引
    private static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        if (left <= right && value >= arr[0] && value <= arr[arr.length - 1]) {
            int mid = (left + right) / 2;
            if (arr[mid] > value) {
                return binarySearch2(arr, left, mid - 1, value);
            } else if (arr[mid] < value) {
                return binarySearch2(arr, mid + 1, right, value);
            } else {
                List<Integer> indexList = new ArrayList<>();
                // 左移
                int temp = mid - 1;
                while (temp > 0 && arr[temp] == value) {
                    indexList.add(temp--);
                }
                // mid
                if (arr[mid] == value) {
                    indexList.add(mid);
                }
                // 右移
                temp = mid + 1;
                while (temp < arr.length && arr[temp] == value) {
                    indexList.add(temp++);
                }
                return indexList;
            }
        } else {
            return new ArrayList<>();
        }
    }

}

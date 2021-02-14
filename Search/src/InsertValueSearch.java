public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 5, 5, 5, 5, 5, 5, 6, 7, 8, 9};
        int index = insertValueSearch(arr, 0, arr.length-1, 0);
        if (index < 0) {
            System.out.println("没找到");
        } else {
            System.out.println("下标为" + index);
        }
//        System.out.println(indexList);
    }

    // 可以查询到指定value值但是无法找出所有为value的索引
    private static int insertValueSearch(int[] arr, int left, int right, int value) {
        System.out.println("递归一次");
        if (left <= right && value >= arr[0] && value <= arr[arr.length - 1]) {
            // 二分查找mid
//            int mid = (left + right) / 2;
            // 自适应mid
            int mid = left + (right - left) * ((value - arr[left]) / (arr[right] - arr[left]));
            if (arr[mid] > value) {
                return insertValueSearch(arr, left, mid - 1, value);
            } else if (arr[mid] < value) {
                return insertValueSearch(arr, mid + 1, right, value);
            } else {
                return mid;
            }
        } else {
            return -1;
        }
    }
}

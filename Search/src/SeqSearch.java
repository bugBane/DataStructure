public class SeqSearch {
    public static void main(String[] args) {
        // 没有顺序的数组
        int[] arr = {5, 3, 4, 1, 2};
        int index = seqSearch(arr, 1);
        if (index < 0) {
            System.out.println("没找到");
        } else {
            System.out.println("下标为" + index);
        }
    }

    private static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}

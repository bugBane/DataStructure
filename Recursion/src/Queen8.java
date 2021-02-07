public class Queen8 {
    // 皇后数量
    static int queenCount = 8;
    // 测试次数
    static int testCount = 0;
    // 成功次数
    static int correctCount = 0;
    static int[] checkerBoard = new int[queenCount];

    public static void main(String[] args) {
        // 从第一行开始放置
        place(0);
        System.out.println("测试次数：" + testCount);
        System.out.println("成功次数：" + correctCount);
    }

    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后有冲突
     *
     * @param n 表示第n个皇后
     * @return
     */
    private static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // 因为是一维数组所以不用判断有没有同一行的，一定没有同一行的
            // 判断是否有同一列的皇后
            if (checkerBoard[i] == checkerBoard[n]) {
                return false;
            }
            // 判断是否有同一斜线的皇后
            if (Math.abs(checkerBoard[n] - checkerBoard[i]) == Math.abs(n - i)) {
                return false;
            }
        }
        return true;
    }

    // 放置
    public static void place(int n) {
        // 放置完了
        if (n == queenCount) {
            // 统计成功次数
            correctCount++;
            return;
        }
        // 从本行第一列开始放置，一直到本行结束
        for (int i = 0; i < queenCount; i++) {
            // 统计测试次数
            testCount++;
            checkerBoard[n] = i;
            // 如果不冲突
            if (judge(n)) {
                // 继续往下一行放置
                place(n + 1);
            }
            // 如果冲突，就继续执行checkerBoard[n] = i;只不过i+1了；即将第n个皇后放置在本行 后移一列 的位置
        }
    }
}

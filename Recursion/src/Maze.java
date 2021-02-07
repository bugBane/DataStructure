public class Maze {

    // 可走路径为0
    static int usable = 0;
    // 墙为1
    static int wall = 1;
    // 已走路径为2
    static int road = 2;
    // 死路为3
    static int die = 3;

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        // 定义迷宫6*5,其余是墙壁
        int[][] mazeArr = new int[8][7];
        // 设置墙
        for (int i = 0; i < 7; i++) {
            mazeArr[0][i] = wall;
            mazeArr[7][i] = wall;
        }
        for (int i = 0; i < 8; i++) {
            mazeArr[i][0] = wall;
            mazeArr[i][6] = wall;
        }
        // 设置障碍墙
        mazeArr[3][1] = 1;
        mazeArr[3][2] = 1;
        mazeArr[3][3] = 1;
        mazeArr[5][4] = 1;
        mazeArr[5][5] = 1;
        print(mazeArr);
        System.out.println("--------------");
        walk(mazeArr, 1, 1);
        print(mazeArr);

    }

    // 打印数组
    public static void print(int[][] mazeArr) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(mazeArr[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 迷宫行走
    public static boolean walk(int[][] mazeArr, int i, int j) {
        // 设置终点
        if (mazeArr[6][5] == road) {
            return true;
        }
        // 当前的点没有走过
        if (mazeArr[i][j] == usable) {
            mazeArr[i][j] = road;
            // 移动策略：下
            if (walk(mazeArr, i + 1, j)) {
                return true;
            }
            // 右
            if (walk(mazeArr, i, j + 1)) {
                return true;
            }
            // 上
            if (walk(mazeArr, i - 1, j)) {
                return true;
            }
            // 左
            if (walk(mazeArr, i, j - 1)) {
                return true;
            }
            // 死路
            mazeArr[i][j] = die;
            return false;
        }
        // 如果当前点不为usable，那么就证明死路
        return false;
    }
}

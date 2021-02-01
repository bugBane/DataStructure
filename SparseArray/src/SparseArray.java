public class SparseArray {
    public static void main(String[] args) {
        //  存盘：
        // 1.创建一个二维数组去完成棋子位置功能，如上假设2行3列为白棋、3行5列为黑棋（白棋为1，黑棋为2，默认值为0）
        int checkerboard_row = 10;
        int checkerboard_column = 10;
        int[][] array1 = new int[checkerboard_row][checkerboard_column];
        array1[1][2] = 1;
        array1[2][4] = 2;
//        array1[6][9] = 1;
        for (int[] array2 : array1) {
            for (int i : array2) {
                System.out.printf(i + "  ");
            }
            System.out.println();
        }
        System.out.println("--------------");
        // 2.将二位数组转换成稀疏数组
        // 获取二维数组有效个数sum，创建稀疏数组int[][] sparseArray = new int[sum+1][3];
        int sum = 0;
        for (int[] array2 : array1) {
            for (int i : array2) {
                if (i != 0) {
                    sum++;
                }
            }
        }
        int[][] sparseArray = new int[sum + 1][3];
        // 稀疏数组第一行为 sparseArray[0][0]= 10,sparseArray[0][1]= 10,sparseArray[0][2]= sum;
        sparseArray[0][0] = checkerboard_row;
        sparseArray[0][1] = checkerboard_column;
        sparseArray[0][2] = sum;
        // 遍历二维数组，将二维数组中的有效值转换到稀疏数组。
        int count = 1;
        for (int i = 0; i < checkerboard_row; i++) {
            for (int j = 0; j < checkerboard_column; j++) {
                if (array1[i][j] != 0) {
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = array1[i][j];
                    count++;
                }
            }
        }
        for (int[] sparseArray1 : sparseArray) {
            for (int i : sparseArray1) {
                System.out.printf(i + "  ");
            }
            System.out.println();
        }
        // 3.将稀疏数组输入文件保存。

        System.out.println("--------------");

        // 续盘：
        // 1.从文件中读取数据转化为稀疏数组。

        // 2.将稀疏数组转换为二维数组：
        // 用稀疏数组第一行数据创建二维数组 int[][] array = new int[sparseArray[0][0]][sparseArray[0][1]];
        int[][] array3 = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 遍历稀疏数组将有效值转换到二维数组。
        for (int i = 1; i < sparseArray.length; i++) {
            array3[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        for (int[] array2 : array3) {
            for (int i : array2) {
                System.out.printf(i + "  ");
            }
            System.out.println();
        }
    }
}

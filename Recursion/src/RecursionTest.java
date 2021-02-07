public class RecursionTest {
    public static void main(String[] args) {
        print(5);
        System.out.println("---------------------");
        System.out.println(factorial(5));
    }

    // 打印问题
    public static void print(int n) {
        if (n > 1) {
            print(n - 1);
        }
        System.out.println(n);
    }

    // 阶乘问题
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }
}

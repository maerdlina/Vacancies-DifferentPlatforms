import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // Необходимая сумма чисел
        int k = scan.nextInt(); // Сколько цветов ручек у него есть

        int result = calc(n, k);


        System.out.println((result % (int)Math.pow(10, 9) + 7));
    }

    private static int calc(int n, int k) {
        int[][] ans = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            ans[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                ans[i][j] = (ans[i - 1][j - 1] + ans[i - 1][j]) % ((int)Math.pow(10, 9) + 7);
            }
        }

        return ans[n][k];
    }
}

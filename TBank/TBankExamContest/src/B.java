import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt(); //  Количество наборов входных данных

        while (t-- > 0) {
            int n = scan.nextInt(); // Размер массива
            int[] a = new int[n]; // Массив чисел

            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt();
            }

            Arrays.sort(a);

            long moves = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] > i + 1) {
                    moves = -1;
                    break;
                }
                moves += (i + 1) - a[i];
            }

            if (moves == -1) {
                System.out.println("Second");
            } else {
                System.out.println(moves % 2 == 1 ? "First" : "Second");
            }
        }
    }
}
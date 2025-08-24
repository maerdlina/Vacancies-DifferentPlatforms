package spring;

import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int x = scan.nextInt();
        int y = scan.nextInt();

        scan.nextLine();

        int totalRows = n * x;
        int condition = (x * y + 1) / 2;

        int answer = 0;

        char[][] matrix = new char[totalRows][];
        for (int i = 0; i < totalRows; i++) {
            matrix[i] = scan.nextLine().toLowerCase().toCharArray();
        }

        for (int floor = 0; floor < n; floor++) {
            for (int apart = 0; apart < m; apart++) {
                int litCount = 0;

                int startRow = floor * x;
                int startCol = apart * y;

                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        if (matrix[startRow + i][startCol + j] == 'x') {
                            litCount++;
                        }
                    }
                }

                if (litCount >= condition) {
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}
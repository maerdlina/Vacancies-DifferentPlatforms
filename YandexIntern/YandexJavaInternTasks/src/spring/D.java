package spring;

import java.util.*;

public class D {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int d = scan.nextInt();
        scan.nextLine();

        char[][] matrix = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'X' || matrix[i][j] == 'x') {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE - 1000000;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i-1][j] + 1);
                }
                if (j > 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j-1] + 1);
                }
            }
        }

        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (i < n-1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i+1][j] + 1);
                }
                if (j < m-1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j+1] + 1);
                }
            }
        }

        boolean[][] b = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                b[i][j] = dist[i][j] >= d;
            }
        }

        int[][] arr3 = new int[n][m];
        int maxSide = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!b[i][j]) {
                    arr3[i][j] = 0;
                } else {
                    if (i == 0 || j == 0) {
                        arr3[i][j] = 1;
                    } else {
                        arr3[i][j] = Math.min(Math.min(arr3[i-1][j], arr3[i][j-1]), arr3[i-1][j-1]) + 1;
                    }
                    if (arr3[i][j] > maxSide) {
                        maxSide = arr3[i][j];
                    }
                }
            }
        }

        System.out.println(maxSide);
    }
}
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        long[] l = new long[n];
        long[] r = new long[n];
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            l[i] = scan.nextLong();
            r[i] = scan.nextLong();
            a[i] = scan.nextLong();
        }

        long result = calcMax(n, l, r, a);
        System.out.println(result);

        scan.close();
    }

    private static long calcMax(int n, long[] l, long[] r, long[] a) {
        if (n == 1) {
            return a[0];
        }

        long maxTotal = 0;

        for (int source = 0; source < n; source++) {
            long[] fill = new long[n];
            fill[source] = a[source];

            long flow = a[source];
            for (int i = source - 1; i >= 0; i--) {
                long channelLimit = l[i + 1];
                long availableSpace = a[i] - fill[i];

                long transfer = Math.min(flow, channelLimit);
                transfer = Math.min(transfer, availableSpace);

                if (transfer <= 0) {
                    break;
                }

                fill[i] += transfer;
                flow = transfer;
            }

            flow = a[source];
            for (int i = source + 1; i < n; i++) {
                long channelLimit = r[i - 1];
                long availableSpace = a[i] - fill[i];

                long transfer = Math.min(flow, channelLimit);
                transfer = Math.min(transfer, availableSpace);

                if (transfer <= 0) {
                    break;
                }

                fill[i] += transfer;
                flow = transfer;
            }

            long totalFill = 0;
            for (int i = 0; i < n; i++) {
                totalFill += fill[i];
            }

            maxTotal = Math.max(maxTotal, totalFill);
        }

        return maxTotal;
    }
}
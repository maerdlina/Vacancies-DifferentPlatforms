package spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class B {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        List<Long> q = new ArrayList<>();
        for(int i = 0; i < n; i++)
            q.add(scan.nextLong());

        List<Long> c = new ArrayList<>();
        for(int i = 0; i < n; i++)
            c.add(scan.nextLong());

        long a = scan.nextLong();
        long b = scan.nextLong();

        long sum = 0;

        if (a == b) {
            for (long num : q) {
                sum += num;
            }
            sum *= a;
        } else {
            for (int i = 0; i < n; i++) {
                long num = c.get(i) * (b - a);
                long value = a + num / 255;

                if (num % 255 >= 128) {
                    value++;
                }

                sum += q.get(i) * value;
            }
        }

        System.out.println(sum);
    }
}
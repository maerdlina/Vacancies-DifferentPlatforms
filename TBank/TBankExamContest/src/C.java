import java.util.*;

public class C {
    static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt(); // Размер массива
        int[] a = new int[n]; // Элементы массива

        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
        }

        Arrays.sort(a);

        Map<Integer, Integer> counter = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            int num = a[i];
            if (counter.containsKey(num)) {
                int count = counter.get(num);
                counter.put(num, count + 1);
            } else {
                counter.put(num, 1);
            }
        }

        List<Integer> uniq = new ArrayList<>(counter.keySet());

        long result = 1;
        for (int num : uniq) {
            int count = counter.get(num);
            result = (result * (count + 1)) % MOD;
        }

        result = (result - 1 + MOD) % MOD;
        System.out.println(result);
    }
}
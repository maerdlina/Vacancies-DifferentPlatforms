import java.util.*;

public class A {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();

        List<Integer> list = new ArrayList<>();

        while(x != 0){
            list.add(x % 10);
            x/=10;
        }
        Collections.sort(list);

        if(list.get(0) == 0){
            list.set(0, list.get(1));
            list.set(1, 0);
        }

        int answ = list.get(0) * 1000 + list.get(1) * 100 + list.get(2) * 10 + list.get(3);

        System.out.println(answ);
    }
}

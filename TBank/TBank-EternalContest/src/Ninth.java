import java.util.ArrayList;
import java.util.List;

public class Ninth {
    public static int searchIndex(int value, List<Integer> list){
        for(int i = 0; i < list.size(); i++)
            if(list.get(i) == value)
                return i;
        return -1;
    }

    public static int maxValue(int index, List<Integer> list){
        int max = 0;

        for(int i = index; i < list.size(); i++){
            if(list.get(i) > max) max = list.get(i);
        }

        return max;
    }

    public static int minSum(List<Integer> list){
        int sum = 0;
        int k = 0;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) >= 100 && (k > 0)) {
                if(k < 1){
                    k++;
                    sum += list.get(i);
                    System.out.println(">=100  && k < 1 " + list);
                } else{
                    int maxValueInt = maxValue(i, list);
                    int indexMaxValue = searchIndex(maxValueInt, list);
                    sum += list.get(i);
                    list.set(indexMaxValue, 0);
                    k--;
                    System.out.println(">=100  && k >= 1 " + list);
                }
            }else if(list.get(i) < 100 && k > 0) {
                int maxValueInt = maxValue(i, list);
                int indexMaxValue = searchIndex(maxValueInt, list);
                sum += list.get(i);
                list.set(indexMaxValue, 0);
                k--;
                System.out.println(">=100  && k >= 1 " + list);
            }
        }

        return sum;
    }

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>() {{
            add(35);
            add(40);
            add(101);
            add(59);
            add(63);
        }};
        System.out.println(minSum(list));
    }
}

package spring;

import java.util.*;
import java.util.stream.Collectors;

public class C {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt(); // Кол-во действий
        int l = scan.nextInt(); // Длина круга
        int s = scan.nextInt(); // Скорость
        scan.nextLine();

        Map<Integer, Long> lastTime = new HashMap<>();
        Map<Integer, Integer> taxiPos = new HashMap<>();
        List<String> results = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = scan.nextLine().split(" ");
            String eventType = parts[0];

            if ("TAXI".equals(eventType)) {
                long timestamp = Long.parseLong(parts[1]);
                int taxiId = Integer.parseInt(parts[2]);
                int pos = Integer.parseInt(parts[3]);

                lastTime.put(taxiId, timestamp);
                taxiPos.put(taxiId, pos);
            } else if ("ORDER".equals(eventType)) {
                long orderTimestamp = Long.parseLong(parts[1]);
                int orderPos = Integer.parseInt(parts[3]);
                int orderTime = Integer.parseInt(parts[4]);

                Set<Integer> freeTaxis = new TreeSet<>();

                for (Integer taxiId : lastTime.keySet()) {
                    long lastTimestamp = lastTime.get(taxiId);
                    int lastTaxiPos = taxiPos.get(taxiId);

                    long timeDiff = orderTimestamp - lastTimestamp;

                    int maxDist = (int) (timeDiff * s);
                    int farthestPos = (lastTaxiPos + maxDist) % l;

                    int distStart = dist(lastTaxiPos, orderPos, l);
                    int distEnd = dist(farthestPos, orderPos, l);

                    int maxDistNeed = Math.max(distStart, distEnd);

                    int maxTimeNeed = (maxDistNeed + s - 1) / s;

                    if (maxTimeNeed <= orderTime) {
                        freeTaxis.add(taxiId);
                        if (freeTaxis.size() >= 5) break;
                    }
                }

                String result = freeTaxis.isEmpty() ? "-1" :
                        freeTaxis.stream().limit(5)
                                .map(String::valueOf).collect(Collectors.joining(" "));
                results.add(result);
            }
        }

        for (String result : results) {
            System.out.println(result);
        }
    }

    private static int dist(int fromPos, int endPos, int circleLength) {
        return (endPos - fromPos + circleLength) % circleLength;
    }
}
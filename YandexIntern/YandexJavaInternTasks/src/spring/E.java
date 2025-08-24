package spring;

import java.util.*;

public class E {
    private static final Map<Character, String> codeLetters = new HashMap<>();
    static {
        codeLetters.put('2', "ABC");
        codeLetters.put('3', "DEF");
        codeLetters.put('4', "GHI");
        codeLetters.put('5', "JKL");
        codeLetters.put('6', "MNO");
        codeLetters.put('7', "PQRS");
        codeLetters.put('8', "TUV");
        codeLetters.put('9', "WXYZ");
    }

    private static final Map<Character, String> codeLetter = new HashMap<>();
    static {
        for (Map.Entry<Character, String> entry : codeLetters.entrySet()) {
            char digit = entry.getKey();
            String letters = entry.getValue();
            for (int i = 0; i < letters.length(); i++) {
                char letter = letters.charAt(i);
                StringBuilder code = new StringBuilder();
                for (int j = 0; j <= i; j++) {
                    code.append(digit);
                }
                codeLetter.put(letter, code.toString());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());
        String[] dict = new String[n];
        for (int i = 0; i < n; i++) {
            dict[i] = scanner.nextLine();
        }

        Map<String, List<String>> encode = new HashMap<>();
        for (String word : dict) {
            String encoded = encodeWord(word);
            encode.computeIfAbsent(encoded, k -> new ArrayList<>()).add(word);
        }

        String[] dp = new String[s.length() + 1];
        int[] prev = new int[s.length() + 1];
        Arrays.fill(prev, -1);
        dp[0] = "";

        for (int i = 0; i < s.length(); i++) {
            if (dp[i] == null) continue;

            for (Map.Entry<String, List<String>> entry : encode.entrySet()) {
                String e = entry.getKey();
                int end = i + e.length();

                if (end <= s.length() && s.substring(i, end).equals(e)) {
                    String word = entry.getValue().get(0);
                    dp[end] = word;
                    prev[end] = i;
                }
            }
        }

        List<String> result = new ArrayList<>();
        int current = s.length();
        while (current > 0) {
            result.add(dp[current]);
            current = prev[current];
        }

        Collections.reverse(result);
        System.out.println(String.join(" ", result));
    }

    private static String encodeWord(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toCharArray()) {
            sb.append(codeLetter.get(c));
        }
        return sb.toString();
    }
}
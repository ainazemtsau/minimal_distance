package it.jaydevs.antoninazemtsau.service;

public class MinimalDistanceServiceImpl implements MinimalDistanceService {
    @Override
    public int minimalDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int deletion = dp[i][j - 1] + 1;
                int insertion = dp[i - 1][j] + 1;
                int substitution = dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1);
                dp[i][j] = Math.min(Math.min(deletion, insertion), substitution);
            }
        }

        int distance = dp[n][m];
        System.out.println(distance);
        int curI = n;
        int curJ = m;
        String[] curWord = word2.split("");

        System.out.println(String.join("", curWord));
        while (distance > 0 && curI > 0 && curJ > 0) {
            int deletion = dp[curI][curJ - 1];
            int insertion = dp[curI - 1][curJ];
            int substitution = dp[curI - 1][curJ - 1];
            if (substitution < distance) {
                curWord[curJ - 1] = Character.toString(word1.charAt(curI - 1));
                curI--;
                curJ--;
                distance = substitution;
                System.out.println(String.join("", curWord));
            } else if (deletion < distance) {
                curWord[curJ - 1] = "";
                curJ--;
                distance = deletion;
                System.out.println(String.join("", curWord));
            } else if (insertion < distance) {
                curWord = insertIntoArray(curWord, curJ, Character.toString(word1.charAt(curI - 1)));
                curI--;
                distance = insertion;
                System.out.println(String.join("", curWord));
            } else {
                curI--;
                curJ--;
            }
        }
    return dp[n][m];
    }
    private String[] insertIntoArray(String[] arr, int index, String newItem) {
        String[] result = new String[arr.length + 1];
        if (index >= 0) System.arraycopy(arr, 0, result, 0, index);
        result[index] = newItem;
        if (result.length - (index + 1) >= 0)
            System.arraycopy(arr, index + 1 - 1, result, index + 1, result.length - (index + 1));
        return result;
    }
}

import java.util.*;

public class Algorithms {

    // 🔹 1. 0/1 Knapsack (Dynamic Programming)
    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    // 🔹 2. Fractional Knapsack (Greedy)
    static class Item {
        int weight, value;

        Item(int w, int v) {
            weight = w;
            value = v;
        }
    }

    public static double fractionalKnapsack(int W, Item[] items) {
        Arrays.sort(items, (a, b) -> Double.compare((double)b.value / b.weight, (double)a.value / a.weight));

        double total = 0.0;

        for (Item item : items) {
            if (W >= item.weight) {
                W -= item.weight;
                total += item.value;
            } else {
                total += item.value * ((double) W / item.weight);
                break;
            }
        }
        return total;
    }

    // 🔹 3. Matrix Multiplication
    public static int[][] matrixMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int m = B[0].length;
        int p = B.length;

        int[][] result = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < p; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // 🔹 4. Longest Common Subsequence (LCS)
    public static int lcs(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    // 🔹 Main Function (Testing All)
    public static void main(String[] args) {

        // ✅ 0/1 Knapsack
        int[] wt = {1, 3, 4, 5};
        int[] val = {1, 4, 5, 7};
        int W = 7;
        System.out.println("0/1 Knapsack: " + knapsack(W, wt, val, wt.length));

        // ✅ Fractional Knapsack
        Item[] items = {
            new Item(10, 60),
            new Item(20, 100),
            new Item(30, 120)
        };
        System.out.println("Fractional Knapsack: " + fractionalKnapsack(50, items));

        // ✅ Matrix Multiplication
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        int[][] result = matrixMultiply(A, B);

        System.out.println("Matrix Multiplication Result:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }

        // ✅ LCS
        System.out.println("LCS Length: " + lcs("ABCBDAB", "BDCAB"));
    }
}

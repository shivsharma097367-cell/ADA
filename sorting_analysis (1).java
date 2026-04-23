import java.util.*;

public class SortingAnalysis {

    // 🔹 Generate Best Case (Sorted)
    public static int[] generateBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    // 🔹 Generate Worst Case (Reverse Sorted)
    public static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    // 🔹 Generate Average Case (Random)
    public static int[] generateAverageCase(int n) {
        int[] arr = generateBestCase(n);
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int j = rand.nextInt(n);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        return arr;
    }

    // 🔹 Bubble Sort
    public static int bubbleSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                steps++;

                if ((ascending && arr[j] > arr[j + 1]) ||
                    (!ascending && arr[j] < arr[j + 1])) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    steps += 3;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
        return steps;
    }

    // 🔹 Selection Sort
    public static int selectionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            for (int j = i + 1; j < n; j++) {
                steps++;

                if ((ascending && arr[j] < arr[idx]) ||
                    (!ascending && arr[j] > arr[idx])) {
                    idx = j;
                }
            }

            if (idx != i) {
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
                steps += 3;
            }
        }
        return steps;
    }

    // 🔹 Insertion Sort
    public static int insertionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            steps++;
            int j = i - 1;

            while (j >= 0) {
                steps++;

                if ((ascending && arr[j] > key) ||
                    (!ascending && arr[j] < key)) {
                    arr[j + 1] = arr[j];
                    steps++;
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
            steps++;
        }
        return steps;
    }

    // 🔹 Utility to print array
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    // 🔹 Copy array
    public static int[] copyArray(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    // 🔹 Main Analysis
    public static void main(String[] args) {

        int[] sizes = {10, 20, 30, 40};

        for (String order : new String[]{"Ascending", "Descending"}) {

            boolean ascending = order.equals("Ascending");

            for (String caseType : new String[]{"Best", "Average", "Worst"}) {

                for (int n : sizes) {

                    int[] original;

                    if (caseType.equals("Best"))
                        original = generateBestCase(n);
                    else if (caseType.equals("Worst"))
                        original = generateWorstCase(n);
                    else
                        original = generateAverageCase(n);

                    System.out.println("\nInput Size: " + n +
                            " | " + caseType + " Case | " + order);

                    // Bubble
                    int[] arr1 = copyArray(original);
                    int steps1 = bubbleSort(arr1, ascending);
                    System.out.println("Bubble Sorted: " + Arrays.toString(arr1));
                    System.out.println("Bubble Steps: " + steps1);

                    // Selection
                    int[] arr2 = copyArray(original);
                    int steps2 = selectionSort(arr2, ascending);
                    System.out.println("Selection Sorted: " + Arrays.toString(arr2));
                    System.out.println("Selection Steps: " + steps2);

                    // Insertion
                    int[] arr3 = copyArray(original);
                    int steps3 = insertionSort(arr3, ascending);
                    System.out.println("Insertion Sorted: " + Arrays.toString(arr3));
                    System.out.println("Insertion Steps: " + steps3);
                }
            }
        }
    }
}

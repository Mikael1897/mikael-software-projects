package ec.edu.espe.model;

public class BubbleSort implements SortingStrategy {
    @Override
    public int[] sort(int[] input) {
        int[] arr = input.clone();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }
}
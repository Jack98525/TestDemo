package package2;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 1, 7, 0, 10, 2};
        quickSort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partition = partition(arr, left, right);
            quickSort(arr, left, partition - 1);
            quickSort(arr, partition + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int smallIndex = left - 1;
        int temp;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivot) {
                smallIndex++;
                temp = arr[smallIndex];
                arr[smallIndex] = arr[i];
                arr[i] = temp;
            }
        }

        arr[right] = arr[smallIndex + 1];
        arr[smallIndex + 1] = pivot;
        return smallIndex + 1;
    }

}

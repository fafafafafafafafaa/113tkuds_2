import java.util.Arrays;

public class AdvancedArrayRecursion {

    // ===== 1. 遞迴實作快速排序 =====
    public static void quickSort(int[] arr) {
        quickSortRec(arr, 0, arr.length - 1);
    }

    private static void quickSortRec(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivotIndex = partition(arr, left, right);
        quickSortRec(arr, left, pivotIndex - 1);
        quickSortRec(arr, pivotIndex + 1, right);
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ===== 2. 遞迴合併兩個已排序陣列 =====
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        return mergeRec(arr1, arr2, arr1.length, arr2.length);
    }

    private static int[] mergeRec(int[] arr1, int[] arr2, int n, int m) {
        if (n == 0) return Arrays.copyOf(arr2, m);
        if (m == 0) return Arrays.copyOf(arr1, n);

        if (arr1[0] < arr2[0]) {
            int[] merged = mergeRec(Arrays.copyOfRange(arr1, 1, n), arr2, n - 1, m);
            int[] result = new int[merged.length + 1];
            result[0] = arr1[0];
            System.arraycopy(merged, 0, result, 1, merged.length);
            return result;
        } else {
            int[] merged = mergeRec(arr1, Arrays.copyOfRange(arr2, 1, m), n, m - 1);
            int[] result = new int[merged.length + 1];
            result[0] = arr2[0];
            System.arraycopy(merged, 0, result, 1, merged.length);
            return result;
        }
    }

    // ===== 3. 遞迴尋找陣列中的第 k 小元素 =====
    public static int kthSmallest(int[] arr, int k) {
        if (k < 1 || k > arr.length) throw new IllegalArgumentException("k 超出範圍");
        return kthSmallestRec(Arrays.copyOf(arr, arr.length), 0, arr.length - 1, k - 1);
    }

    private static int kthSmallestRec(int[] arr, int left, int right, int k) {
        if (left == right) return arr[left];
        int pivotIndex = partition(arr, left, right);
        if (k == pivotIndex) {
            return arr[k];
        } else if (k < pivotIndex) {
            return kthSmallestRec(arr, left, pivotIndex - 1, k);
        } else {
            return kthSmallestRec(arr, pivotIndex + 1, right, k);
        }
    }

    // ===== 4. 遞迴檢查子序列總和是否等於目標 =====
    public static boolean hasSubsetSum(int[] arr, int target) {
        return subsetSumRec(arr, target, arr.length);
    }

    private static boolean subsetSumRec(int[] arr, int target, int n) {
        if (target == 0) return true;
        if (n == 0) return false;
        if (arr[n - 1] > target) {
            return subsetSumRec(arr, target, n - 1);
        }
        return subsetSumRec(arr, target, n - 1) || subsetSumRec(arr, target - arr[n - 1], n - 1);
    }

    // ===== 測試主程式 =====
    public static void main(String[] args) {
        // 測試快速排序
        int[] arr1 = {34, 7, 23, 32, 5, 62};
        quickSort(arr1);
        System.out.println("快速排序: " + Arrays.toString(arr1));

        // 測試合併兩個已排序陣列
        int[] arr2 = {1, 3, 5, 7};
        int[] arr3 = {2, 4, 6, 8};
        int[] merged = mergeSortedArrays(arr2, arr3);
        System.out.println("合併排序後: " + Arrays.toString(merged));

        // 測試第 k 小元素
        int[] arr4 = {12, 3, 5, 7, 19};
        int k = 3;
        System.out.println("第 " + k + " 小元素: " + kthSmallest(arr4, k));

        // 測試子序列總和
        int[] arr5 = {3, 34, 4, 12, 5, 2};
        int target = 9;
        System.out.println("是否存在子序列總和等於 " + target + "? " + hasSubsetSum(arr5, target));
    }
}

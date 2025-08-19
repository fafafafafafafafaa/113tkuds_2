import java.util.*;

public class NumberArrayProcessor {

    // 移除陣列中的重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int i = 0, j = 0, k = 0;
        int[] result = new int[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }
        while (i < arr1.length) result[k++] = arr1[i++];
        while (j < arr2.length) result[k++] = arr2[j++];
        return result;
    }

    // 找出陣列中出現頻率最高的元素
    public static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0, mostFreqNum = arr[0];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > maxFreq) {
                maxFreq = entry.getValue();
                mostFreqNum = entry.getKey();
            }
        }
        return mostFreqNum;
    }

    // 將陣列分割成兩個相等（或近似相等）的子陣列
    public static int[][] splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] firstHalf = Arrays.copyOfRange(arr, 0, mid);
        int[] secondHalf = Arrays.copyOfRange(arr, mid, arr.length);

        return new int[][]{firstHalf, secondHalf};
    }

    // 輔助：印出陣列
    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 3, 5, 2, 8, 8, 9};
        int[] sorted1 = {1, 3, 5, 7};
        int[] sorted2 = {2, 4, 6, 8};

        System.out.println("原始陣列:");
        printArray(arr);

        System.out.println("\n移除重複元素:");
        printArray(removeDuplicates(arr));

        System.out.println("\n合併兩個已排序的陣列:");
        printArray(mergeSortedArrays(sorted1, sorted2));

        System.out.println("\n出現頻率最高的元素:");
        System.out.println(mostFrequentElement(arr));

        System.out.println("\n分割陣列:");
        int[][] split = splitArray(arr);
        System.out.print("前半部: ");
        printArray(split[0]);
        System.out.print("後半部: ");
        printArray(split[1]);
    }
}

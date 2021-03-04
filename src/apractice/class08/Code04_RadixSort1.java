package apractice.class08;

import java.util.Arrays;

public class Code04_RadixSort1 {
    public static void radixSort(int[] arr) {
        int range = countRange(arr);
        int[] help = new int[arr.length];
        for(int j=1;j<=range;j++) {
            int[] sum = new int[10];
            for(int i=0;i<arr.length;i++) {
                int index = getDigit(arr[i],j);
                sum[index]++;
            }

            for(int i=1;i<sum.length;i++) {
                sum[i] = sum[i] + sum[i-1];
            }

            for(int i=arr.length-1;i>=0;i--) {
                int index = getDigit(arr[i],j);
                if(sum[index] > 0) {
                    sum[index]--;
                    help[sum[index]] = arr[i];
                }

            }

            for(int i=0;i<arr.length;i++) {
                arr[i] = help[i];
            }

        }

        // 在range位范围内，从个位开始，计算个位的值，放入对应的数组中
        // 元素在从左至右进行遍历，排序
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }

    public static int countRange(int[] arr) {
        int max = Integer.MIN_VALUE;
        int count = 1;
        int index = 10;
        for(int i=0;i<arr.length;i++) {
            max = Math.max(max,arr[i]);
        }
        while((max/index) != 0) {
            count++;
            index = index * 10;
            System.out.println(index);
        }
        return count;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
//            int[] arr1 = new int[]{20552,97434,64265 };
//            int[] arr2 = new int[]{20552,97434,64265 };
            radixSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        radixSort(arr);
        printArray(arr);

    }
}

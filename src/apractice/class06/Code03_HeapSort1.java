package apractice.class06;

import java.util.Arrays;
import java.util.PriorityQueue;

//不管大根堆，小根堆，都只保证根堆，不保证整个有序
public class Code03_HeapSort1 {
    public static void heapSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        for(int i=arr.length - 1; i >= 0;i--) {
            heapify(arr,i,arr.length );
        }

        swap(arr,0,arr.length-1);
        int size = arr.length -1;
        while (size > 0) {
            heapify(arr,0,size--);
            swap(arr,0,size);
        }

    }

    public static void heapify(int[] arr,int index, int size) {
        int left = 2*index + 1;
        while(left < size) {
            int largest = left + 1 < size && arr[left] < arr[left + 1] ? left + 1 : left;
            if(arr[index] < arr[largest]) {
                swap(arr,index,largest);
                index = largest;
                left = 2*index + 1;

            } else {
                break;
            }

        }
    }

    public static void heapInsert(int[] arr, int i) {
        while(arr[i] > arr[(i-1)/2]) {
            swap(arr,i,(i-1)/2);
           i = (i-1)/2;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(6);
        heap.add(8);
        heap.add(0);
        heap.add(2);
        heap.add(9);
        heap.add(1);

        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}

package apractice.class05;

import java.util.Stack;

// 递归需要根据返回值，进行下一步，改成非递归要用辅助栈，保存返回值
public class Code02_PartitionAndQuickSort {
    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int partition(int[] arr, int L, int R) {
        if( L > R) {
            return -1;
        }
        if(L == R) {
            return L;
        }
        int index = L ;
        int less = L - 1 ;
        while(index < R) {
            if(arr[index] <= arr[R]) {
                swap(arr,++less,index);
            }
            index ++;
        }
        swap(arr,++less,R);
        return less;
    }

    public static int[] netherlandFlag(int[] arr, int L, int R) {
        if( L > R) {
            return new int[]{-1,-1};
        }
        if(L == R) {
            return new int[]{L, R};
        }

        int index = L;
        int less = L -1;
        int more = R;
        while (index < more) {
            if(arr[index] < arr[R]) {
                swap(arr,++less,index++);
            }else if(arr[index] > arr[R]) {
                swap(arr,--more,index);
            } else {
                index ++;
            }
        }
        swap(arr,more,R);
        return new int[]{less + 1, more };
    }

    public static class Op{
        int l;
        int r;
        Op(int l,int r) {
            this.l = l;
            this.r = r;
        }
    }

    // 非递归实现快排
    public static void quickSort4(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int N = arr.length - 1;
        int random = L + (int)(Math.random()*(N));
        swap(arr,random,N);
        int[] area = netherlandFlag(arr,L,N);
        Stack<Op> stack = new Stack();
        stack.push(new Op(L,area[0] -1));
        stack.push(new Op(area[1] + 1,N));
        while(! stack.isEmpty()) {
            Op op = stack.pop();
            int l = op.l;
            int r = op.r;
            if(l < r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                int[] area1 = netherlandFlag(arr,l,r);
                stack.push(new Op(l,area1[0] -1));
                stack.push(new Op(area1[1] + 1,r));
            }
        }
    }

    public static void quickSort01 (int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int R = arr.length -1;
        process1(arr, L, R);
    }

    private static void process1(int[] arr, int l, int r) {
        if(l >= r) {
            return ;
        }
        int equalIndex = partition(arr,l,r);
        process1(arr,l,equalIndex-1);
        process1(arr,equalIndex+1,r);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process22(arr, 0, arr.length - 1);
    }

    private static void process22(int[] arr, int l, int r) {
        if(l >= r) {
            return ;
        }

        int[] area = netherlandFlag(arr,l,r);
        process22(arr,l,area[0] - 1);
        process22(arr,area[1] + 1,r);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process33(arr, 0, arr.length - 1);
    }

    private static void process33(int[] arr, int l, int r) {
        if(l >= r) {
            return ;
        }
        swap(arr,(int)(l + Math.random() *(r-l+1)),r);
        int[] area = netherlandFlag(arr,l,r);
        process33(arr,l,area[0] - 1);
        process33(arr,area[1] + 1,r);
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
        int testTime = 50000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr1 = new int[]{68,-7,30};
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            quickSort01(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            quickSort4(arr4);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3) || !isEqual(arr3, arr4)) {
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                printArray(arr4);
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }

}

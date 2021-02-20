package apractice.class04;

//递归向迭代转换，递归的终结选择作为迭代的开始
public class Code01_MergeSort1 {
    public static void mergeSort(int[] arr){
        if(arr == null || arr.length <2) {
            return;
        }
        int L = 0;
        int R = arr.length - 1;
        work(arr, L, R);
    }

    private static void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        while(p1 <= mid && p2<= R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p1 <= mid){
            help[i++] = arr[i++];
        }
        while (p2 <= R){
            help[i++] = arr[i++];
        }
        for(int j=0;j<help.length;j++) {
            arr[L+j] = help[j];
        }

    }

    private static void work(int[] arr, int L, int R) {
        int mid = L + (R-L) >> 1;
        work(arr, L,mid);
        work(arr, mid+1, R);
        merge(arr,L,mid,R);
    }
}

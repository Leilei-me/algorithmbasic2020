package apractice.class05;

public class Code01_CountOfRangeSum {
    public static int countOfRangeSum(int[] arr,int rangeL,int rangeR) {
        if(arr == null) {
            return 0;
        }
        int[] sum = new int[arr.length];
        int L = 0;
        int R = arr.length - 1;
        sum[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            sum[i] = sum[i-1] + arr[i];
        }
        return process(sum,L,R,rangeL,rangeR);
    }

    private static int process(int[] sum, int l, int r, int rangeL, int rangeR) {
        if(l == r){
            if(rangeL <= sum[l] && sum[r] <= rangeR) {
                return 1;
            } else {
                return 0;
            }
        }
        int mid = l + ((r-l) >> 1);
        return process(sum,l,mid,rangeL,rangeR)
                + process(sum,mid + 1, r,rangeL,rangeR)
                + merge(sum,l,mid,r,rangeL,rangeR);
    }

    private static int merge(int[] sum, int l, int mid, int r, int rangeL, int rangeR) {
        int windowL = l;
        int windowR = r;
        int ans = 0;
        for(int i=mid + 1; i<= r; i++) {
            while(windowL <= mid){
                if(windowL < sum[i] - rangeR) {
                    windowL++;
                }
            }
            while(windowR <= mid){
                if(windowR <= sum[i] - rangeL) {
                    windowR++;
                }
            }
        ans += windowR - windowL;
        }
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            help[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
        }
        while (p1 <= mid) {
            help[i++] = sum[p1++];
        }
        while (p2 <= r) {
            help[i++] = sum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            sum[l + i] = help[i];
        }
        return ans;
    }
}

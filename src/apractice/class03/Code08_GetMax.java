package apractice.class03;

public class Code08_GetMax {
    public int[] process(int[] arr,int L,int R){
        if(L==R){
            return arr;
        }
        int mid = L + (R-L)/2;
        process(arr,L,mid);
        process(arr,mid + 1, R);
        merge(arr,L,mid,R);
        return arr;
    }

    private void merge(int[] arr, int L, int mid, int R) {
        // merge

        // 不merge
    }
}

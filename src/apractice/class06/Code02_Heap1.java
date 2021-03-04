package apractice.class06;

public class Code02_Heap1 {
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public static void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = arr[i];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void heapInsert(int num) {
            if(isFull()) {
                return;
            }
            heap[heapSize] = num;
            int currIndex = heapSize;
            int parentIndex = (heapSize - 1) /2;
            while (currIndex > 0 && num > heap[parentIndex]) {
                    swap(heap,currIndex,parentIndex);
                    currIndex = parentIndex;
            }
            heapSize++;
        }

        public int heapify(int[] arr) {
            int index = 0;
            if(arr[2*index + 1] > arr[2*index + 2]) {
                int max = arr[2*index + 1];
                if(max > arr[index]) {
                    swap(arr, index , 2*index + 1);
                    index = 2*index + 1;
                } else {
                    swap(arr, index , 2*index + 2);
                    index = 2*index + 2;
                }
            }
            return index;

        }

        // 入随便入，出找出最大的，并向左补齐。。
        public static class RightMaxHeap {
            private int[] arr;
            private final int limit;
            private int size;

            public RightMaxHeap(int limit) {
                arr = new int[limit];
                this.limit = limit;
                size = 0;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == limit;
            }

            public void push(int value) {
                if (size == limit) {
                    throw new RuntimeException("heap is full");
                }
                arr[size++] = value;
            }

            public int pop() {
                int maxIndex = 0;
                for (int i = 1; i < size; i++) {
                    if (arr[i] > arr[maxIndex]) {
                        maxIndex = i;
                    }
                }
                int ans = arr[maxIndex];
                arr[maxIndex] = arr[--size];
                return ans;
            }

        }
    }
}

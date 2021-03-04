package apractice.class07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

// 给自己这个类用的方法，所以不用传堆了
// review
public class Code03_HeapGreater {
    public static class HeapGreater<T>{
        private ArrayList<T> heap;
        private int heapSize;
        private HashMap<T,Integer> indexMap;
        //只要继承自T的都可。
        private Comparator<? super T> comp;

        HeapGreater(Comparator<T> comp) {
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
            this.comp = comp;
        }

        public int size() {
            return heapSize;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean contains(T key) {
            return indexMap.containsKey(key);
        }

        public T pop() {
            if(isEmpty()) {
                return null;
            }
            T ans = heap.get(0);
            swap(0,heapSize-1);
            heapSize--;
            indexMap.remove(ans);
            heap.remove(heapSize);
            heapify(0);
            return ans;
        }

        public T push(T t) {
            heap.add(t);
            heapInsert(heapSize);
            heapSize++;
            return t;
        }

        // list删除一个元素会自己补齐，故删除末尾的，再做替换。
        public void remove(T obj) {
            T replace = heap.get(heapSize -1);
            int index = indexMap.get(obj);
            indexMap.remove(obj);
            heap.remove(--heapSize);
            if(replace != obj) {
                heap.set(index,replace);
                indexMap.put(replace,index);
                resign(replace);
            }
        }

        // 不是replace,而是修改了
        public T resign(T t) {
            int replace = indexMap.get(t);
            heapify(replace);
            heapInsert(replace);
            return t;
        }

        public void heapInsert(int index) {
            while(comp.compare(heap.get(index),heap.get((index-1)/2)) < 0) {
                swap(index,(index-1)/2);
            }
        }

        // 因为从0开始，左边界开始不必判断
        public void heapify(int index) {
            int left = 2*index + 1;

            while (left < heapSize) {
                int best = left + 1 < heapSize && comp.compare(heap.get(left),heap.get(left + 1))< 0? left + 1 : left;
                if(comp.compare(heap.get(index),heap.get(best)) < 0) {
                    swap(index,best);
                    index = best;
                    left = 2*index + 1;
                }
            }
        }

        public void swap(int i, int j) {
            T temp = heap.get(i);
            heap.set(i,heap.get(j));
            heap.set(j,temp);
            indexMap.put(heap.get(i),j);
            indexMap.put(heap.get(j),i);
        }
    }

    public static class Inner<E> {
        private E e;
        Inner(E e) {
            this.e = e;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(1);
        for(int i=0; i< list.size();i++) {
            System.out.println(list.get(i));
        }
    }
}

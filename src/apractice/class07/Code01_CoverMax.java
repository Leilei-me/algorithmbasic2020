package apractice.class07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
    public static int coverMax1(int[][] m) {
        Line[] lines = new Line[m.length];
        for(int i=0;i<m.length;i++) {
            lines[i] = new Line(m[i][0],m[i][1]);
        }
        Arrays.sort(lines,new LineCompator());
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<lines.length;i++) {
            while(!queue.isEmpty() && queue.peek() <= lines[i].start) {
                queue.poll();
            }
            queue.add(lines[i].end);
            max = Math.max(max, queue.size());
        }
        return max;

    }

    public static int coverMax2(int[][] m) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0;i<m.length;i++) {
            min = Math.min(min,m[i][0]);
            max = Math.max(max, m[i][1]);
        }
        int ans = 0;
        int cover = 0;
        for(double p = 0.5+min;p<max;p++) {
            for(int j=0;j<m.length;j++) {
                if(m[j][0] < p && p < m[j][1]) {
                    cover ++;
                }
            }
            ans = Math.max(cover,ans);
            cover = 0;
        }
        return ans;

    }

    public static class LineCompator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class Line{
        private int start;
        private int end;
        Line(int start,int end) {
            this.start = start;
            this.end = end;
        }
    }
    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<yuanshi.class07.Code01_CoverMax.Line> {

        @Override
        public int compare(yuanshi.class07.Code01_CoverMax.Line o1, yuanshi.class07.Code01_CoverMax.Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        yuanshi.class07.Code01_CoverMax.Line l1 = new yuanshi.class07.Code01_CoverMax.Line(4, 9);
        yuanshi.class07.Code01_CoverMax.Line l2 = new yuanshi.class07.Code01_CoverMax.Line(1, 4);
        yuanshi.class07.Code01_CoverMax.Line l3 = new yuanshi.class07.Code01_CoverMax.Line(7, 15);
        yuanshi.class07.Code01_CoverMax.Line l4 = new yuanshi.class07.Code01_CoverMax.Line(2, 4);
        yuanshi.class07.Code01_CoverMax.Line l5 = new yuanshi.class07.Code01_CoverMax.Line(4, 6);
        yuanshi.class07.Code01_CoverMax.Line l6 = new yuanshi.class07.Code01_CoverMax.Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<yuanshi.class07.Code01_CoverMax.Line> heap = new PriorityQueue<>(new yuanshi.class07.Code01_CoverMax.StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            yuanshi.class07.Code01_CoverMax.Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = coverMax1(lines);
            int ans2 = coverMax2(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}

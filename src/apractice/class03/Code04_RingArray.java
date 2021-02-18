package apractice.class03;

//空时返回什么
//这里泛型使用
public  class Code04_RingArray<T> {
    int size = 0;
    int inIndex =0;
    int outIndex =0;

    private int[] arr = new int[5];

    public void add(T value){
        if(size < 5) {
            arr[inIndex] = (int)value;
            if(inIndex == 4) {
                inIndex = 0;
            }else {
                inIndex ++;
            }
            this.size ++;
        }else {
            System.out.println("空间已满");
        }
    }

    //获得最新加入数据
    public int getFromLast(){
        if(size < 1) {
            System.out.println("没有数据");
            return -1;
        }else {
            if(inIndex-1 > 0){
                inIndex--;
                size--;
                return arr[inIndex];
            }else {
                inIndex = arr.length-1;
                size--;
                return arr[inIndex];
            }
        }
    }

    //获得最早加入数据
    public int getFromNext(){
        if(size < 1) {
            System.out.println("没有数据");
            return -1;
        }else {
            if(outIndex + 1 > arr.length){
               outIndex = 0;
                size--;
                return arr[arr.length-1];
            }else {
                outIndex++;
                size--;
                return arr[outIndex-1];
            }
        }
    }

}

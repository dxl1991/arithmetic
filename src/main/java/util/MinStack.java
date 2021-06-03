package util;

/**
 * @author dcsz
 * @date 2021/5/26 14:26
 * @version 1.0
 */
public class MinStack {
    private int[] data;
    private int top = -1;

    /** initialize your data structure here. */
    public MinStack() {
        data = new int[4];
    }

    public void push(int val) {
        if(full()){
            expand();
        }
        data[++top] = val;
    }

    public void pop() {
        if(top >= 0){
            top--;
        }
    }

    public int top() {
        if(top >= 0){
            return data[top];
        }
        return 0;
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for(int i=0;i<=top;i++){
            min = Math.min(min,data[i]);
        }
        return min;
    }

    public boolean full(){
        return top == data.length - 1;
    }

    public void expand(){
        int[] newData = new int[data.length * 2];
        System.arraycopy(data,0,newData,0,data.length);
        data = newData;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
    }
}

package util;

/**
 * @Author dengxinlong
 * @Date 2019/10/24
 * 环形队列
 */
public class Queue<E> {
    private int size; //长度 只能放size-1个元素（因为要留一个位置判断队列是否满了）
    private int rear; //队尾指针
    private int front; //队首指针
    private Object[] data;

    public Queue(int size){
        this.size = size;
        data = new Object[size];
    }

    public boolean push(E element){
        if(full()){
            return false;
        }
        rear = (rear + 1) % size;
        data[rear] = element;
        return true;
    }

    public E pop(){
        if(empty()){
            return null;
        }
        front = (front + 1) % size;
        return (E)data[front];
    }

    public boolean full(){
        return (rear + 1) % size == front;
    }
    public boolean empty(){
        return rear == front;
    }

    public static void main(String[] args){
        Queue<String> queue = new Queue(5);
        for(int i=0;i<5;i++){
            queue.push(i+"");
        }
        for(int i=0;i<5;i++){
            System.out.println(queue.pop());
        }
    }
}

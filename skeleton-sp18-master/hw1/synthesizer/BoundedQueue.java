package synthesizer;

public interface BoundedQueue<T> {
    int capacity(); // size of buffer

    int fillCount(); // number of items currently in the buffer

    void enqueue(T x); // add item x to the end

    T dequeue();

    T peek();

    default boolean isEmpty(){
        return fillCount()==0;
    }

    default boolean isFull(){
        return fillCount()==capacity();
    }

    //void clearFillCount();
}

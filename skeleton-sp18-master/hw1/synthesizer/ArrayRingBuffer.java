package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package

import java.util.Arrays;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<Double>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private Double[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */

//    public void clearFillCount(){
//        fillCount = 0;
//    }

    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        rb = (Double[]) new Object[capacity];
        Arrays.fill(rb,0);
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(Double x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if(isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }else {
            fillCount++;
            last = (last + 1) % capacity; // get the next pos
            this.rb[last] = x;
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public Double dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if(isEmpty()){
            throw new RuntimeException("Ring buffer underflow");
        }else{
            fillCount--;
            Double buf = this.rb[first];
            this.rb[first] = 0.0;
            first = (first+1)%capacity;
            return buf;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return this.rb[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}

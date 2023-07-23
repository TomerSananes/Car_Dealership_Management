public class ArrayQueue <T> implements Queue<T> {
    private T[] queue;
    private int size;

    /**
     * constructor ArrayQueue that create array by the variable capacity
     * and put 0 in the size parameter.
     *
     * @param capacity the number that create the array length.
     */
    ArrayQueue (int capacity){
        queue = (T[]) new Object[capacity];
        size = 0;
    }

    /**
     * Adds an element to the back of the queue.
     *
     * @param element the element to add
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean enqueue(T element){
        if(size == queue.length){
            return false;
        }
        queue[size] = element;
        size++;
        return true;
    }

    /**
     * Removes and returns the element at the front of the queue.
     * If the queue is empty, returns null.
     *
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public T dequeue(){
        if(size == 0){
            return null;
        }
        T element = queue[0];
        for(int i = 1; i<size; i++){
            queue[i-1] = queue[i];
        }
        queue[size-1] = null;
        size--;
        return element;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * If the queue is empty, returns null.
     *
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public T peek(){
        if(size == 0){
            return null;
        }
        return queue[0];
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Returns true if the queue is empty, false otherwise.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty(){
        if(size == 0 && queue.length>0){
            return true;
        }
        return false;
    }

    /**
     * Returns true if the queue is full, false otherwise.
     *
     * @return true if the queue is full, false otherwise
     */
    @Override
    public boolean isFull(){
        if(size == queue.length){
            return true;
        }
        return false;
    }
}

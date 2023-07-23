
public interface Queue<T> {

    /**
     * Adds an element to the back of the queue.
     */
    boolean enqueue(T element);

    /**
     * Removes and returns the element at the front of the queue.
     * If the queue is empty, returns null.
     */
    T dequeue();

    /**
     * Returns the element at the front of the queue without removing it.
     * If the queue is empty, returns null.
     */
    T peek();

    /**
     * Returns the number of elements in the queue.
     */
    int size();

    /**
     * Returns true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns true if the queue is full, false otherwise.
     */
    boolean isFull();
}
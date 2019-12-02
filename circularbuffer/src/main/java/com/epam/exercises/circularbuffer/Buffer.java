package com.epam.exercises.circularbuffer;

import java.util.Comparator;
import java.util.List;

public interface Buffer<T> {

    /**
     * Implement a simple program (writing a real unit test is not expected)
     * to demonstrate and test each method of the circular buffer.
     */
    void put(T t);

    /**
     * This method inserts t into the buffer at the position of the head.
     * After the insertion, the head points to the next index in the buffer.
     * The put method throws RUNTIME exception if the buffer is full.
     * The buffer is full when the head and the tail points to the same index, but the buffer is not empty.
     */
    T get();

    /**
     * This method gets the value at the tail of the buffer.
     * After that, the tail points to the next index in the buffer.
     * The get method throws RUNTIME exception if the buffer is empty.
     * The buffer is empty if the head and the tail points to the same index, and the buffer is not full.
     *
     * @return: buffer transformed to array Object
     */
    Object[] toObjectArray();

    /**
     * This method returns the buffer as an Object array.
     * The size of the array should be equal to the current number
     * of actual elements in the buffer. The first element should be the tail.
     */
    T[] toArray();

    /**
     * This method returns the buffer as a type T array.
     * The size of the array should be equal to the current number
     * of actual elements in the buffer. The first element should be the tail.
     */
    List<T> asList();

    /**
     * This method returns the buffer as a list with type T.
     * The size of the list should be equal to the current number
     * of actual elements in the buffer. The first element should be the tail.
     *
     * @param toAdd collection will be added
     */
    void addAll(List<? extends T> toAdd);

    /**
     * This method adds all elements from a given list
     * (which can be type T and T's subclasses) to the buffer.
     * It throws an exception if there is not
     * enough free space in the buffer to add all elements.
     *
     * @param comparator Comparator
     */
    void sort(Comparator<? super T> comparator);

    /**
     * This method sorts the buffer with the given comparator. As the order of the elements change in the buffer, the positions of the head and the tail should be maintained as well.
     * This method returns true if the buffer is empty, false otherwise.
     */
    boolean isEmpty();

}

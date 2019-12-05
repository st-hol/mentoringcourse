package com.epam.exercises.circularbuffer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.epam.exercises.circularbuffer.exception.BufferIllegalStateException;

public class CircularBuffer<T> implements Buffer<T> {

    private static final String BUFFER_IS_FULL = "Buffer is full.";
    private static final String BUFFER_IS_EMPTY = "Buffer is empty.";

    private int readPosition;
    private int writePosition;
    private T[] buffer;

    @SuppressWarnings("unchecked")
    public CircularBuffer(int bufferLimit) {
        this.buffer = (T[]) new Object[bufferLimit];
    }

    @Override
    public void put(T t) {
        if (!isEmpty() && writePosition == readPosition) {
            throw new BufferIllegalStateException(BUFFER_IS_FULL);
        } else {
            buffer[writePosition] = t;
            shiftWritePosition();
        }
    }

    @Override
    public T get() {
        if (isEmpty() && writePosition == readPosition) {
            throw new BufferIllegalStateException(BUFFER_IS_EMPTY);
        } else {
            T obtainedElement = buffer[readPosition];
            buffer[readPosition] = null;
            shiftReadPosition();
            return obtainedElement;
        }
    }

    @Override
    public Object[] toObjectArray() {
        return Arrays.stream(buffer).filter(Objects::nonNull).toArray(Object[]::new);
    }

    @Override
    public boolean isEmpty() {
        return Arrays.stream(buffer).allMatch(Objects::isNull);
    }

    /**
     * https://stackoverflow.com/questions/18581002/how-to-create-a-generic-array
     *
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return asList().toArray(
                (T[]) Array.newInstance(Arrays.stream(buffer)
                        .findAny()
                        .orElseThrow(() -> new BufferIllegalStateException(BUFFER_IS_EMPTY))
                        .getClass(), buffer.length));
//        return asList().toArray(((T[])new Object[buffer.length]));
    }

    @Override
    public List<T> asList() {
        return Arrays.stream(buffer).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void addAll(List<? extends T> toAdd) {
//        for (T element : toAdd){
//            this.put(element);
//        }
        toAdd.forEach(this::put);
    }

    @Override
    public void sort(Comparator<? super T> comparator) {
        List<T> elements = asList();
        elements.sort(comparator);
        clear();
        elements.forEach(this::put);
    }

    private void clear() {
        while (readPosition != writePosition) {
            get();
        }
    }

    private void shiftReadPosition() {
        readPosition = incrementPosition(readPosition);
    }

    private void shiftWritePosition() {
        writePosition = incrementPosition(writePosition);
    }

    /**
     * util
     *
     * @param currentIndex
     * @return
     */
    private int incrementPosition(int currentIndex) {
        return (currentIndex + 1) % buffer.length;
    }
}

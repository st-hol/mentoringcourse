package com.epam.exercises.circularbuffer;

import java.util.Comparator;
import java.util.List;

public class  CircularBuffer <T> implements Buffer <T> {

    private int bufferLimit;


    public CircularBuffer(int bufferLimit) {
        this.bufferLimit = bufferLimit;
    }

    @Override
    public void put(T t) {

    }

    @Override
    public T get() {
        return null;
    }

    @Override
    public Object[] toObjectArray() {
        return new Object[0];
    }

    @Override
    public T[] toArray() {
        return null;
    }

    @Override
    public List<T> asList() {
        return null;
    }

    @Override
    public void addAll(List<? extends T> toAdd) {

    }

    @Override
    public void sort(Comparator<? super T> comparator) {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

package com.epam.exercises.circularbuffer;

import com.epam.exercises.circularbuffer.exception.BufferIllegalStateException;
import com.google.common.collect.Ordering;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CircularBufferTest {

    private static final int LIMIT = 7;

    private CircularBuffer<Integer> circularBuffer;

    @BeforeEach
    public void init() {
        circularBuffer = new CircularBuffer<>(LIMIT);
    }

    @AfterEach
    public void destroy() {
        circularBuffer = null;
    }

    @Test
    public void givenBufferIsFull_whenPut_thenThrow() {
        fillBufferToLimitWithRandom();
        assertThrows(BufferIllegalStateException.class,
                () -> circularBuffer.put(123));
    }

    @Test
    public void givenFilledBuffer_whenGet_thenReturnOldest() {
        fillBufferToLimitWithValues(1, 2, 3, 4, 5, 6, 7);
        assertEquals(1, circularBuffer.get());
    }

    @Test
    public void givenEmptyBuffer_whenGet_thenThrow() {
        assertThrows(BufferIllegalStateException.class,
                () -> circularBuffer.get());
    }

    @Test
    public void givenNotFullyFilledBuffer_whenToObjectArray_thenReturnObjectArray() {
        circularBuffer.put(1);
        circularBuffer.put(2);
        assertEquals(2, circularBuffer.toObjectArray().length);
    }

    @Test
    public void givenValues_whenToArray_thenReturnArrayOfType() {
        fillBufferToLimitWithValues(1, 2, 3, 4, 5, 6, 7);

        Integer[] returnArray = circularBuffer.toArray();

        assertEquals(1, returnArray[0]);
        assertEquals(2, returnArray[1]);
        assertEquals(3, returnArray[2]);
        //..
        assertEquals(7, returnArray[6]);
    }

    @Test
    public void givenValues_whenAsList_thenReturnList() {
        fillBufferToLimitWithValues(1, 2, 3, 4, 5, 6, 7);

        List<Integer> returnList = circularBuffer.asList();

        assertEquals(1, returnList.get(0));
        assertEquals(2, returnList.get(1));
        assertEquals(3, returnList.get(2));
        //..
        assertEquals(7, returnList.get(6));
    }

    @Test
    public void givenList_whenAddAll_thenInsertAll() {
        List<Integer> integers = new ArrayList<>();
        fillBufferToLimitWithValues(1, 2, 3, 4, 5, 6, 7);

        circularBuffer.addAll(integers);

        assertEquals(1, circularBuffer.get());
        assertEquals(2, circularBuffer.get());
        assertEquals(3, circularBuffer.get());
    }

    @Test
    public void givenListGreaterThanBufferLimit_whenAddAll_thenThrow() {
        assertThrows(BufferIllegalStateException.class,
                () -> {
                    List<Integer> integers = new ArrayList<>();
                    fillBufferToLimitWithValues(1, 2, 3, 4, 5, 6, 7, 8);

                    circularBuffer.addAll(integers);

                    assertEquals(1, circularBuffer.get());
                    assertEquals(2, circularBuffer.get());
                    assertEquals(3, circularBuffer.get());
                });
    }

    @Test
    public void givenEmptyList_whenCheckIsEmpty_thenTrue() {
        assertTrue(circularBuffer.isEmpty());
    }

    @Test
    public void givenNotEmptyList_whenCheckIsEmpty_thenTrue() {
        circularBuffer.put(1);
        assertFalse(circularBuffer.isEmpty());
    }

    @Test
    public void givenNaturalComparator_whenSort_thenIsOrdered() {
        Comparator<Integer> comparator = Integer::compareTo;
        fillBufferToLimitWithValues(1, 2, 3);
        circularBuffer.sort(comparator);
        assertTrue(Ordering.natural().isOrdered(circularBuffer.asList()));
    }

    private void fillBufferToLimitWithRandom() {
        Random random = new Random();
        for (int i = 0; i < LIMIT; i++) {
            circularBuffer.put(random.nextInt());
        }
    }

    private void fillBufferToLimitWithValues(Integer... args) {
        for (Integer integer : args) {
            circularBuffer.put(integer);
        }
    }
}
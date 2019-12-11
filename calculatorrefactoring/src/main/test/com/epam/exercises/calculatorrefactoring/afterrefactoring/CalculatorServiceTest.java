package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorServiceTest {

    private CalculatorService calculatorService = new CalculatorService();

    @Test
    public void givenPositiveNumbers_whenCalc_thenReturnResult() {
        String[] operations = {" ", "+", "-"};
        int[] numbers = {5, 2, 1};
        int result = calculatorService.calculate(operations, numbers);
        assertEquals( 6, result);
    }

    @Test
    public void givenNegativeNumbers_whenCalc_thenReturnResult() {
        String[] operations = {" ", "+", "-"};
        int[] numbers = {-5, -2, -1};
        int result = calculatorService.calculate(operations, numbers);
        assertEquals( -6, result);
    }

    @Test
    public void givenAllDifferPriorityOperations_whenCalc_thenReturnResult() {
        String[] operations = {" ", "+", "/", "*", "-"};
        int[] numbers = {2, 6, 3, 3, 1};
        int result = calculatorService.calculate(operations, numbers);
        assertEquals(7, result);
    }
}
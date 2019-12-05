package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operations.DIVIDE_OPERATION;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operations.MINUS_OPERATION;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operations.MULTIPLY_OPERATION;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operations.PLUS_OPERATION;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class CalculatorService {

    private static final Set<String> HIGHER_PRIORITY_OPERATIONS = ImmutableSet.of(
            MULTIPLY_OPERATION, DIVIDE_OPERATION);
    private static final Set<String> LOWER_PRIORITY_OPERATIONS = ImmutableSet.of(
            PLUS_OPERATION, MINUS_OPERATION);

    private static final Map<String, MathOperation> calculatorActions =
            ImmutableMap.of(
                    MULTIPLY_OPERATION, (x, y) -> x * y,
                    DIVIDE_OPERATION, Integer::divideUnsigned,
                    PLUS_OPERATION, Integer::sum,
                    MINUS_OPERATION, (x, y) -> x - y);

    public int calculate(String[] operations, int[] numbers) {
        applyHigherPriorityOperations(operations, numbers);
        applyLowerPriorityOperations(operations, numbers);
        return numbers[0];
    }

    private void applyHigherPriorityOperations(String[] operations, int[] numbers) {
        applyOperations(operations, numbers, HIGHER_PRIORITY_OPERATIONS);
    }

    private void applyLowerPriorityOperations(String[] operations, int[] numbers) {
        applyOperations(operations, numbers, LOWER_PRIORITY_OPERATIONS);
    }

    private void applyOperations(String[] operations, int[] numbers, Set<String> priorityOperations) {
        int numberOfOperations = operations.length;
        int index = 1;
        while (index < numberOfOperations) {
            String operation = operations[index];
            if (priorityOperations.contains(operation)) {
                performSingleOperation(numbers, index, operation);
                shiftElements(index, numberOfOperations, operations, numbers);
                numberOfOperations--;
            } else {
                index++;
            }
        }
    }

    private void performSingleOperation(int[] numbers, int index, String operator) {
        numbers[index - 1] = calculatorActions.get(operator)
                .calculate(numbers[index - 1], numbers[index]);
    }

    private void shiftElements(int startIndex, int length, String[] operations, int[] numbers) {
        for (int j = startIndex; j < length - 1; j++) {
            numbers[j] = numbers[j + 1];
            operations[j] = operations[j + 1];
            operations[j + 1] = null;
        }
    }
}

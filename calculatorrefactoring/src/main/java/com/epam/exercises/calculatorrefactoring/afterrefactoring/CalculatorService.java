package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operation.DIVIDE;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operation.MINUS;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operation.MULTIPLY;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Operation.PLUS;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.RESULT_POSITION;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class CalculatorService {

    private static final Set<String> HIGHER_PRIORITY_OPERATIONS = ImmutableSet.of(
            MULTIPLY, DIVIDE);
    private static final Set<String> LOWER_PRIORITY_OPERATIONS = ImmutableSet.of(
            PLUS, MINUS);

    private static final Map<String, MathOperation> calculatorActions =
            ImmutableMap.of(
                    MULTIPLY, (x, y) -> x * y,
                    DIVIDE, Integer::divideUnsigned,
                    PLUS, Integer::sum,
                    MINUS, (x, y) -> x - y);

    public int calculate(String[] operations, int[] numbers) {
        applyHigherPriorityOperations(operations, numbers);
        applyLowerPriorityOperations(operations, numbers);
        return numbers[RESULT_POSITION];
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

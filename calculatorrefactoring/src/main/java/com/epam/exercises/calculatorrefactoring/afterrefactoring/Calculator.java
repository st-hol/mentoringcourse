package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.CalculatorUtils.convertStringArrayToIntArray;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.CalculatorUtils.extractOperands;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.CalculatorUtils.extractOperations;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.CalculatorUtils.printLine;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.CalculatorUtils.readLine;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.ENTER_EXPRESSION;

import java.io.IOException;
import java.util.Arrays;

public class Calculator {
    public static void main(String[] args) throws IOException {

        CalculatorService calculatorService = new CalculatorService();
        printLine(ENTER_EXPRESSION);
        String inputExpression = readLine();

        String[] operations = extractOperations(inputExpression);
        printLine(Arrays.toString(operations));

        String[] numbers = extractOperands(inputExpression);
        printLine(Arrays.toString(numbers));

        int[] numbersConverted = convertStringArrayToIntArray(numbers);
        printLine(Arrays.toString(numbersConverted));

        int result = calculatorService.calculate(operations, numbersConverted);
        printLine(result);
    }

}

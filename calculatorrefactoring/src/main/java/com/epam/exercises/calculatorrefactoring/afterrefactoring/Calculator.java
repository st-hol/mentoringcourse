package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.ENTER_EXPRESSION;

import java.io.IOException;
import java.util.Arrays;

public class Calculator {
    public static void main(String[] args) throws IOException {
        UtilService utilService = new UtilService();
        CalculatorService calculatorService = new CalculatorService();
        System.out.println(ENTER_EXPRESSION);
        String inputExpression = utilService.readLine();
        System.out.println(inputExpression);

        String[] operations = utilService.extractOperations(inputExpression);
        System.out.println(Arrays.toString(operations));

        String[] numbers = utilService.extractOperands(inputExpression);
        System.out.println(Arrays.toString(numbers));

        int[] integerNumbers = utilService.convertStringArrayToIntArray(numbers);
        System.out.println(Arrays.toString(integerNumbers));

        int result = calculatorService.calculate(operations, integerNumbers);
        System.out.println(result);
    }

}

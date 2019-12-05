package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Regexes.OPERANDS_REGEX;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Regexes.OPERATIONS_REGEX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CalculatorUtils {

    private CalculatorUtils() {
    }

    public static String readLine() throws IOException {
        String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
        System.out.println(line);
        return line;
    }

    public static void printLine(Object o) {
        System.out.println(o);
    }

    public static int[] convertStringArrayToIntArray(String[] numbers) {
        return Arrays.stream(numbers)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static String[] extractOperations(String line) {
        return line.split(OPERATIONS_REGEX);
    }

    public static String[] extractOperands(String line) {
        return line.split(OPERANDS_REGEX);
    }
}

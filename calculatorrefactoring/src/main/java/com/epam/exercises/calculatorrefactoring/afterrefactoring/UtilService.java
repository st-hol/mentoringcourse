package com.epam.exercises.calculatorrefactoring.afterrefactoring;

import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Regex.OPERANDS;
import static com.epam.exercises.calculatorrefactoring.afterrefactoring.Constants.Regex.OPERATIONS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UtilService {

    public String readLine() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public int[] convertStringArrayToIntArray(String[] stringNumbersArray) {
        return Arrays.stream(stringNumbersArray)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public String[] extractOperations(String line) {
        return line.split(OPERATIONS);
    }

    public String[] extractOperands(String line) {
        return line.split(OPERANDS);
    }
}

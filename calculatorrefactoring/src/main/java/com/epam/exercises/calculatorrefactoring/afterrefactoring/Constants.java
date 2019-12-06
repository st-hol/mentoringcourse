package com.epam.exercises.calculatorrefactoring.afterrefactoring;

public interface Constants {
    String ENTER_EXPRESSION = "Enter an expression: ";

    interface Operations {
        String MULTIPLY_OPERATION = "*";
        String DIVIDE_OPERATION = "/";
        String PLUS_OPERATION = "+";
        String MINUS_OPERATION = "-";
    }

    interface Regexes {
        String OPERATIONS_REGEX = "[0-9]+";
        String OPERANDS_REGEX = "[\\+\\-\\*\\/]";
    }
}

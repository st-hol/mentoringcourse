package com.epam.exercises.calculatorrefactoring.afterrefactoring;

@FunctionalInterface
public interface MathOperation {
    int calculate(int number1, int number2);
}

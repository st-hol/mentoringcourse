package com.epam.exercises.hashcracker.parallelsolution;

import static com.epam.exercises.hashcracker.parallelsolution.Constants.ALL_POSSIBLE_CHARACTERS;
import static com.epam.exercises.hashcracker.parallelsolution.Constants.CURRENT_RESULT;
import static com.epam.exercises.hashcracker.parallelsolution.Constants.EQUALS_SIGN;
import static com.epam.exercises.hashcracker.parallelsolution.Constants.WORKING_THREAD;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HashCrackerService {

    private static final Logger LOG = LoggerFactory.getLogger(HashCrackerService.class);


    public Pair<String, String> crack(int[] numberArray, int max) {
        String generatedMD5;
        StringBuilder currentString;

        numberArray[0]++;
        for (int i = 0; i < numberArray.length; i++) {
            if (numberArray[i] >= max) {
                numberArray[i + 1]++;
                numberArray[i] = 0;
            }
        }

        currentString = new StringBuilder();
        for (int i : numberArray) {
            if (!(i == -1)) {
                currentString.append(ALL_POSSIBLE_CHARACTERS.charAt(i));
            }
        }

        generatedMD5 = hashText(currentString.toString());
        LOG.info(WORKING_THREAD + Thread.currentThread().getName());
        LOG.info(CURRENT_RESULT + currentString + EQUALS_SIGN + generatedMD5);

        return new ImmutablePair<>(generatedMD5, currentString.toString());
    }

    private String hashText(String text) {
        HashCalculator calculator = new HashCalculator();
        return calculator.hash(text);
    }

}

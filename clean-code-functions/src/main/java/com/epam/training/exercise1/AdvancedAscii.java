package com.epam.training.exercise1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


/**
 * FIXED:
 * function complexity
 * code duplications
 * magic numbers
 */
public class AdvancedAscii {

    private static final String FILE_NAME = "pair_hiking.png";
    private static final char[] CHARS_BY_DARKNESS = {'#', '@', 'X', 'L', 'I', ':', '.', ' '};
    private static final int HEIGHT_DIVIDER = 60;
    private static final int WIDTH_DIVIDER = 200;
    private static final int BYTE_TYPE_LIMIT = 255;

    public static void main(String[] args) {
        AdvancedAscii advancedAscii = new AdvancedAscii();
        List<Character> imageToCharResult = advancedAscii.transformImageToChars();
        advancedAscii.output(imageToCharResult);
    }

    private void output(List<Character> imageToCharResult) {
        for (Character character : imageToCharResult) {
            System.out.print(character);
        }
    }

    private List<Character> transformImageToChars() {
        List<Character> imageToCharResult = new ArrayList<>();
        Image image = new Image(FILE_NAME);
        int max = 0;
        int min = 3 * BYTE_TYPE_LIMIT;
        int stepY = image.getHeight() / HEIGHT_DIVIDER;
        int stepX = image.getWidth() / WIDTH_DIVIDER;
        Pair<Integer, Integer> limits = findMaxAndMinLimits(image, stepY, stepX, max, min);
        max = limits.getRight();
        min = limits.getLeft();
        extractCharacters(image, stepY, stepX, max, min, imageToCharResult);
        return imageToCharResult;
    }

    private Pair<Integer, Integer> findMaxAndMinLimits(Image image, int stepY, int stepX, int max, int min) {
        for (int y = 0; y < image.getHeight(); y += stepY) {
            for (int x = 0; x < image.getWidth(); x += stepX) {
                int sum = processImageCalculations(image, stepY, stepX, y, x);
                if (max < sum) {
                    max = sum;
                }
                if (min > sum) {
                    min = sum;
                }
            }
        }
        return new ImmutablePair<>(min, max);
    }

    private void extractCharacters(Image image, int stepY, int stepX, int max, int min,
                                   List<Character> imageToCharResult) {
        for (int y = 0; y < image.getHeight() - stepY; y += stepY) {
            for (int x = 0; x < image.getWidth() - stepX; x += stepX) {
                int sum = processImageCalculations(image, stepY, stepX, y, x);
                int index = (sum - min) * CHARS_BY_DARKNESS.length / (max - min + 1);
                imageToCharResult.add(CHARS_BY_DARKNESS[index]);
            }
            imageToCharResult.add('\n');
        }
    }

    private int processImageCalculations(Image image, int stepY, int stepX, int y, int x) {
        int sum = 0;
        Coordinate currentCoordinate;
        for (int avgY = 0; avgY < stepY; avgY++) {
            for (int avgX = 0; avgX < stepX; avgX++) {
                currentCoordinate = new Coordinate(x, y);
                sum += image.getIntensity(currentCoordinate);
            }
        }
        sum = sum / stepY / stepX;
        return sum;
    }

}

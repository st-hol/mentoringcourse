package com.epam.training.exercise1;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image {
    private static final String X_OUT_OF_RANGE = "Coordinate x out of range: 0..";
    private static final String Y_OUT_OF_RANGE = "Coordinate y out of range: 0..";
    private static final String FILE_NOT_FOUND = "File not found!";


    private static final int LAST_BYTE = 0xFF;
    private static final int BYTE = 8;
    private static final int TWO_BYTES = 16;

    private BufferedImage bufferedImage;

    Image(String fileName) {
        this.bufferedImage = loadImageFromFile(fileName);
    }

    int getHeight() {
        return bufferedImage.getHeight();
    }

    int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getIntensity(Coordinate coordinate) {
        return getRed(coordinate) + getBlue(coordinate) + getGreen(coordinate);
    }

    int getRed(Coordinate coordinate) {
        int rgbValue = getRgbValue(coordinate);
        return (rgbValue >> TWO_BYTES) & LAST_BYTE;
    }

    int getGreen(Coordinate coordinate) {
        int rgbValue = getRgbValue(coordinate);
        return (rgbValue >> BYTE) & LAST_BYTE;
    }

    int getBlue(Coordinate coordinate) {
        int rgbValue = getRgbValue(coordinate);
        return rgbValue & LAST_BYTE;
    }

    private int getRgbValue(Coordinate coordinate) {
        if (coordinate.getX() < 0 || coordinate.getX() > bufferedImage.getWidth()) {
            throw new CoordinateOutOfRangeException(X_OUT_OF_RANGE + bufferedImage.getWidth());
        } else if (coordinate.getY() < 0 || coordinate.getY() > bufferedImage.getHeight()) {
            throw new CoordinateOutOfRangeException(Y_OUT_OF_RANGE + bufferedImage.getHeight());
        }
        return bufferedImage.getRGB(coordinate.getX(), coordinate.getY());
    }

    private BufferedImage loadImageFromFile(String fileName) {
        try {
            URL url = getClass().getClassLoader().getResource(fileName);
            assert url != null;
            return ImageIO.read(url);
        } catch (IOException exception) {
            throw new FileNotFoundException(FILE_NOT_FOUND, exception);
        }
    }

}

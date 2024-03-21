package com.shpp.p2p.cs.aiakovenko.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The class to read file with image,
 * create an array of pixels and modify it
 */
public class ImageProcessor {

    /**
     * Receives file name, reads it, creates a double array of pixels
     * and convert it to grayscale, and then to black and white image
     *
     * @param fileName          String with file name in format *.jpg
     * @return                  double array of pixels
     * @throws IOException      catch and throws higher exception if file wasn't read
     */
    public static int[][] getImage(String fileName) throws IOException {
        File file = new File(Constants.FILE_PATH + fileName);
        int[][] image = createPixelsFromJPG(file);
        return toBlackAndWhite(toGrayscale(image));
    }

    /**
     * Reads the file with an image and create an array of pixels
     *
     * @param file the file with image
     * @return array of pixels
     * @throws IOException if file couldn't be read
     */
    private static int[][] createPixelsFromJPG(File file) throws IOException {
        BufferedImage image = null;

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new IOException("Can`t read the file");
        }
        int height = image.getHeight();
        int width = image.getWidth();
        int[][] imagePixels = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imagePixels[y][x] = image.getRGB(x, y);
            }
        }
        return imagePixels;
    }

    /**
     * Modifies the array of colored pixels to the array with grayscale pixels
     *
     * @param image the array of pixels
     * @return the modified array of pixels
     */
    private static int[][] toGrayscale(int[][] image) {
        int height = image.length;
        int width = image[0].length;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Color color = new Color(image[i][j]);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red + green + blue) / 3;
                image[i][j] = new Color(gray, gray, gray).getRGB();
            }
        }
        return image;
    }

    /**
     *
     * Modifies the array of grayscale pixels to black and white
     *
     * @param image the array of pixels
     * @return the modified array of pixels
     */
    private static int[][] toBlackAndWhite(int[][] image) {
        int averagePixel = getAveragePixel(image); // average gray
        convertGrayToBlackAndWhite(image, averagePixel);
        return image;
    }

    /**
     * Takes gray pixel and change it to white or black one
     *
     * @param averagePixel int of average gray
     */
    private static void convertGrayToBlackAndWhite(int[][] image, int averagePixel) {
        int height = image.length;
        int width = image[0].length;

        int blackPixels = 0;
        int whitePixels = 0;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = image[i][j];
                if (pixel < averagePixel) {
                    image[i][j] = Constants.BLACK_COLOR;
                    blackPixels++;
                } else {
                    image[i][j] = Constants.WHITE_COLOR;
                    whitePixels++;
                }
            }
        }
        /* if black area is bigger than white - invert image
         * because the background has a bigger area in our conception
         */
        if (whitePixels <= blackPixels) {
            invertImage(image);
        }
    }

    /**
     * Change black pixel to white and white to black
     *
     * @param image the array of pixels
     */
    private static void invertImage(int[][] image) {
        int height = image.length;
        int width = image[0].length;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (image[i][j] == Constants.BLACK_COLOR) {
                    image[i][j] = Constants.WHITE_COLOR;
                } else {
                    image[i][j] = Constants.BLACK_COLOR;
                }
            }
        }
    }

    /**
     * Finds the lightest and the darkest pixel and calculate the average gray
     *
     * @param image the array of pixels
     * @return int of average gray
     */
    private static int getAveragePixel(int[][] image) {
        int height = image.length;
        int width = image[0].length;

        int lightestPixel = image[0][1];
        int darkestPixel = image[0][1];
        int averagePixel;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = image[i][j];
                if (lightestPixel < pixel) {
                    lightestPixel = pixel;
                }
                if (darkestPixel > pixel) {
                    darkestPixel = pixel;
                }
            }
        }
        averagePixel = (lightestPixel + darkestPixel) / 2;
        return averagePixel;
    }

}

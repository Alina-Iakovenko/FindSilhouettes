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
    // height of the image
    static int height;
    // width of the image
    static int width;
    // array of pixels of the image
    static int[][] image;

    /**
     * The constructor to create an object of the class
     * @param file              the file in jpg format with image
     * @throws IOException      if file couldn't be read
     */
    public ImageProcessor(File file) throws IOException {
        image = createPixelsFromJPEG(file);
    }

    /**
     * The getter for array of pixels of modified image
     * @return  array of pixels
     */
    public int[][] getImage() {
        return this.toBlackAndWhite(this.toGrayscale(image));
    }

    /**
     * Reads the file with an image and create an array of pixels
     * @param file          the file with image
     * @return              array of pixels
     * @throws IOException  if file couldn't be read
     */
    public static int[][] createPixelsFromJPEG(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        height = image.getHeight();
        width = image.getWidth();
        int[][] imagePixels = new int[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imagePixels[y][x] = image.getRGB(x, y);
            }
        }
        return imagePixels;
    }

    /**
     * Modify image to grayscale
     * @param image the array of pixels
     * @return      the modified array of pixels
     */
    private int[][] toGrayscale(int[][] image) {
        int[][] grayscaleImage = new int[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                Color color = new Color(image[i][j]);
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int gray = (red + green + blue) / 3;
                grayscaleImage[i][j] = new Color(gray, gray, gray).getRGB();
            }
        }
        return grayscaleImage;
    }
    /**
     * Modify grayscale image to black and white
     * @param image the array of pixels
     * @return      the modified array of pixels
     */
    private int[][] toBlackAndWhite(int[][] image) {
        int[][] blackAndWhiteImage = new int[height][width];
        int averagePixel = getAveragePixel(image); // average gray
        convertGrayToBlackAndWhite(image, averagePixel, blackAndWhiteImage);
        return blackAndWhiteImage;
    }

    /**
     * Takes gray pixel and change it to white or black one
     * @param grayscaleImage        array of the image to convert
     * @param averagePixel          int of average gray
     * @param blackAndWhiteImage    array of the image to save to black and white image
     */
    private void convertGrayToBlackAndWhite(int[][] grayscaleImage, int averagePixel, int[][] blackAndWhiteImage) {
        int blackPixels = 0;
        int whitePixels = 0;

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = grayscaleImage[i][j];
                if (pixel < averagePixel) {
                    blackAndWhiteImage[i][j] = 0;
                    blackPixels++;
                }
                else {
                    blackAndWhiteImage[i][j] = -1;
                    whitePixels++;
                }
            }
        }
        /* if black area is bigger than white - invert image
        * because the background has a bigger area in our conception
        */
        if (whitePixels <= blackPixels) {
            invertImage(blackAndWhiteImage);
        }
    }

    /**
     * Change black pixel to white and white to black
     * @param image     the array of pixels
     */
    private static void invertImage(int[][] image) {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = image[i][j];
                if (pixel == 0) {
                    image[i][j] = -1;
                } else {
                    image[i][j] = 0;
                }
            }
        }
    }

    /**
     * Finds the lightest and the darkest pixel and calculate the average gray
     * @param image     the array of pixels
     * @return          int of average gray
     */
    private static int getAveragePixel(int[][] image) {
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
        averagePixel = (lightestPixel + darkestPixel)/2;
        return averagePixel;
    }

}

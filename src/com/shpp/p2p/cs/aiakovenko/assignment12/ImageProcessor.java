package com.shpp.p2p.cs.aiakovenko.assignment12;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {

    public static int[][] createPixelsFromJPEG(File file) throws IOException {

        // Читаємо зображення з файлу
        BufferedImage image = ImageIO.read(file);

        // Отримуємо розмір зображення
        int width = image.getWidth();
        int height = image.getHeight();

        // Створюємо масив для зберігання пікселів
        int[][] imagePixels = new int[height][width];

        // Заповнюємо масив пікселів даними зображення
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imagePixels[y][x] = image.getRGB(x, y);
            }
        }
        return imagePixels;
    }

    public static boolean[][] createBooleanPixels(File file) throws IOException {
        // Читаємо зображення з файлу
        BufferedImage image = ImageIO.read(file);

        // Отримуємо розмір зображення
        int width = image.getWidth();
        int height = image.getHeight();

        // Створюємо масив для зберігання пікселів
        boolean[][] imagePixels = new boolean[height][width];

        // Заповнюємо масив пікселів даними зображення
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imagePixels[y][x] = false;
            }
        }
        return imagePixels;
    }

    public static int[][] toGrayscale(int[][] image) {
        int height = image.length;
        int width = image[0].length;
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
    public static int[][] toContrast(int[][] pixels) {
        int[][] luminances = new int[pixels.length][pixels[0].length];

        for (int i = 0; i < pixels.length; ++i) {
            for (int j = 0; j < pixels[i].length; ++j) {
                Color color = new Color(pixels[i][j]);
                luminances[i][j] = color.getRed();
            }
        }

        return luminances;
    }
    public static int[][] contrastToBlackAndWhite(int[][] image) {
        int height = image.length;
        int width = image[0].length;
        int[][] blackAndWhiteImage = new int[height][width];
        int lightestPixel = image[0][1]; //255
        int darkestPixel = image[0][1]; //0
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

        System.out.println("lightestPixel " + lightestPixel);
        System.out.println("darkestPixel " + darkestPixel);
        System.out.println("averagePixel " + averagePixel);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = image[i][j];
                if (pixel < averagePixel) {
                    blackAndWhiteImage[i][j] = 0;
                }
                else {
                    blackAndWhiteImage[i][j] = -1;
                }
            }
        }
        return blackAndWhiteImage;
    }




    public static void printImage(int[][] image) {
        int height = image.length;
        int width = image[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(image[i][j] + " ");
            }
            System.out.println();
        }
    }
}

package com.shpp.p2p.cs.aiakovenko.assignment12;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The class to show image in the separate window
 */
public class ImageDisplay {
    /**
     * Creates window and show image
     * @param grayscaleImage    an array of pixels of the image
     */
    public static void displayImage(int[][] grayscaleImage) {
        JFrame frame = new JFrame("Grayscale Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawImage(g, grayscaleImage);
            }
        };
        int width = grayscaleImage[0].length;
        int height = grayscaleImage.length;
        panel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates image from an array of pixels
     * @param g                 Graphics object
     * @param grayscaleImage    array of pixels
     */
    private static void drawImage(Graphics g, int[][] grayscaleImage) {
        int width = grayscaleImage[0].length;
        int height = grayscaleImage.length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int gray = grayscaleImage[y][x];
                image.setRGB(x, y, gray);
            }
        }
        g.drawImage(image, 0, 0, null);
    }

}


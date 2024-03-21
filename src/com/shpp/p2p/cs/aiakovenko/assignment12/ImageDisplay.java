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
     * @param image    an array of pixels of the image
     */
    public static void displayImage(int[][] image) {
        JFrame frame = new JFrame("Detect silhouettes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawImage(g, image);
            }
        };
        int width = image[0].length;
        int height = image.length;
        panel.setPreferredSize(new Dimension(width, height));
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates image from an array of pixels
     * @param g                 Graphics object
     * @param image    array of pixels
     */
    private static void drawImage(Graphics g, int[][] image) {
        int width = image[0].length;
        int height = image.length;

        BufferedImage imageToDisplay = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int color = image[y][x];
                imageToDisplay.setRGB(x, y, color);
            }
        }
        g.drawImage(imageToDisplay, 0, 0, null);
    }

}


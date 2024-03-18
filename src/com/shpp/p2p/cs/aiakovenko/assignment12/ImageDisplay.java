package com.shpp.p2p.cs.aiakovenko.assignment12;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageDisplay {

    // Метод, який відображає масив сірих пікселів у новому вікні
    public void displayImage(int[][] grayscaleImage) {
        // Створення нового JFrame
        JFrame frame = new JFrame("Grayscale Image");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Створення JPanel для відображення зображення
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawImage(g, grayscaleImage);
            }
        };

        // Встановлення розмірів панелі
        int width = grayscaleImage[0].length;
        int height = grayscaleImage.length;
        panel.setPreferredSize(new Dimension(width, height));

        // Додавання панелі до JFrame
        frame.getContentPane().add(panel);

        // Налаштування розмірів вікна та відображення його на екрані
        frame.pack();
        frame.setVisible(true);
    }

    // Метод для малювання зображення на JPanel
    private void drawImage(Graphics g, int[][] grayscaleImage) {
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


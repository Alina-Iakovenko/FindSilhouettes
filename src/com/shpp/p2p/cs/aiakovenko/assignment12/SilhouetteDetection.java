package com.shpp.p2p.cs.aiakovenko.assignment12;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for detecting and calculating silhouettes
 */
public class SilhouetteDetection {
    /**
     * The list with silhouettes
     */
    List<Silhouette> silhouettes;
    /**
     * The array of pixels of the image
     */
    int[][] imagePixel;
    /**
     * The array to remember checked pixels
     */
    boolean[][] checkedPixelsArray;
    /**
     * Quantity of pixels of the silhouettes
     */
    int silhouetteArea;

    /**
     * Creates the class` object that can detect and calculate silhouettes
     *
     * @param image an array of pixels of the image
     */
    public SilhouetteDetection(int[][] image) {
        imagePixel = image;
        checkedPixelsArray = new boolean[imagePixel.length][imagePixel[0].length];
        silhouettes = new ArrayList<>();
    }

    /**
     * Detects and calculate silhouettes on the image
     *
     * @return a quantity of silhouettes
     */
    public int calculateSilhouettes() {
        detectSilhouettes();
        return silhouettes.size();
    }

    /**
     * Finds the first black pixel of the silhouette,
     * calculating the area using deep recursive
     * and add new silhouette to the list
     */
    public void detectSilhouettes() {
        int height = imagePixel.length;
        int width = imagePixel[0].length;
        double totalArea = height * width;
        double garbageThreshold = totalArea * Constants.PERCENT_OF_TOTAL_AREA;
        System.out.println(garbageThreshold);

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                //if the pixel is black
                if (imagePixel[i][j] == Constants.BLACK_COLOR) {
                    checkPixelsUsingDFS(i, j, height, width);
                    /* when all black pixels of the silhouette are found,
                     * the silhouette is big enough add it to the list
                     */
                    if (silhouetteArea > 0 && silhouetteArea > garbageThreshold) {
                        System.out.println(silhouetteArea);
                        silhouettes.add(new Silhouette(silhouetteArea));
                        silhouetteArea = 0;
                    }
                }
                // if the pixel is white change it`s status in the array to checked
                else {
                    checkedPixelsArray[i][j] = true;
                }
            }
        }
    }

    /**
     * Check all neighbor pixels of the black one
     *
     * @param i coordinate of the pixel on the ordinate axis
     * @param j coordinate of the pixel on the abscissa axis
     */
    private void checkPixelsUsingDFS(int i, int j, int height, int width) {
        if (i >= 0 && i < height && j >= 0 && j < width) {
            if (imagePixel[i][j] == Constants.BLACK_COLOR) {
                if (!checkedPixelsArray[i][j]) {
                    checkedPixelsArray[i][j] = true;
                    silhouetteArea++;
                    for (int k = 0; k < Constants.NEXT_X.length; k++) {
                        int neighborX = i + Constants.NEXT_X[k];
                        int neighborY = j + Constants.NEXT_Y[k];
                        checkPixelsUsingDFS(neighborX, neighborY, height, width);
                    }
                }
            }
        }
    }
}


package com.shpp.p2p.cs.aiakovenko.assignment12;

import java.util.ArrayList;
import java.util.List;

/**
 * The class for detecting and calculating silhouettes
 */
public class SilhouetteDetection {
    // a list with silhouettes
    static List<Silhouette> silhouettes;
    // the array of pixels of the image
    static int[][] imagePixel;
    // the array to remember checked pixels
    static boolean[][] checkedPixels;
    // quantity of pixels of the silhouettes
    int silhouetteArea;
    // the height of the image
    static int height;
    // the width of the image
    static int width;

    /**
     * The constructor to create the class object
     * @param image an array of pixels of the image
     */
    public SilhouetteDetection(int[][] image) {
        imagePixel = image;
        height = imagePixel.length;
        width = imagePixel[0].length;
        checkedPixels = new boolean[height][width];
        silhouettes = new ArrayList<>();
    }

    /**
     * The finish method of calculating silhouettes
     * @return  a quantity of silhouettes
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
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = imagePixel[i][j];
                if (isBlack(pixel)) {
                    checkPixels8Neighboures(i, j);
                    /* when all black pixels of the silhouette are found
                    * the silhouette is big enough add it to the list
                    */
                    if (silhouetteArea > 0) {
                        if (silhouetteArea > height * width / 300) {
                            silhouettes.add(new Silhouette(silhouetteArea));
                        }
                        silhouetteArea = 0;
                    }
                }
            }
        }
    }

    /**
     * Check all neighbor pixels of the black one
     * @param i     coordinate of the pixel on the ordinate axis
     * @param j     coordinate of the pixel on the abscissa axis
     */
    private void checkPixels8Neighboures(int i, int j) {
        if (j == 0 && i == 0) {
            checkTheTopLeftCorner(i, j);
        } 
        else if (j == 0 && i > 0 && i < height - 1) {
            checkTheLeftColumn(i, j);
        }
        else if (i == height - 1 && j == 0) {
            checkTheBottomLeftCorner(i, j);
        }
        else if (i == height - 1 && j > 0 && j < width - 1) {
            checkTheBottomRow(i, j);
        }
        else if (i == height - 1 && j == width - 1) {
            checkTheBottomRightCorner(i, j);
        }
        else if (j == width - 1 && i < height - 1 && i > 0) {
            checkTheRightColumn(i, j);
        }
        else if (i == 0 && j == width - 1) {
            checkTheTopRightCorner(i, j);
        }
        else if (i == 0 && j < width - 1 && j > 0) {
            checkTheTopRow(i, j);
        }
        else {
            checkNotBorderPixels(i, j);
        }
    }

    private void checkTheTopRightCorner(int i, int j) {
        checkPixel(i + 1, j);
        checkPixel(i + 1, j - 1);
        checkPixel(i, j - 1);
    }

    private void checkNotBorderPixels(int i, int j) {
        checkPixel(i - 1, j - 1);
        checkPixel(i, j - 1);
        checkPixel(i + 1, j - 1);
        checkPixel(i + 1, j);
        checkPixel(i + 1, j + 1);
        checkPixel(i, j + 1);
        checkPixel(i - 1, j + 1);
        checkPixel(i - 1, j);
    }

    private void checkTheTopRow(int i, int j) {
        checkTheTopLeftCorner(i, j);
        checkPixel(i + 1, j - 1);
        checkPixel(i, j - 1);
    }

    private void checkTheRightColumn(int i, int j) {
        checkTheTopRightCorner(i, j);
        checkPixel(i - 1, j - 1);
        checkPixel(i - 1, j);
    }

    private void checkTheBottomRightCorner(int i, int j) {
        checkPixel(i, j - 1);
        checkPixel(i - 1, j - 1);
        checkPixel(i - 1, j);
    }

    private void checkTheBottomRow(int i, int j) {
        checkTheBottomRightCorner(i, j);
        checkPixel(i - 1, j + 1);
        checkPixel(i, j + 1);
    }

    private void checkTheBottomLeftCorner(int i, int j) {
        checkPixel(i - 1, j);
        checkPixel(i - 1, j + 1);
        checkPixel(i, j + 1);
    }

    private void checkTheLeftColumn(int i, int j) {
        checkTheBottomLeftCorner(i, j);
        checkPixel(i + 1, j + 1);
        checkPixel(i + 1, j);
    }

    private void checkTheTopLeftCorner(int i, int j) {
        checkPixel(i, j + 1);
        checkPixel(i + 1, j + 1);
        checkPixel(i + 1, j);
    }

    /**
     * Check if pixel was visited earlier.
     * If no - change status in the array of checked pixels.
     * If the pixel is black, add 1 to the silhouette's area
     * and check all eight neighbors
     *
     * @param i     coordinate of the pixel on the ordinate axis
     * @param j     coordinate of the pixel on the abscissa axis
     */
    private void checkPixel(int i, int j) {
        if (!isChecked(checkedPixels[i][j])) {
            checkedPixels[i][j] = true;
            if (isBlack(imagePixel[i][j])) {
                silhouetteArea++;
                checkPixels8Neighboures(i, j);
            }
        }
    }

    /**
     * Check if the pixel was visited earlier
     * @param pixel     a boolean from checkedPixels array
     * @return          true if was vested earlier or false if no
     */
    public boolean isChecked(boolean pixel) {
        if (pixel == true) {
            return true;
        }
        return false;
    }
    /**
     * Check if the pixel is black
     * @param pixel     the pixel to check
     * @return          true if black or false if white
     */
    public boolean isBlack(int pixel) {
        if (pixel == 0) {
            return true;
        }
        return false;
    }

}


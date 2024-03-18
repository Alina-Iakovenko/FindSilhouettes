package com.shpp.p2p.cs.aiakovenko.assignment12;

import java.util.ArrayList;
import java.util.List;

public class SilhouetteDetection {
    static List<Silhouette> silhouettes = new ArrayList<>();
    int[][] imagePixel;
    boolean[][] checkedPixels;
    int silhouetteArea;
    int height;
    int width;

    public SilhouetteDetection(int[][] imagePixel, boolean[][] checkedPixels) {
        this.imagePixel = imagePixel;
        this.checkedPixels = checkedPixels;
        this.height = this.imagePixel.length;
        this.width = this.imagePixel[0].length;
    }

    public void detectSilhouettes() {
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                int pixel = this.imagePixel[i][j];
                if (isBlack(pixel)) {
                    checkPixel(i, j);
                }
            }
        }
    }

    private void checkPixel(int i, int j) {
        // check the top left corner
        if (i == 0 && j == 0) {
            checkNeighbourPixel(i + 1, j);
            checkNeighbourPixel(i + 1, j + 1);
            checkNeighbourPixel(i, j + 1);
        }
        // check the top line
        else if (i < width-2 && i > 0 && j == 0) {
            checkNeighbourPixel(i+1, j);
            checkNeighbourPixel(i+1, j + 1);
            checkNeighbourPixel(i, j + 1);
            checkNeighbourPixel(i-1, j + 1);
            checkNeighbourPixel(i-1, j);
        }
        // check the top right corner
        else if (i == width-1 && j == 0) {
            checkNeighbourPixel(i, j + 1);
            checkNeighbourPixel(i - 1, j + 1);
            checkNeighbourPixel(i - 1, j);
        }
        // check the right column
        else if (i == width-1 && j > 0 && j < height-2) {
            checkNeighbourPixel(i, j+1);
            checkNeighbourPixel(i-1, j + 1);
            checkNeighbourPixel(i-1, j);
            checkNeighbourPixel(i-1, j - 1);
            checkNeighbourPixel(i, j-1);
        }
        // check the bottom right corner
        else if (i == width-1 && j == height-1) {
            checkNeighbourPixel(i - 1, j);
            checkNeighbourPixel(i - 1, j - 1);
            checkNeighbourPixel(i, j - 1);
        }
        // check the bottom line
        else if (i < width-2 && i > 0 && j == height-1) {
            checkNeighbourPixel(i-1, j);
            checkNeighbourPixel(i-1, j - 1);
            checkNeighbourPixel(i, j - 1);
            checkNeighbourPixel(i+1, j + 1);
            checkNeighbourPixel(i+1, j);
        }
        // check the bottom left corner
        else if (i == 0 && j == height-1) {
            checkNeighbourPixel(i, j - 1);
            checkNeighbourPixel(i + 1, j - 1);
            checkNeighbourPixel(i + 1, j);
        }
        // check the left column
        else if (i == 0 && j > 0 && j < height-2) {
            checkNeighbourPixel(i, j-1);
            checkNeighbourPixel(i+1, j + 1);
            checkNeighbourPixel(i+1, j);
            checkNeighbourPixel(i+1, j + 1);
            checkNeighbourPixel(i, j+1);
        }
        // check not border pixels
        else {
            checkNeighbourPixel(i - 1, j - 1);
            checkNeighbourPixel(i, j - 1);
            checkNeighbourPixel(i + 1, j - 1);
            checkNeighbourPixel(i + 1, j);
            checkNeighbourPixel(i + 1, j + 1);
            checkNeighbourPixel(i, j + 1);
            checkNeighbourPixel(i - 1, j + 1);
            checkNeighbourPixel(i - 1, j);
        }
        if (silhouetteArea > 0) {
            if (silhouetteArea > height*width/150) {
                silhouettes.add(new Silhouette(silhouetteArea));
            }
            silhouetteArea = 0;
        }
    }

    private void checkNeighbourPixel(int i, int j) {
        if (!isChecked(checkedPixels[i][j])) {
            checkedPixels[i][j] = true;
            if (isBlack(imagePixel[i][j])) {
                silhouetteArea++;
                checkPixel(i, j);
            }
        }
    }

    public boolean isChecked(boolean pixel) {
        if (pixel == true) {
            return true;
        }
        return false;
    }

    public boolean isBlack(int pixel) {
        if (pixel == 0) {
            return true;
        }
        return false;
    }

}


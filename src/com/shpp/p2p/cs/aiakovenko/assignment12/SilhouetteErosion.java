package com.shpp.p2p.cs.aiakovenko.assignment12;

/**
 * Class for separating two silhouettes that are a little stick together.
 * Silhouettes should be black, and background - white,
 * so it works only for black and white images.
 * There shouldn't be any other parts of silhouettes
 * that are connected only by several pixels.
 * Constant can regulate the deepness of erosion.
 */
public class SilhouetteErosion {
    /**
     * Eroses black silhouettes on a white background for chosen deepness
     *
     * @param imagePixels   array of black and white pixels of image to erose
     * @param erosionDeep   the quantity of pixel to remove from the borders of silhouette
     * @return              image with erosed silhouettes
     */
    public static int[][] repeatErosion(int[][] imagePixels, int erosionDeep) {
        for (int i = 0; i < erosionDeep; i++) {
            imagePixels = SilhouetteErosion.erosion(imagePixels);
        }
        return imagePixels;
    }

    /**
     * Eroses silhouettes for chosen deepness by inverting to white all black pixels,
     * that have a white pixel among 8 neighbor pixels
     *
     * @param image     array of black and white pixels of image to erose
     * @return          image with erosed silhouettes
     */
    public static int[][] erosion(int[][] image) {
        int height = image.length;
        int width = image[0].length;

        int[][] erosedImage = new int[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (image[i][j] == Constants.BLACK_COLOR) {
                    erosedImage[i][j] = Constants.BLACK_COLOR;
                    checkNeighborPixels(image, i, j, erosedImage);
                } else {
                    erosedImage[i][j] = Constants.WHITE_COLOR;
                }
            }
        }
        return erosedImage;
    }

    /**
     * Checks 8 neighbors of the black pixel.
     * If there is at least one white pixel black pixel invert to white.
     *
     * @param image             array of black and white pixels of image to erose
     * @param i                 x ccordinate of pixel in image to erose
     * @param j                 y ccordinate of pixel in image to erose
     * @param eroisedImage      array of black and white pixels of erosed image
     */
    private static void checkNeighborPixels(int[][] image, int i, int j, int[][] eroisedImage) {
        for (int k = 0; k < Constants.NEXT_X.length; k++) {
            int neighborX = i + Constants.NEXT_X[k];
            int neighborY = j + Constants.NEXT_Y[k];
            if (i > 0 && i < image.length - 1 && j > 0 && j < image[0].length - 1)
                if (image[neighborX][neighborY] == Constants.WHITE_COLOR) {
                    eroisedImage[i][j] = Constants.WHITE_COLOR;
                }
        }
    }
}

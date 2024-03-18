package com.shpp.p2p.cs.aiakovenko.assignment12;

public class Histogram {
    private static final int MAX_LUMINANCE = 255;

    /**
     * Given the luminances of the pixels in an image, returns a histogram of the frequencies of
     * those luminances.
     * <p/>
     * You can assume that pixel luminances range from 0 to MAX_LUMINANCE, inclusive.
     *
     * @param luminances The luminances in the picture.
     * @return A histogram of those luminances.
     */
    public static int[] histogramFor(int[][] luminances) {
        int[] histogramFor = new int[256]; // here we`ll creat histogram
        for (int i = 0; i < 256; i++) { // we take every possible value
            int valuInHistogram = 0;
            //and check how many pixels have the same
            for (int[] row : luminances) {
                for (int pixel : row) {
                    if (i == pixel) {
                        valuInHistogram++;
                    }
                }
            }
            histogramFor[i] = valuInHistogram; // write to array number of pixel with this value
        }
        return histogramFor;
    }

    /**
     * Given a histogram of the luminances in an image, returns an array of the cumulative
     * frequencies of that image.  Each entry of this array should be equal to the sum of all
     * the array entries up to and including its index in the input histogram array.
     * <p/>
     * For example, given the array [1, 2, 3, 4, 5], the result should be [1, 3, 6, 10, 15].
     *
     * @param histogram The input histogram.
     * @return The cumulative frequency array.
     */
    public static int[] cumulativeSumFor(int[] histogram) {
        int[] cumulativeSumFor = new int[256]; // creates array for future cumulative histogram
        cumulativeSumFor[0] = histogram[0]; // determines the first value
        // determines all other values
        for (int i = 1; i < histogram.length; i++) {
            int cumulativeValueInHistogram = cumulativeSumFor[i - 1] + histogram[i];
            cumulativeSumFor[i] = cumulativeValueInHistogram;
        }
        return cumulativeSumFor;
    }

    /**
     * Returns the total number of pixels in the given image.
     *
     * @param luminances A matrix of the luminances within an image.
     * @return The total number of pixels in that image.
     */
    public static int totalPixelsIn(int[][] luminances) {
        int row = luminances.length;
        int column = luminances[0].length;
        int totalPixelsIn = row * column;
        return totalPixelsIn;
    }

    /**
     * Applies the histogram equalization algorithm to the given image, represented by a matrix
     * of its luminances.
     * <p/>
     * You are strongly encouraged to use the three methods you have implemented above in order to
     * implement this method.
     *
     * @param luminances The luminances of the input image.
     * @return The luminances of the image formed by applying histogram equalization.
     */
    public static int[][] equalize(int[][] luminances) {
        /* creates new double array for equalized picture */
        int rows = luminances.length;
        int columns = luminances[0].length;
        int[][] equalizedLuminances = new int[rows][columns];

        /* collect info about picture we should equalize */
        int[] histogramFor = histogramFor(luminances);
        int[] cumulativeHistogram = cumulativeSumFor(histogramFor);
        int totalPixels = totalPixelsIn(luminances);

        /* let`s equalize */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int pixel = luminances[i][j]; // for every pixel in the picture
                // use given in task formula with correction in variable's types
                double fractionSmaller = (double) cumulativeHistogram[pixel] / totalPixels;
                if (fractionSmaller < 0.5) {
                    equalizedLuminances[i][j] = 0;
                } else {
                    equalizedLuminances[i][j] = 255;
                }

            }
        }
        return equalizedLuminances;
    }

    public static int[][] convertToBlackAndWhite(int[][] grayscaleImage) {
        /* Створення нового масиву для чорно-білого зображення */
        int rows = grayscaleImage.length;
        int columns = grayscaleImage[0].length;
        int[][] blackAndWhiteImage = new int[rows][columns];

        /* Еквалізація зображення */
        int[][] equalizedLuminances = equalize(grayscaleImage);

        /* Перетворення значень яскравості на біле або чорне */
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // Якщо значення яскравості перевищує півмаксимума, встановлюється білий колір, інакше - чорний
                blackAndWhiteImage[i][j] = equalizedLuminances[i][j] > MAX_LUMINANCE / 2 ? MAX_LUMINANCE : 0;
            }
        }
        return blackAndWhiteImage;
    }
}

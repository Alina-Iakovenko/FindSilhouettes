package com.shpp.p2p.cs.aiakovenko.assignment12;

import java.io.File;
import java.io.IOException;

/**
 * The program receives a jpg-format image and calculates the quantity of silhouettes on it.
 * The background should be rather simple and contrast to silhouettes,
 * and its area should be bigger than the total silhouettes` area.
 */
public class Assignment12Part1 {
//      dark background:
//    private static String testFilePath = "assets/blackBackground/butterflies.jpg";
//    private static String testFilePath = "assets/blackBackground/butterflies2.jpg";
//    private static String testFilePath = "assets/blackBackground/circlesAndSquare.jpg";
//    private static String testFilePath = "assets/blackBackground/paintedMusicians.jpg";
//    private static String testFilePath = "assets/blackBackground/simpleSmallThree.jpg";
//    private static String testFilePath = "assets/blackBackground/terribleCase.jpg";

//    private static String testFilePath = "assets/bigSilhouettes.jpg"; // - silhouettes too big
//    private static String testFilePath = "assets/emptyWhite.jpg";
//    private static String testFilePath = "assets/emptyBlack.jpg";
//    private static String testFilePath = "assets/blueSquares.jpg";
//    private static String testFilePath = "assets/blueYellow.jpg";
    private static String testFilePath = "assets/boy.jpg";
//    private static String testFilePath = "assets/diagonalSquares.jpg";
//    private static String testFilePath = "assets/din.jpg";
//    private static String testFilePath = "assets/fiveSilhouettes.jpg";
//    private static String testFilePath = "assets/gimnastics.jpg";
//    private static String testFilePath = "assets/girlsAmongPalms.jpg";
//    private static String testFilePath = "assets/hearts.jpg";
//    private static String testFilePath = "assets/kids.jpg";
//    private static String testFilePath = "assets/nineOrTen.jpg";
//    private static String testFilePath = "assets/redSilhouettesOnGreen.jpg"; // +- not valid image
//    private static String testFilePath = "assets/squaredBackground.jpg";
//    private static String testFilePath = "assets/stars.jpg";
//    private static String testFilePath = "assets/superbeerman.jpg";
//    private static String testFilePath = "assets/triangles.jpg";
//    private static String testFilePath = "assets/unicorn.jpg"; // - not valid image

    public static void main(String[] args) throws IOException {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = testFilePath;
        }
        File file = new File(filePath);

        // Read the file, create and show black and white image from it
        ImageProcessor processor = new ImageProcessor(file);
        int[][] imagePixels = processor.getImage();
        ImageDisplay.displayImage(imagePixels);

        // Calculate silhouettes and print its quantity to console
        SilhouetteDetection detection = new SilhouetteDetection(imagePixels);
        System.out.println("Silhouettes found: " + detection.calculateSilhouettes());
    }
}

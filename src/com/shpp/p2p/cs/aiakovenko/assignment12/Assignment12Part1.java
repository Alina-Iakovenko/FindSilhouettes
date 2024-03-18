package com.shpp.p2p.cs.aiakovenko.assignment12;

import java.io.File;
import java.io.IOException;

public class Assignment12Part1 {
    //    private static String testFilePath = "assets/blueStudying/bluesStudying1.jpg";
//    private static String testFilePath = "assets/blueStudying/bluesStudying2.jpg";
//    private static String testFilePath = "assets/blueStudying/bluesStudying3.jpg";
//    private static String testFilePath = "assets/bigSiluetes.jpg";
//    private static String testFilePath = "assets/empty.jpg";
//    private static String testFilePath = "assets/blueSquares.jpg";
    private static String testFilePath = "assets/blueYellow.jpg";
//    private static String testFilePath = "assets/boy.jpg";
//    private static String testFilePath = "assets/butterflies.jpg";
//    private static String testFilePath = "assets/butterflies2.jpg";
//    private static String testFilePath = "assets/circlesAndSquare.jpg";
//    private static String testFilePath = "assets/circlesAndSquare_small.jpg";
//    private static String testFilePath = "assets/diagonalSquares.jpg";
//    private static String testFilePath = "assets/din.jpg";
//    private static String testFilePath = "assets/fiveSiluetes.jpg";
//    private static String testFilePath = "assets/gimnastics.jpg";
//    private static String testFilePath = "assets/girls.jpg";
//    private static String testFilePath = "assets/hearts.jpg";
//    private static String testFilePath = "assets/kids.jpg";
//    private static String testFilePath = "assets/nineOrTen.jpg";
//    private static String testFilePath = "assets/paintedMusicians.jpg";
//    private static String testFilePath = "assets/redSiluetesOnGreen.jpg"; // ^(
//    private static String testFilePath = "assets/simpleSmalThree.jpg";
//    private static String testFilePath = "assets/squaredBackground.jpg";
//    private static String testFilePath = "assets/stars.jpg";
//    private static String testFilePath = "assets/superbeerman.jpg";
//    private static String testFilePath = "assets/terribleCase.jpg";
//    private static String testFilePath = "assets/triangles.jpg";
//    private static String testFilePath = "assets/unicorn.jpg";
    static int[][] imagePixels;
    static boolean[][] checkedPixels;

    public static void main(String[] args) {
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = testFilePath;
        }
        File file = new File(filePath);

        ImageProcessor processor = new ImageProcessor();
        try {
            imagePixels = processor.createPixelsFromJPEG(file);
            imagePixels = processor.toGrayscale(imagePixels);
//            imagePixels = processor.toContrast(imagePixels);
            imagePixels = processor.contrastToBlackAndWhite(imagePixels);

            checkedPixels = processor.createBooleanPixels(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDisplay showImage = new ImageDisplay();
        showImage.displayImage(imagePixels);
        SilhouetteDetection silhouetteDetection = new SilhouetteDetection(imagePixels, checkedPixels);
        silhouetteDetection.detectSilhouettes();
        System.out.println("Silhouettes found: " + SilhouetteDetection.silhouettes.size());
    }
}

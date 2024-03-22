package com.shpp.p2p.cs.aiakovenko.assignment12;
import java.io.IOException;

/**
 * The program receives a jpg-format image and calculates the quantity of silhouettes on it.
 * The background should be rather simple and contrast to silhouettes,
 * and its area should be bigger than the total silhouettes` area.
 */
public class Assignment12Part1 {


    public static void main(String[] args){
        try {
            String fileName = "blackBackground/circlesAndSquare.jpg";
            if (args.length > 0) {
                fileName = args[0];
            }
            // Read the file, create and show black and white image from it
            int[][] imagePixels = ImageProcessor.getImage(fileName);
            imagePixels = SilhouetteErosion.repeatErosion(imagePixels,Constants.EROSION_DEEP);
            ImageDisplay.displayImage(imagePixels);

            // Calculate silhouettes and print its quantity to console
            SilhouetteDetection detection = new SilhouetteDetection(imagePixels);
            System.out.println("Silhouettes found: " + detection.calculateSilhouettes());
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

package tests;

import com.shpp.p2p.cs.aiakovenko.assignment12.*;
import com.shpp.p2p.cs.aiakovenko.assignment12.Constants;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assignment12Part1Test {

    public static int testMain(String[] args) {
        try {
            String fileName = "girlsAmongPalms.jpg";
            if (args.length > 0) {
                fileName = args[0];
            }
            // Read the file, create and show black and white image from it
            int[][] imagePixels = ImageProcessor.getImage(fileName);

            imagePixels = SilhouetteErosion.repeatErosion(imagePixels, Constants.EROSION_DEEP);
            // Calculate silhouettes and print its quantity to console
            SilhouetteDetection detection = new SilhouetteDetection(imagePixels);
            return detection.calculateSilhouettes();
        } catch (IOException e) {
            System.err.println(e);
        }
        return -1;
    }
    @Test
    public void Assignment12Part1_test() {
        String fileName = "test.jpg";
        int expectedResult = 4;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }

    @Test
    public void Assignment12Part1_testBlueSquares() {
        String fileName = "blueSquares.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBlueYellow() {
        String fileName = "blueYellow.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBoy() {
        String fileName = "boy.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testDiagonalSquares() {
        String fileName = "diagonalSquares.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testDin() {
        String fileName = "din.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testEmptyBlack() {
        String fileName = "emptyBlack.jpg";
        int expectedResult = 0;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testEmptyWhite() {
        String fileName = "emptyWhite.jpg";
        int expectedResult = 0;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testFiveSilhouettes() {
        String fileName = "fiveSilhouettes.jpg";
        int expectedResult = 5;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testGimnastics() {
        String fileName = "gimnastics.jpg";
        int expectedResult = 8;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testGirlsAmongPalms() {
        String fileName = "girlsAmongPalms.jpg";
        int expectedResult = 6;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testHearts() {
        String fileName = "hearts.jpg";
        int expectedResult = 10;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testKids() {
        String fileName = "kids.jpg";
        int expectedResult = 10;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testNineOrTen() {
        String fileName = "nineOrTen.jpg";
        int expectedResult = 10;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }

    @Test
    public void Assignment12Part1_testSquaredBackground() {
        String fileName = "squaredBackground.jpg";
        int expectedResult = 6;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testStars() {
        String fileName = "stars.jpg";
        int expectedResult = 4;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testSuperbeerman() {
        String fileName = "superbeerman.jpg";
        int expectedResult = 1;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testTransperentAlfaChanel() {
        String fileName = "testWithTransparentAlfaChanel.jpg";
        int expectedResult = 0;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }

    @Test
    public void Assignment12Part1_testTriangles() {
        String fileName = "triangles.jpg";
        int expectedResult = 3;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }

    @Test
    public void Assignment12Part1_testBlackBackgroundButterflies() {
        String fileName = "blackBackground/butterflies.jpg";
        int expectedResult = 20;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBlackBackgroundButterflies2() {
        String fileName = "blackBackground/butterflies2.jpg";
        int expectedResult = 5;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBlackBackgroundCirclesAndSquare() {
        String fileName = "blackBackground/circlesAndSquare.jpg";
        int expectedResult = 3;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBlackBackgroundPaintedMusicians() {
        String fileName = "blackBackground/paintedMusicians.jpg";
        int expectedResult = 3;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBlackBackgroundSimpleSmallThree() {
        String fileName = "blackBackground/simpleSmallThree.jpg";
        int expectedResult = 3;
        assertEquals(expectedResult, testMain(new String[]{fileName}));
    }

    @Test
    public void Assignment12Part1_testUnicorn_False() {
        String fileName = "unicorn.jpg";
        int expectedResult = 1;
        assertNotEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testRedSilhouettesOnGreen_False() {
        String fileName = "redSilhouettesOnGreen.jpg";
        int expectedResult = 4;
        assertNotEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testBigSilhouettes_False() {
        String fileName = "bigSilhouettes.jpg";
        int expectedResult = 3;
        assertNotEquals(expectedResult, testMain(new String[]{fileName}));
    }
    @Test
    public void Assignment12Part1_testWithIllegalArgument_False() {
        String fileName = "1";
        PrintStream originalErr = System.err;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        testMain(new String[]{fileName});
        System.setErr(originalErr);

        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("IOException"));
    }

}

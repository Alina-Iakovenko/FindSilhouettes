package com.shpp.p2p.cs.aiakovenko.assignment12;

public class Constants {

    /**
     * The integer for black color
     */
    final static int BLACK_COLOR = 0;
    /**
     * The integer for white color
     */
    final static int WHITE_COLOR = -1;
    /**
     * The coefficient to set a garbage threshold
     */
    final static double PERCENT_OF_TOTAL_AREA = 0.002;
    /**
     * The program looks for image file in this package
     */
    static final String FILE_PATH = "assets/";
    //An array with coordinate on axis X of pixels neighbors
    static final int[] NEXT_X = {-1, -1, -1, 0, 1, 1, 1, 0};
    //An array with coordinate on axis Y of pixels neighbors
    static final int[] NEXT_Y = {-1, 0, 1, 1, 1, 0, -1, -1};
    public static final int EROSION_DEEP = 0;
}

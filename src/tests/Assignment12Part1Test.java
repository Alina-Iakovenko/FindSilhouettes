package tests;
import com.shpp.p2p.cs.aiakovenko.assignment12.Assignment12Part1;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Assignment12Part1Test {
    @Test
    public void testImageProcessing_boy(){
        String filePath = "assets/boy.jpg";
        Assignment12Part1.main(new String[]{filePath});
    }
}

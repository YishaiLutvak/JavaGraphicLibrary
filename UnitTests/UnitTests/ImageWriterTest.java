package UnitTests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Testing ImageWriter class
 * by creating simple image with no any geometries
 * @author Michael Bergshtein and Yishai Lutvak
 */
public class ImageWriterTest {
    /**
     * Test for
     * {@link renderer.ImageWriter#ImageWriter(String, double, double, int, int)}.
     * {@link ImageWriter#getNx()}
     * {@link ImageWriter#getNy()}
     * {@link renderer.ImageWriter#writePixel(int, int, Color)}
     * {@link ImageWriter#writeToImage()}
     */
    @Test
    public void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("firstImage",1600,1000, 800, 500);
        int Nx=imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i= 0; i < Ny;i++)
            for (int j = 0; j <Nx; j++)
                if(i % 50 == 0 || j % 50 == 0 )
                    imageWriter.writePixel(j,i, Color.YELLOW);
                else
                    imageWriter.writePixel(j,i, Color.BLUE);
        imageWriter.writeToImage();
    }
}
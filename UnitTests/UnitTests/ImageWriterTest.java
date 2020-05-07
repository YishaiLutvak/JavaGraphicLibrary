package UnitTests;

import org.junit.Test;
import renderer.ImageWriter;

import java.awt.*;

import static org.junit.Assert.*;

public class ImageWriterTest {
    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter("MyImage",1600,1000, 800, 500);
        int Nx=imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i= 0; i < Ny;i++)
            for (int j = 0; j <Nx; j++)
                //if(i % 50 ==)
                imageWriter.writePixel(j,i, Color.BLUE);
        imageWriter.writeToImage();

    }

}
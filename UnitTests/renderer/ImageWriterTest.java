package renderer;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void writeToImage() {
        ImageWriter imageWriter = new ImageWriter(,1600,1000,);
        int Nx=imageWriter.getNx();
        int Ny = imageWriter.getNy();
        for (int i= 0; i < Ny;i++)
            for (int j = 0; j <Nx; j++)
                //if(i % 50 ==)
                imageWriter.writePixel(j,i, Color.BLUE);
        imageWriter.writeToImage();

    }
}
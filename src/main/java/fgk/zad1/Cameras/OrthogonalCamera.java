package fgk.zad1.Cameras;

import fgk.zad1.Basics.*;
import fgk.zad1.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class OrthogonalCamera extends Camera{
    public OrthogonalCamera(Image image) {
        super(image);

    }
    public void  ortogonalShoot(Image image, GraphicsObject graphicsObject,  int color1) throws IOException {
        Color color=image.getColor();
        int width=image.getWidth();
        int heigth= image.getHeight();
        float pixelWidth=2.0f/width;
        float pixelHeigth=2.0f/heigth;
        float srodekX;
        float srodekY;
        BufferedImage bfimg=image.getBufferedImage();
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < heigth; j++) {
                srodekX = -1.0f + (i + 0.5f) * pixelWidth ;
                srodekY = 1.0f - (j + 0.5f) * pixelHeigth;
                Ray ray = new Ray(new Vector3(srodekX, srodekY, 0),new Vector3(0, 0, 1) );
                Vector3 intersetion = graphicsObject.checkSection(ray);

            if (intersetion != null)
             {
                 bfimg.setRGB(i, j,color1 );
                 System.out.println("dzialaaaaaaaaa");
             }
             else{

                bfimg.setRGB(i, j, color.getRGB());
            }

             }
        }

        File file = new File("CameraOrtogonalna.jpg");
        ImageIO.write((bfimg), "jpg", file);



    }
}

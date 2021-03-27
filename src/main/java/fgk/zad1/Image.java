package fgk.zad1;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class Image {
    Color color;
    int height;
    int width;
    BufferedImage bufferedImage;
    Image(LightIntensity lightIntensity, int width, int height)
    {
       color = new Color((int)(lightIntensity.getR()*255), (int)(lightIntensity.getG()*255), (int)(lightIntensity.getB()*255));

        bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        for(int  i =0;i<width;i++)
        {
            for(int  j =0;i<width;i++)
            {
                bufferedImage.setRGB(i,j,color.getRGB());
            }
        }
    }
}

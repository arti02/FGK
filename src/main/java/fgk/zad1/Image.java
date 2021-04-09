package fgk.zad1;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    Color color;
    int height;
    int width;
    BufferedImage bufferedImage;
    LightIntensity lightIntensity;



    /**konstructor obrazu
     *
     * @param lightIntensity
     * @param width
     * @param height
     */
   public Image(LightIntensity lightIntensity, int width, int height) throws IOException {
       this.height=height;
       this.width=width;
       this.lightIntensity=lightIntensity;
       color = new Color((int)(lightIntensity.getR()*255), (int)(lightIntensity.getG()*255), (int)(lightIntensity.getB()*255));
        System.out.println(color);
        bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        for(int  i =0;i<width;i++)
        {
            for(int  j =0;j<height;j++)
            {
                bufferedImage.setRGB(i,j,color.getRGB());
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }
    public Color getColor() {
        return color;
    }
    public LightIntensity getLightIntensity() {
        return lightIntensity;
    }

    public void setLightIntensity(LightIntensity lightIntensity) {
        this.lightIntensity = lightIntensity;
    }
}

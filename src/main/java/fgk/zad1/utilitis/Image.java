package fgk.zad1.utilitis;
import fgk.zad1.main.Driver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public BufferedImage buffer;
    public File image;

    /**Konstruktor
     *
     * @param filename
     */
    public Image(String filename){
        //imie obrazka
        image =new File(filename);
        //Tworzenie obrazka na ktorym rysujemy
        buffer=new BufferedImage(Driver.world.viewPlane.width,Driver.world.viewPlane.heigth,BufferedImage.TYPE_INT_RGB);
    }

    /**Metoda ktora Zapisuje nasz obrazek
     *
     * @param filetype
     * @throws IOException
     */
    public void write(String filetype) throws IOException {
        ImageIO.write(buffer,filetype,image);
    }

}

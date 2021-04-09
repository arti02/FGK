package fgk.zad1.Cameras;

import fgk.zad1.Basics.Ray;
import fgk.zad1.Basics.Sphere;
import fgk.zad1.Basics.Vector3;
import fgk.zad1.Image;
import fgk.zad1.Utilitis.ColorM;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PerspectivalCamera extends Camera {

    public PerspectivalCamera(Image image, Vector3 eye,Vector3 lookat,double FOV) {
        super(image);
        this.eye=eye;
        this.lookat=lookat;
        this.distance=(double) image.getHeight()/2/Math.tan((Math.toRadians(FOV)));
        compute_uvw();
    }
    public void ortogonalShoot(Image image, Sphere sphere) throws Exception {
        int width = image.getWidth();
        int heigth = image.getHeight();
//        float pixelWidth = 2.0f / width;
//        float pixelHeigth = 2.0f / heigth;
        float srodekX;
        float srodekY;
        Random random=new Random();
        BufferedImage bfimg = image.getBufferedImage();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                //dla kazdego piksela jest tworzony swoj kolor
                ColorM color=new ColorM(0,0,0);

                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        //Color tla
                        ColorM tempColor=  new ColorM((float) image.getLightIntensity().getR(),
                                (float) image.getLightIntensity().getG(),(float) image.getLightIntensity().getB());
                        //Dzielimy pixel na 8 wierszy i 8 kolumn i sprawdzamy color prszesuwajac sie randomowo
//                        srodekX= (float) (0.25*(i-width/2+(col+random.nextFloat())/8));
//                        srodekY= (float) (0.25*(j-heigth/2+(row+random.nextFloat())/8));
                        Ray ray = new Ray(this.eye, this.u.multByK(i).vecAdd(this.v.multByK(j)).vecSub(w.multByK((float) distance)));
                        Vector3 intersetion = sphere.checkSection(ray);
                        if (intersetion != null) {
                            //doajemy 1 do naszego kolora jak jest przecinanie
                            color.add(sphere.getColor());
                        }else {
                            // jak niema przecinania z obiektem dodajemy kolor tla
                            color.add(tempColor);
                        }

                    }
                }
                //w koncu dzielimy na liczbe probek
                color.divide(64);
                bfimg.setRGB(i, j, (color.toIntneger()));
            }
        }


        File file = new File("CameraPrespektywiczna.png");
        ImageIO.write((bfimg),"png",file);
    }

//   public Ray createRay(Vector3)
}

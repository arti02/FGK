package fgk.zad1.main;

import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.basics.Vector3;
import fgk.zad1.cameras.Camera;
import fgk.zad1.cameras.OrthogonalCamera;
import fgk.zad1.cameras.PerspectivalCamera;
import fgk.zad1.antyaliasing.TeachSample;
import fgk.zad1.antyaliasing.Sampler;
import fgk.zad1.scene.World;
import fgk.zad1.utilitis.Image;

public class Driver {

    public static World world;
    public static Image image;
    public static Tracer tracer;
    public static Sampler sampler;
    public static Camera camera;

    public static void main(String[] args) throws Exception {
        world=new World(1600,800,1);
        image=new Image("MyImage.png");
        tracer= new Tracer();
        sampler = new AdaptiveSampler(0.05f,0.05f,0.05f,100,100);
         //sampler= new TeachSample(10);
        /**Liczba probek
         *
         */
        //sampler=new AdaptiveSampler(0.05f);
        /**Definicja  camery
         */
       //camera=new OrthogonalCamera();
      camera=new PerspectivalCamera(new Vector3(0,0,600),new Vector3(0,0,0),30);
        for (int x = 0; x <world.viewPlane.width; x++) {
            for (int y = 0; y < world.viewPlane.heigth; y++) {
              //tracer.traceBruteForce(x,y);
               tracer.adaptiveTrace(x,y);
            }
        }
            image.write("png");
        }

    }


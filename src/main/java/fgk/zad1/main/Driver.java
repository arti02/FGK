package fgk.zad1.main;

import fgk.zad1.Lights.PointSource;
import fgk.zad1.Lights.Source;
import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.antyaliasing.RegularSample;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Triangle;
import fgk.zad1.basics.Vector3;
import fgk.zad1.cameras.Camera;
import fgk.zad1.cameras.OrthogonalCamera;
import fgk.zad1.cameras.PerspectivalCamera;
import fgk.zad1.antyaliasing.TeachSample;
import fgk.zad1.antyaliasing.Sampler;
import fgk.zad1.obj.ObjReader;
import fgk.zad1.scene.World;
import fgk.zad1.utilitis.Image;
import fgk.zad1.utilitis.Lightintencity;

import java.util.ArrayList;
import java.util.List;

import static fgk.zad1.obj.ObjReader.readFile;

public class Driver {

    public static World world;
    public static Image image;
    public static Tracer tracer;
    public static Sampler sampler;
    public static Camera camera;
    public static List<Source> listOfSources = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        world=new World(1600,800,1);
        image=new Image("MyImage12.png");
        tracer= new Tracer();
    //    sampler = new AdaptiveSampler(0.05f,0.05f,0.05f,100,100);
          sampler= new RegularSample(1);
        //camera=new OrthogonalCamera();
      camera=new PerspectivalCamera(new Vector3(0,0,400),new Vector3(0,0,0),60);

      listOfSources.add(new PointSource(new Vector3(0,100,200),new Lightintencity(0.5f,0.5f,0.5f)));

        listOfSources.add(new PointSource(new Vector3(0,-200,-200),new Lightintencity(1,1,1)));

        for (int x = 0; x <world.viewPlane.width; x++) {
            for (int y = 0; y < world.viewPlane.heigth; y++) {
              tracer.traceBruteForce(x,y,listOfSources);
              // tracer.adaptiveTrace(x,y);
            }
        }
            image.write("png");
        }




//            ObjReader.readFile("exampleScene.obj");
    }





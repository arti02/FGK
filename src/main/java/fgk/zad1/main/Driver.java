package fgk.zad1.main;

import fgk.zad1.Lights.PointSource;
import fgk.zad1.Lights.Source;
import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.antyaliasing.RegularSample;
import fgk.zad1.basics.*;
import fgk.zad1.cameras.Camera;
import fgk.zad1.cameras.OrthogonalCamera;
import fgk.zad1.cameras.PerspectivalCamera;
import fgk.zad1.antyaliasing.TeachSample;
import fgk.zad1.antyaliasing.Sampler;
import fgk.zad1.material.Material;
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


        world = new World(1600, 800, 1);
        image = new Image("MyImage15.png");
        tracer = new Tracer();
        //    sampler = new AdaptiveSampler(0.05f,0.05f,0.05f,100,100);
        sampler = new RegularSample(1);
      camera=new OrthogonalCamera();
   //   camera = new PerspectivalCamera(new Vector3(0, 0, 450), new Vector3(0, 0, -1), 60);

        //listOfSources.add(new PointSource(new Vector3(1000,100,100),new Lightintencity(0.5f,0.5f,0.5f),2f));
          listOfSources.add(new PointSource(new Vector3(300,200,600),new Lightintencity(0.5f,0.5f,0.5f),300f));
        listOfSources.add(new PointSource(new Vector3(-300,150,600),new Lightintencity(0.5f,0.5f,0.5f),300f));
       for (int x = 0; x <world.viewPlane.width; x++) {
            for (int y = 0; y < world.viewPlane.heigth; y++) {
              tracer.traceBruteForce(x,y,listOfSources);
            }
        }
            image.write("png");
        }

//            ObjReader.readFile("exampleScene.obj");
    }






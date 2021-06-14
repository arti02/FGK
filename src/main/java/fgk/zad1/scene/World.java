package fgk.zad1.scene;


import fgk.zad1.Lights.PointSource;
import fgk.zad1.Lights.Source;
import fgk.zad1.basics.*;
import fgk.zad1.main.Driver;
import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

import javax.security.auth.callback.TextInputCallback;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static fgk.zad1.obj.ObjReader.readFile;

public class World {
    /**Definicja swiata i wszystkich obiektów
     */
    public ViewPlane viewPlane;
    public List<GraphicsObject> objects;
    public Lightintencity background;
    public List<Source> lights;
    public World(int width,int heigth, double sizeOfPixel) throws IOException {
        viewPlane=new ViewPlane(width,heigth, sizeOfPixel);
        /**kolor tla
         *
         */
        background= new Lightintencity(1f,0f,0f);
        /**List który przechowywuje wszystkie obiekty
         *
         */
        lights = new ArrayList<>();
        objects=new ArrayList<GraphicsObject>();

        /**Wczytywanie pliku z OBJ
         *
         */
//      Mesh mesh=readFile("Sfere1.obj");
//        List<Triangle> a=mesh.getList();
////        /**Skalowanie
////         *
//////         */
//        for (Triangle n:a){
//            System.out.println(n.toString());
//            n.skale(30);
//        }
//        objects.addAll(a);
//   objects.add(new Surface(new Vector3(0,0,0),new Vector3(0,1,0),new Lightintencity(1,1,0)));
     //   objects.add(new Sphere(new Vector3(100,0,100),50,new Lightintencity(0f,0f,1f),
     //           new Material(new Vector3(0.6f,01.f,0.6f),new Vector3(1f,0.6f,0.6f),new Vector3(0.6f,01.f,0.6f), 5f)));
      //  objects.add(new Sphere(new Vector3(-100,0,100),50,new Lightintencity(1f,0f,0),
       //       new Material(new Vector3(0.6f,01.f,0.6f),new Vector3(1f,0.6f,0.6f),
              //        new Vector3(0.6f,01.f,0.6f),5f,true,false)));
       // objects.add(new Sphere(new Vector3(-100,0,300),50,new Lightintencity(0f,1f,0),
       //      new Material(new Vector3(0.6f,01.f,0.6f),new Vector3(1f,0.6f,0.6f),
     //              new Vector3(0.6f,01.f,0.6f),5f,false,false)));

   //  objects.add(new Sphere(new Vector3(-200,0,300),200,new Lightintencity(1f,1f,0.3f),s
    // //           new Material(new Vector3(0f,0.f,0f),new Vector3(1f,1f,1f),
    //                    new Vector3(0.4f,0.4f,0.4f),5f,trues,false)));



        objects.add(new Sphere(new Vector3(350,0,150),159,new Lightintencity(1f,1f,0f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),6f,false,true)));
        objects.add(new Sphere(new Vector3(-100,100,300),50,new Lightintencity(1f,0f,0f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),1f,true,false)));
        objects.add(new Sphere(new Vector3(-400,000,100),100,new Lightintencity(1f,0f,0f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),4f,false,true)));
        objects.add(new Surface(new Vector3(400,200,-100), new Vector3(0,0,1),new Lightintencity(1f,1f,1f),
               new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                       new Vector3(0.4f,0.4f,0.4f),5f,false,false)));
        objects.add(new Surface(new Vector3(0,-200,-100), new Vector3(0,1,1),new Lightintencity(1f,1f,0f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),5f,false,false)));
        objects.add(new Surface(new Vector3(0,200,-100), new Vector3(0,-1,1),new Lightintencity(0f,1f,1f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),5f,false,false)));
        objects.add(new Surface(new Vector3(-600,0,-100), new Vector3(1,0,1),new Lightintencity(1f,0f,1f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),5f,false,false)));
        objects.add(new Surface(new Vector3(600,0,-100), new Vector3(-1,0,1),new Lightintencity(0.6f,0.2f,1f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),5f,false,false)));
        objects.add(new Surface(new Vector3(0,0,600), new Vector3(0,0,-1),new Lightintencity(0f,1f,0f),
                new Material(new Vector3(0.4f,0.4f,0.4f),new Vector3(0.4f,0.4f,0.4f),
                        new Vector3(0.4f,0.4f,0.4f),5f,false,false)));






    }

}

package fgk.zad1.scene;


import fgk.zad1.basics.GraphicsObject;
import fgk.zad1.basics.Sphere;
import fgk.zad1.basics.Surface;
import fgk.zad1.basics.Vector3;
import fgk.zad1.utilitis.Lightintencity;

import java.util.ArrayList;
import java.util.List;

public class World {
    /**Definicja swiata i wszystkich obiektów
     */
    public ViewPlane viewPlane;
    public List<GraphicsObject> objects;
    public Lightintencity background;
    public World(int width,int heigth, double sizeOfPixel){
        viewPlane=new ViewPlane(width,heigth, sizeOfPixel);
        /**kolor tla
         *
         */
        background= new Lightintencity(0.7f,0,0.4f);
        /**List który przechowywuje wszystkie obiekty
         *
         */
        objects=new ArrayList<GraphicsObject>();
        objects.add(new Sphere(new Vector3(200,0,170),50,new Lightintencity(1f,0,0)));
        objects.add(new Sphere(new Vector3(0,0,0),200,new Lightintencity(0f,1f,0)));
//        objects.add(new Surface(new Vector3(0,0,0),new Vector3(0,1,0.2f),new Lightintencity(1,1,0)));
//        objects.add(new Sphere(new Vector3(-200,0,0),50,new Lightintencity(0f,0f,1f)));
    }
}

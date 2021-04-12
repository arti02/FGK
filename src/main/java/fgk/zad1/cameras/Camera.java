package fgk.zad1.cameras;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.utilitis.Image;

import java.awt.*;


public abstract class Camera {

    public abstract Ray createRay(Vector2 vector);


    /** pozycja  położenie obserwatora w układzie xyz;
     *
     */
    public Vector3 eye;
    /**Kierunek patrzenia kamery
     *
     */
    public Vector3 lookat;
    /**odleglosc do plaszyzny rzutowania
     *
     */
    public double distance;
    /**koordynaty camery
     *
     */
    public Vector3 u,v,w;

    /**Obliczenie i normalizacja koordynat camery
     *
     */
    public void compute_uvw(){
        w=eye.vecSub(lookat);;
        w.normalize();

        //przyczepiony na wektorze kierunku patrzenia lookat, określa on orientację położenia
        //Vup - vup, wektor skierowany do góry kierunku obserwacji (jest to dowolny wektor)
        Vector3 up=new Vector3(0.00424f,1,0.00764f);
        u=up.vecCross(w);
        u.normalize();

        v=w.vecCross(u);
        v.normalize();

    }

}

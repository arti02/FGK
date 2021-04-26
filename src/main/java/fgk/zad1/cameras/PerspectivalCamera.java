package fgk.zad1.cameras;

import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.utilitis.Image;
import fgk.zad1.main.Driver;

import java.awt.*;
import java.util.Vector;

public class PerspectivalCamera extends Camera {
    /**Konstruktor kamery prespektywicznej
     *
     * @param eye
     * @param lookat
     * @param FOV
     */
    public PerspectivalCamera(Vector3 eye,Vector3 lookat,double FOV) {

        this.eye=eye;
        eye.setY(eye.getX()*-1);
        this.lookat=lookat;
        this.distance=(double) Driver.world.viewPlane.heigth/2/Math.tan((Math.toRadians(FOV)));
        compute_uvw();
    }

    /**Implemnetacja promienia dla kamery prespektywicznej
     *
     * @param vector
     * @return
     */

    public Ray createRay(Vector2 vector) {
        final Vector3 w1=new Vector3(w);
        final Vector3 u1=new Vector3(u);
        final Vector3 v1=new Vector3(v);
        Ray ray=new Ray(new Vector3(eye),(u1.multByK(vector.x).vecAdd(v1.multByK(vector.y).vecSub(w1.multByK((float) distance))))) ;
        ray.getDirection().normalize();
        return ray;
    }

}

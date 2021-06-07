package fgk.zad1.basics;

import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

import java.lang.Math;

public class Sphere implements GraphicsObject {
    /**
     * Wektor określający centrum
     */
    private final Vector3 center;
    /**
     * Odwołanie do materiału
     */
    Material material;
    /**
     * Color sfery
     */
    Lightintencity color;
    /**
     * Promień sfery
     */
    private final float radius;

    /**
     * Konstruktor sfery, argumenty pobierane to wektor określający środek sfery oraz promień sfery.
     */
    public Sphere(Vector3 center, float radius, Lightintencity color) {
        this.radius = radius;
        this.center = center;
        this.color = color;

    }

    public Sphere(Vector3 center, float radius, Lightintencity color, Material material) {
        this.radius = radius;
        this.center = center;
        this.color = color;
        this.material = material;
    }

    public Lightintencity getColor() {
        return color;
    }

    @Override
    public Vector3 getNormal(Vector3 vec) {

        Vector3 vector31=vec.vecSub(this.center);
        vector31.normalize();
        return vector31;
    }


    @Override
    public Material getMaterial() {
        return material;
    }

    public void setColor(Lightintencity color) {
        this.color = color;
    }

    /*
    Sprawdzanie czy promień o wektorze źródła równym
    origin oraz wektorze kierunkowym równym direction
    przecina  sferę przez którą jest wywoływana metoda.
     */
    public Vector3[] checkSection(Vector3 origin, Vector3 direction) {
        Vector3 oMinusC = center.vecSub(origin);
        float A = direction.scalProd(direction);
        float B = direction.scalProd(oMinusC) * 2;
        float C = oMinusC.scalProd(oMinusC) - (radius * radius);
        float Delta = B * B - 4 * A * C;
        //t = (-B + sqrt)/A

        if (Delta < 0) {
            return null;
        } else if (Delta == 0) {
            Vector3[] vector = new Vector3[1];
            float t = (-1) * B / (2 * A);
            vector[0] = new Vector3(origin.getX() + direction.getX() * t, origin.getY() + direction.getY() * t, origin.getZ() + direction.getZ() * t);
            return vector;
        } else {
            Vector3[] vector = new Vector3[2];
            float[] result = new float[2];
            float res1 = (float) (((-1) * B + Math.sqrt(Delta)) / (2 * A));
            float res2 = (float) (((-1) * B - Math.sqrt(Delta)) / (2 * A));
            vector[0] = new Vector3(origin.getX() + direction.getX() * res1, origin.getY() + direction.getY() * res1, origin.getZ() + direction.getZ() * res1);
            vector[1] = new Vector3(origin.getX() + direction.getX() * res2, origin.getY() + direction.getY() * res2, origin.getZ() + direction.getZ() * res2);
            return vector;
        }
    }

    /*
    Sprawdzanie czy promień
    przecina  sferę przez którą jest wywoływana metoda.
     */
    @Override
    public Vector3 checkSectionReturnVector(Ray ray) {
        Vector3 origin = ray.getOrigin();
        Vector3 direction = ray.getDirection();
        Vector3 oMinusC = origin.vecSub(center);
        float A = direction.scalProd(direction);
        float B = 2 * oMinusC.scalProd(direction);
        float C = (oMinusC.scalProd(oMinusC)) - radius * radius;
        float Delta = B * B - 4 * A * C;
        //t = (-B + sqrt)/A

        if (Delta < 0) {
            return null;
        } else if (Delta == 0) {
            Vector3[] vector = new Vector3[1];
            float t = (float) ((-B - Math.sqrt(Delta)) / (2 * A));
            vector[0] = new Vector3(origin.getX() + direction.getX() * t, origin.getY() + direction.getY() * t, origin.getZ() + direction.getZ() * t);
            return vector[0];
        } else {
            Vector3[] vector = new Vector3[2];
            float[] result = new float[2];
            float res1 = (float) (- B + Math.sqrt(Delta)) / (2 * A);
            float res2 = (float)   (-B - Math.sqrt(Delta)) / (2 * A);
            vector[0] = new Vector3(origin.getX() + direction.getX() * res1, origin.getY() + direction.getY() * res1, origin.getZ() + direction.getZ() * res1);
            vector[1] = new Vector3(origin.getX() + direction.getX() * res2, origin.getY() + direction.getY() * res2, origin.getZ() + direction.getZ() * res2);


            return vector[1];

        }
    }


    /**
     * Sprawdzanie czy promień
     * przecina  sferę przez którą jest wywoływana metoda.
     *
     * @param ray
     * @return
     */
    public double checkSection(Ray ray) {
        Vector3 origin = ray.getOrigin();
        Vector3 direction = ray.getDirection();
        Vector3 oMinusC = origin.vecSub(center);
        float A = direction.scalProd(direction);
        float B = 2 * oMinusC.scalProd(direction);
        float C = (oMinusC.scalProd(oMinusC)) - radius * radius;
        float Delta = B * B - 4 * A * C;
        //t = (-B + sqrt)/A
        if (Delta < 0) {
            return 0;
        } else {
            return (-B - Math.sqrt(Delta)) / (2 * A);
        }


    }
}

package fgk.zad1.basics;

import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

public class Surface implements GraphicsObject {
    /**Wektor normalny plasczyzny
     */
    private Vector3 normalPlaneVector;
    /**Punkt na plasczyznie
     */
    private Vector3 point;
    /**Color swiatla
     */
    private Lightintencity color;

    /**Kostruktor Plaszczyzny
     * @param normalPlaneVector
     * @param point
     * @param color
     */
    public Surface(Vector3 point,Vector3 normalPlaneVector,  Lightintencity color) {
        this.point=point;
        this.normalPlaneVector = normalPlaneVector;
        normalPlaneVector.normalize();
        this.color = color;
    }

    @Override
    public String toString() {
        return "Surface{" +
                "normalPlaneVector=" + normalPlaneVector +
                ", point=" + point +
                ", color=" + color +
                '}';
    }

    /**Kostruktor Plaszczyzny
     * @param normalPlaneVector
     * @param point
     */
    public Surface(Vector3 point,Vector3 normalPlaneVector) {
        this.point=point;
        this.normalPlaneVector = normalPlaneVector;
        normalPlaneVector.normalize();

    }
    public void setNormalPlaneVector(Vector3 normalPlaneVector) {
        this.normalPlaneVector = normalPlaneVector;
    }

    public Lightintencity getColor() {
        return color;
    }

    @Override
    public Vector3 getNormal(Vector3 vector3) {
        return normalPlaneVector;
    }

    @Override
    public Material getMaterial() {
        return null;
    }

    public void setColor(Lightintencity color) {
        this.color = color;
    }

    @Override
    public Vector3 checkSectionReturnVector(Ray ray) {
        return null;
    }

    /**
    Sprawdzanie czy promień
    przecina  powierzchnię przez którą jest wywoływana metoda.
     */

    public double checkSection(Ray ray) {
        return point.vecSub(ray.getOrigin()).scalProd(normalPlaneVector)/ray.getDirection().scalProd(normalPlaneVector);
    }
    public Vector3 getPoint() {
        return point;
    }

    public void setPoint(Vector3 point) {
        this.point = point;
    }

    public Vector3 getNormalPlaneVector() {
        return normalPlaneVector;
    }
//    /**
//    Sprawdzanie czy promień o wektorze źródła równym
//    origin oraz wektorze kierunkowym równym direction
//    przecina  powierzchnię przez którą jest wywoływana metoda.
//    */
//    public Vector3 checkSection(Vector3 origin, Vector3 direction) {
//        float nDotV = this.normalPlaneVector.scalProd(direction);
//        if (nDotV == 0) {
//            return null;
//        } else {
//            float nDotXr = this.normalPlaneVector.scalProd(origin);
//            float t = (this.getD() * (-1) - nDotXr) / nDotV;
//            if (t > 0) {
//                float xo = origin.getX();
//                float yo = origin.getY();
//                float zo = origin.getZ();
//                float xd = direction.getX();
//                float yd = direction.getY();
//                float zd = direction.getZ();
//                return new Vector3(xo + t * xd, yo + t * yd, zo + t * zd);
//            } else {
//                return null;
//            }
//        }
//    }
}


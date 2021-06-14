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
    private Material material;
    /**Kostruktor Plaszczyzny
     * @param normalPlaneVector
     * @param point
     * @param color
     */
    public Surface(Vector3 point,Vector3 normalPlaneVector,  Lightintencity color, Material material) {
        this.material =material;
        this.point=point;
        this.normalPlaneVector = normalPlaneVector;
        this.normalPlaneVector.normalize();
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
        this.normalPlaneVector.normalize();

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
        return material;
    }

    public void setColor(Lightintencity color) {
        this.color = color;
    }



    /**
    Sprawdzanie czy promień
    przecina  powierzchnię przez którą jest wywoływana metoda.
     */

    public float checkSection(Ray ray) {
        double xd  =(this.getNormalPlaneVector().scalProd(this.point)-(this.getNormalPlaneVector().scalProd(ray.getOrigin())))/
                (this.getNormalPlaneVector().scalProd(ray.getDirection())); //   double xd =point.vecSub(ray.getOrigin()).scalProd(normalPlaneVector)/ray.getDirection().scalProd(normalPlaneVector);
        if(xd>0)
        {
            return (float)xd;
        }
        else
            return 0;
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

    @Override // OKI
    public Vector3 checkSectionReturnVector(Ray ray) {
        double d = this.checkSection(ray);

            return ray.getOrigin().vecAdd(new Vector3(ray.getDirection().scalMulti((float)d)));

    }

    @Override
    public Vector3 checkSectionReturnVectorTransparent(Ray ray) {
        double d = this.checkSection(ray);
        if(d!=0)
        {
            return ray.getOrigin().vecAdd(new Vector3(ray.getDirection().scalMulti((float)d)));
        }
        else
        {
            return  null;
        }
    }
}


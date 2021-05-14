package fgk.zad1.basics;

import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

public class Triangle implements GraphicsObject {
    Vector3 vertex1, vertex2, vertex3, N;
    Lightintencity color;
    Material material;


    @Override
    public String toString() {
        return "Triangle{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                ", vertex3=" + vertex3 +
                ", N=" + N +
                ", color=" + color +
                ", material=" + material +
                '}';
    }

    public Triangle(Vector3 vertex1, Vector3 vertex2, Vector3 vertex3, Vector3 n, Lightintencity color, Material material) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        N = n;
        this.color = color;
        this.material=material;
    }

    public Triangle(Vector3 vertex1, Vector3 vertex2, Vector3 vertex3, Lightintencity lightintencity) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.vertex3 = vertex3;
        Vector3 AB = vertex2.vecSub(vertex1);
        Vector3 AC = vertex3.vecSub(vertex1);
        this.N = AB.vecCross(AC);
        this.color = lightintencity;
    }

    /**Skalowanie trojkata
     *
     * @param k
     * @return
     */
    public Triangle skale(int k){
        getVertex1().multByK(k);
        getVertex2().multByK(k);
        getVertex3().multByK(k);
        return this;
    }
    @Override
    public double checkSection(Ray ray) {

        Vector3 originOfRay = ray.getOrigin();
        Vector3 diretcionOfRay =ray.getDirection();
                //Szukanie płaaszczyzny
        Surface surface = new Surface(vertex1,N);
//        System.out.println(surface);
       double d =  surface.checkSection(ray);
        if(d>0) {
            Vector3 X = new Vector3(
                    (float) (originOfRay.getX() + d * diretcionOfRay.getX()),
                    (float) (originOfRay.getY() + d * diretcionOfRay.getY()),
                    (float) (originOfRay.getZ() + d * diretcionOfRay.getZ())
            );
            Vector3 XA = vertex1.vecSub(X);
            Vector3 XB = vertex2.vecSub(X);
            Vector3 XC = vertex3.vecSub(X);

            Vector3 XAxXB = XA.vecCross(XB);
            Vector3 XCxXA = XC.vecCross(XA);
            Vector3 XBxXC = XB.vecCross(XC);

            if(XAxXB .scalProd(N)>0)
            {
                if(XCxXA.scalProd(N)>=0)
                {
                    if(XBxXC.scalProd(N)>=0)
                    {
                        return d;
                    }
                }
            }
        }
        return  0;

    }
    public Vector3 checkSectionReturnVector(Ray ray) {

        Vector3 originOfRay = ray.getOrigin();
        Vector3 diretcionOfRay = ray.getDirection();
        //Szukanie płaaszczyzny
        Surface surface = new Surface(vertex1,N);
        double d =  surface.checkSection(ray);
        if(d>0) {
            Vector3 X = new Vector3(
                    (float) (originOfRay.getX() + d * diretcionOfRay.getX()),
                    (float) (originOfRay.getY() + d * diretcionOfRay.getY()),
                    (float) (originOfRay.getZ() + d * diretcionOfRay.getZ())
            );
            Vector3 XA = vertex1.vecSub(X);
            Vector3 XB = vertex2.vecSub(X);
            Vector3 XC = vertex3.vecSub(X);

            Vector3 XAxXB = XA.vecCross(XB);
            Vector3 XCxXA = XC.vecCross(XA);
            Vector3 XBxXC = XB.vecCross(XC);
            if(XAxXB .scalProd(N)>=0)
            {
                if(XCxXA.scalProd(N)>=0)
                {
                    if(XBxXC.scalProd(N)>=0)
                    {
                        return X;
                    }
                }
            }

        }
        return  null;
        // P = 1/2*(ABxAC)
        // powierzchnia p(alfa,beta,gama) = a*alfa + beta*b + gama*c
        //alfa = Aa/A;
        //alfa +beta + gama =1
    }
    @Override
    public Lightintencity getColor() {
        return color;
    }

    public Vector3 getVertex1() {
        return vertex1;
    }

    public void setVertex1(Vector3 vertex1) {
        this.vertex1 = vertex1;
    }

    public Vector3 getVertex2() {
        return vertex2;
    }

    public void setVertex2(Vector3 vertex2) {
        this.vertex2 = vertex2;
    }

    public Vector3 getVertex3() {
        return vertex3;
    }

    public void setVertex3(Vector3 vertex3) {
        this.vertex3 = vertex3;
    }

    public Vector3 getN() {
        return N;
    }

    public void setN(Vector3 n) {
        N = n;
    }
}

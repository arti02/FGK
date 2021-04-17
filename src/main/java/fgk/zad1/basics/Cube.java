package fgk.zad1.basics;

import fgk.zad1.utilitis.Lightintencity;

public class Cube implements GraphicsObject{
    Vector3 point;
    float lengthOfEdge;
    @Override
    public double checkSection(Ray ray) {
        return 0;
    }

    @Override
    public Lightintencity getColor() {
        return null;
    }
}

package fgk.zad1.basics;

import fgk.zad1.utilitis.Lightintencity;

public interface GraphicsObject {
    public double checkSection(Ray ray);
    public Lightintencity getColor();
}

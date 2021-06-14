package fgk.zad1.basics;

import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

public interface GraphicsObject {
    public Vector3 checkSectionReturnVector(Ray ray);
   Vector3 checkSectionReturnVectorTransparent(Ray ray);
    public float checkSection(Ray ray);
    public Lightintencity getColor();
    public Vector3 getNormal(Vector3 vec);
    public Material getMaterial();
}

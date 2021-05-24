package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Vector2;
import fgk.zad1.utilitis.Lightintencity;

public abstract class Sampler {
    /**Liczba probek
     */
    public float samples;
    public abstract Lightintencity sample(int x, int y);
    public abstract Vector2 sampleNormal(int row,int col,int x,int y);
}

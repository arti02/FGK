package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Vector2;
import fgk.zad1.utilitis.Lightintencity;

public abstract class Sampler {
    /**Liczba probek
     */
    public int samples;
    public abstract Lightintencity sample(int x, int y);
}

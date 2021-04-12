package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Vector2;

public abstract class Sampler {
    /**Liczba probek
     */
    public int samples;
    public abstract Vector2 sample(int row,int col,int x,int y);
}

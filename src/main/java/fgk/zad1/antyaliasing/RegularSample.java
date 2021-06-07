package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Vector2;
import fgk.zad1.main.Driver;
import fgk.zad1.utilitis.Lightintencity;

import java.awt.geom.Point2D;

public class RegularSample extends Sampler {

    public RegularSample(int samples){
        this.samples=samples;
    }
    @Override
    public Lightintencity sample(int x, int y) {
        return null;
    }
    public Vector2 sampleNormal(int row,int col,int x,int y){
        Vector2 vector2=new Vector2();
        vector2.x= (float) (x- Driver.world.viewPlane.width*0.5+(row+0.5)/samples);
        vector2.y= (float) (y- Driver.world.viewPlane.heigth*0.5+(col+0.5)/samples);
        return vector2;
    }
}

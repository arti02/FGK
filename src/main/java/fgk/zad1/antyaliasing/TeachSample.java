package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Vector2;
import fgk.zad1.main.Driver;

import java.util.Random;

public class TeachSample extends Sampler {


    public TeachSample(int samples){
        this.samples=samples;
    }

    /**Implementacja antyaliasingu
     *
     * @param row
     * @param col
     * @param x
     * @param y
     * @return
     */
    public Vector2 sample(int row, int col, int x, int y) {
        Random random=new Random();
        Vector2 vector2=new Vector2();
        vector2.x= (float) (x - Driver.world.viewPlane.width *0.5 + (row + random.nextFloat()) / 8);
        vector2.y= (float) (y - Driver.world.viewPlane.heigth *0.5 + (col + random.nextFloat()) / 8);
        return vector2;
    }
}

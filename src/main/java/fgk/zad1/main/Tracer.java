package fgk.zad1.main;

import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.utilitis.Lightintencity;

public class Tracer {

    /**Ray tracing brute Force
     *
     * @param x
     * @param y
     */
    public void traceBruteForce(int x, int y) {
        boolean hit=false;
        //Poczatkowy kolor
        Lightintencity color = new Lightintencity(0f, 0f, 0f);
        /** Antyaliasing dzielimy nasz pixel na 8 wiersz i 8 kolumn
         *
         */
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                Lightintencity lightintencity = Driver.sampler.sample(x,y);
                if(!lightintencity.equals(Driver.world.background))
                {
                    hit = true;
                }
                color.add(lightintencity);
            }
        }
        //dzielimy nasz kolor przez liczbe probek
        color.divide(Driver.sampler.samples*Driver.sampler.samples);
        if (hit){
            Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth - y -1, color.toIntneger());
        }else {
            Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth-y-1 ,Driver.world.background.toIntneger());
        }


    }

    public void adaptiveTrace(int x, int y) {

        Driver.image.buffer.setRGB(x,y, Driver.sampler.sample(x,y).toIntneger());
    }


}




package fgk.zad1.antyaliasing;

import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.main.Driver;
import fgk.zad1.utilitis.Lightintencity;

import java.util.Random;

public class TeachSample extends Sampler {


    public TeachSample(int samples){
        this.samples=samples;
    }

    public Lightintencity sampleNew(int x, int y,int row , int col) {
        Random random=new Random();
        Vector2 vector2=new Vector2();
        vector2.x= (float) (x - Driver.world.viewPlane.width *0.5 + (row +0.5 ) / samples);
        vector2.y= (float) (y - Driver.world.viewPlane.heigth *0.5 + (col + 0.5) / samples);
        //Jest tworzony ray z wybranej kamery
        Ray ray = Driver.camera.createRay(vector2);
        //Kolor tla
        Lightintencity tempColor=Driver.world.background;
        double min=Double.MAX_VALUE;
        //Petla ktora sprawdza wszystkie obiekty na przeciencie z rayjem
        for (int i = 0; i < Driver.world.objects.size(); i++) {
            double temp=Driver.world.objects.get(i).checkSection(ray);
            if (temp!= 0&&temp<min) {
                tempColor = Driver.world.objects.get(i).getColor();
                min=temp;
            }
        }
        return tempColor;
    }

    /**Implementacja antyaliasingu
     *

     * @param x
     * @param y
     * @return
     */

    public Lightintencity sample(int x, int y) {
        int col = 8;
        int row = 8;
        Random random=new Random();
        Vector2 vector2=new Vector2();
        vector2.x= (float) (x - Driver.world.viewPlane.width *0.5 + (row + random.nextFloat()) / 8);
        vector2.y= (float) (y - Driver.world.viewPlane.heigth *0.5 + (col + random.nextFloat()) / 8);
        //Jest tworzony ray z wybranej kamery
        Ray ray = Driver.camera.createRay(vector2);
        //Kolor tla
        Lightintencity tempColor=Driver.world.background;
        double min=Double.MAX_VALUE;
        //Petla ktora sprawdza wszystkie obiekty na przeciencie z rayjem
        for (int i = 0; i < Driver.world.objects.size(); i++) {
            double temp=Driver.world.objects.get(i).checkSection(ray);
            if (temp!= 0&&temp<min) {
                tempColor = Driver.world.objects.get(i).getColor();
                min=temp;
            }
        }
        return tempColor;
    }

    @Override
    public Vector2 sampleNormal(int row, int col, int x, int y) {
        return null;
    }
}

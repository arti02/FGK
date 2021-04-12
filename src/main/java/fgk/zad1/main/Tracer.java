package fgk.zad1.main;

import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.utilitis.Lightintencity;

public class Tracer {

    /**Ray tracing
     *
     * @param x
     * @param y
     */
    public void trace(int x, int y) {
        boolean hit=false;
        //Poczatkowy kolor
        Lightintencity color = new Lightintencity(0f, 0f, 0f);
        /** Antyaliasing dzielimy nasz pixel na 8 wiersz i 8 kolumn
         *
         */
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                // Dwuwymiatowy wektor z liczba column i wierszy i kordynatami x y
                Vector2 vector2 = Driver.sampler.sample(row, col, x, y);
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
                        hit=true;
                        min=temp;
                    }

                }
                //Doadajemy do kolora kolor tla
                color.add(tempColor);
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
}



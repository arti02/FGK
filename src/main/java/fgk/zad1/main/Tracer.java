package fgk.zad1.main;

import fgk.zad1.Lights.Source;
import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.utilitis.Lightintencity;

import java.awt.*;

public class Tracer {

    Source source;
    /**Ray tracing brute Force
     *
     * @param x
     * @param y
     */
    public void traceBruteForce(int x, int y,Source source) throws Exception {
        //Poczatkowy kolor
        Lightintencity colorKoncowy;
        Lightintencity color = new Lightintencity(0f, 0f, 0f);
        /** Antyaliasing dzielimy nasz pixel na 8 wiersz i 8 kolumn
         *
         */
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                Vector2 vector2 = Driver.sampler.sampleNormal(row, col, x, y);
                Ray ray = Driver.camera.createRay(vector2);
                Lightintencity tempColor = Driver.world.background;
                double min = Double.MAX_VALUE;
                //Petla ktora sprawdza wszystkie obiekty na przeciencie z rayjem
                for (int i = 0; i < Driver.world.objects.size(); i++) {
                    double temp = Driver.world.objects.get(i).checkSection(ray);
                    Vector3 intersetion=Driver.world.objects.get(i).checkSectionReturnVector(ray);
                    if (temp != 0 && temp < min) {
                        float r, g, b, cosinus,specular;
                        Vector3 I = ray.getDirection();
                        I.normalize();
                        Vector3 N = Driver.world.objects.get(i).getNormal(intersetion);
                        Vector3 R = I.scalSub((N.vecMult(N).scalProd(I)*2.0f));
                       float ss = ray.getDirection().normalizeProduct().scalProd(R);
                        if (-ss > 0)
                        {
                            specular = (float) Math.pow(ss,100);
                        }
                        else
                        {
                            specular = 0;
                        }
                        specular *= Driver.world.objects.get(i).getMaterial().getSpecularCs().getX();
                        Lightintencity sIntensity = source.getLightintencity().multByK(specular);
                        // diffuse
                        cosinus = ray.getDirection().normalizeProduct().scalProd(N);
                        r = -source.getLightintencity().getR() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
                        g = -source.getLightintencity().getG() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
                        b = -source.getLightintencity().getB() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
                        Lightintencity diffuseIntensity = new Lightintencity(r, g, b);


                        min = temp;

//                        tempColor=Driver.world.objects.get(i).getColor();

                        tempColor = Driver.world.objects.get(i).getColor().addVec(sIntensity).addVec(diffuseIntensity);
                    }
                }

                color.add(tempColor);

//                if (color.getR()>0||color.getG()>0||color.getB()>0) {
//                    System.out.println(color + "***************");
//
//                }

            }
        }
//        System.out.println(color);
        //dzielimy nasz kolor przez liczbe probek
        color.divideByK(Driver.sampler.samples*Driver.sampler.samples);
        Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth - y -1, color.toIntneger());


    }

    public void adaptiveTrace(int x, int y) {
//
        Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth - y -1, Driver.sampler.sample(x,y).toIntneger());
    }


}




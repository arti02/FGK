package fgk.zad1.main;

import fgk.zad1.Lights.Source;
import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.basics.GraphicsObject;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

import java.awt.*;
import java.util.List;

public class Tracer {


    /**Ray tracing brute Force
     *
     * @param x
     * @param y
     */
    public void traceBruteForce(int x, int y,List<Source> sources) throws Exception {
        //Poczatkowy kolor

        Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth - y -1, calculateLight(x,y,sources).toIntneger());


    }

    public void adaptiveTrace(int x, int y) {
//
        Driver.image.buffer.setRGB(x,Driver.world.viewPlane.heigth - y -1, Driver.sampler.sample(x,y).toIntneger());
    }
    public Lightintencity calculateLight(int x, int y,List<Source> sources) throws Exception {
        Lightintencity color = new Lightintencity(0f, 0f, 0f);
        /** Antyaliasing dzielimy nasz pixel na 8 wiersz i 8 kolumn
         *
         */
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                Vector2 vector2 = Driver.sampler.sampleNormal(row,col, x, y);
                Ray ray = Driver.camera.createRay(vector2);
                Lightintencity tempColor = Driver.world.background;
                double min = Double.MAX_VALUE;
                for (int i = 0; i < Driver.world.objects.size(); i++) {
                    double temp = Driver.world.objects.get(i).checkSection(ray);

                    Vector3 intersetion =Driver.world.objects.get(i).checkSectionReturnVector(ray);

                    if (intersetion!=null){
                        for (Source source: sources
                        ) {
                            Vector3 lightDir = source.getSourcePoint().vecSub(intersetion);
                            lightDir.normalize();
                            Ray shadowRay = new Ray(intersetion,lightDir.scalMulti(-1));
                           GraphicsObject firstBumpedObject =  shadowRay.checkFirstIntersectObject();
                           if(firstBumpedObject!=null)
                           {
                               if(firstBumpedObject.equals(Driver.world.objects.get(i)))
                                {
                                    Material material =   Driver.world.objects.get(i).getMaterial();
                                    Vector3 normal = Driver.world.objects.get(i).getNormal(intersetion);
                                    Vector3 reflect = lightDir.reflect(normal);
                                    float diff = normal.scalProd(lightDir);
                                    if(diff<0) diff=0;
                                    float spec = (float) Math.pow(intersetion.scalProd(reflect),source.shiness);
                                    float distance = source.getSourcePoint().vecSub(intersetion).lengthOfVector();
                                    float attenuation = (float) ( 1/(10*distance + Math.pow(distance,2)));
                                    Vector3 result = material.getAmbientKa().scalMulti(attenuation).vecAdd(
                                            material.getDiffuseKd().scalMulti(diff)).vecAdd(
                                                 material.getSpecularKs().scalMulti(
                                                            spec*attenuation));
                                    color.add(new Lightintencity(result.getX()*Driver.world.objects.get(i).getColor().getR(),
                                            result.getY()*Driver.world.objects.get(i).getColor().getG(),
                                            result.getZ()*Driver.world.objects.get(i).getColor().getB()
                                    ));

                                    }
                                else
                                {
                                    color.add(new Lightintencity(0.01f*Driver.world.objects.get(i).getColor().getR(),
                                            0.01f*Driver.world.objects.get(i).getColor().getG(),
                                            0.01f*Driver.world.objects.get(i).getColor().getB()));
                                }
                           }
                        }
                    }
                }



            }
        }
        color.divideByK(Driver.sampler.samples*Driver.sampler.samples);
        return  color;
    }


}




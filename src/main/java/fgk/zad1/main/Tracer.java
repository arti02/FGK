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
                //Petla ktora sprawdza wszystkie obiekty na przeciencie z ray
                for (int i = 0; i < Driver.world.objects.size(); i++) {
                    double temp = Driver.world.objects.get(i).checkSection(ray);

                    Vector3 intersetion=Driver.world.objects.get(i).checkSectionReturnVector(ray);
                    if (intersetion!=null) {
                        System.out.println(intersetion);
                    }
//                    if (temp != 0 && temp < min) {
                    if (intersetion!=null){
//                        //System.out.println(wspzanikania);
//                        float r, g, b, cosinus;
//                        Vector3 I = Driver.camera.eye.vecSub(intersetion);
//                        I.normalize();
////
//                        Vector3 N = Driver.world.objects.get(i).getNormal(intersetion);
//                        N.normalize();
////
////                       Vector3 R = I.scalSub((N.vecMult(N).scalProd(I)*2.0f));
////                       float ss = I.scalProd(R);
//
//                        Vector3 wspzanikania1=intersetion.vecSub(source.getSourcePoint());
//                        wspzanikania1.normalize();
//                        float ss=I.scalProd(wspzanikania1);
//                         float specular;
//                        System.out.println(ss);
//                        if (-ss > 0)
//                        {
//                            specular = (float) Math.pow(ss,64);
//                        }
//                        else
//                        {
//                            specular = 0;
//                        }
//                        specular *= Driver.world.objects.get(i).getMaterial().getSpecularKs().getX();
//                        Lightintencity sIntensity = source.getLightintencity().multByK(specular);
//
//                        // diffuse------------------------------------------------------------
//                            Vector3 wspzanikania=source.getSourcePoint().vecSub(intersetion);
//                            wspzanikania.normalize();
//                        cosinus =wspzanikania.scalProd(N);
////                        System.out.println(cosinus);
//                       if (cosinus<0){
//                           cosinus=0;
//                       }
//                       if (cosinus>1){
//                           cosinus=1;
//                       }
//
//                        r =source.getLightintencity().getR() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
//                        g = source.getLightintencity().getG() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
//                        b = source.getLightintencity().getB() * Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX() * cosinus;
//                        Lightintencity diffuseIntensity = new Lightintencity(r, g, b);
                        //-----------------------------------
                        Vector3 lightDir = source.getSourcePoint().vecSub(intersetion);
                         float distance = lightDir.lengthOfVector();
                         lightDir.normalize(); // = normalize(lightDir);
                       distance = distance * distance; //This line may be optimised
                        //Intensity of the diffuse light. Saturate to keep within the 0-1 range.
                        Vector3 N = Driver.world.objects.get(i).getNormal(intersetion);
                        float NdotL = N.scalProd( lightDir);
//
//
                         Lightintencity diffuseIntensity=source.getLightintencity().multByK(NdotL).multByK(Driver.world.objects.get(i).getMaterial().getDiffuseKd().getX());
//                        //System.out.println(diffIntensity);

//---------------------------------------------------------------------------------------------------
                        //SPECULAR
                        //Calculate the half vector between the light vector and the view vector.
                        // This is typically slower than calculating the actual reflection vector
                        // due to the normalize function's reciprocal square root
                        Vector3 viewDir=new Vector3(intersetion.normalizeProduct());
//                        viewDir.negate();
                        viewDir.normalize();

                        Vector3 H = viewDir.vecAdd(lightDir.multByK(3));
                        H.normalize();
                        //Intensity of the specular light
                        float NdotH =N.scalProd(H);
                        if (NdotH<0){
                            NdotH=0;
                        }
                        float intensity = (float) Math.pow(NdotH,128);
                        min = temp;

                          Lightintencity specular = source.getLightintencity().multByK(intensity).multByK(Driver.world.objects.get(i).getMaterial().getSpecularKs().getX());

                        tempColor = Driver.world.objects.get(i).getColor().multByLight(specular.addVec(diffuseIntensity.addVec(new Lightintencity(0.2f,0.2f,0.2f))));

                    }
                }

               color.add(tempColor);
//
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




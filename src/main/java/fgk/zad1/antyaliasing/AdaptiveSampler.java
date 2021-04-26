package fgk.zad1.antyaliasing;

import fgk.zad1.basics.GraphicsObject;
import fgk.zad1.basics.Ray;
import fgk.zad1.basics.Vector2;
import fgk.zad1.main.Driver;
import fgk.zad1.utilitis.Lightintencity;

import java.awt.*;

public class AdaptiveSampler extends Sampler {

    Lightintencity spartialRGB;
    int min,max;


    public AdaptiveSampler(float spatialContrastR,float spatialContrastG,float spatialContrastB, int max, int min){

        this.spartialRGB = new Lightintencity(spatialContrastR,spatialContrastG,spatialContrastB);
        this.max = max;
        this.min = min;
    }

    public Lightintencity sample(int x, int y) {
        int step =0;
        boolean sampling = true;
            Vector2[] vectors = {
                    new Vector2((float)( x+0.5f - Driver.world.viewPlane.width *0.5),(float) (y+0.5f - Driver.world.viewPlane.heigth *0.5)),
                    new Vector2((float)( x+0.5f - Driver.world.viewPlane.width *0.5),(float) (y-0.5f - Driver.world.viewPlane.heigth *0.5)),
                    new Vector2((float)( x-0.5f - Driver.world.viewPlane.width *0.5),(float) (y+0.5f - Driver.world.viewPlane.heigth *0.5)),
                    new Vector2((float)( x-0.5f - Driver.world.viewPlane.width *0.5),(float) (y-0.5f - Driver.world.viewPlane.heigth *0.5)),
                    new Vector2((float)( x - Driver.world.viewPlane.width *0.5),(float) (y - Driver.world.viewPlane.heigth *0.5))};
            Lightintencity[] lightintencities = {Driver.world.background,
                    Driver.world.background,
                    Driver.world.background,
                    Driver.world.background,
                    Driver.world.background};
            double[] sectionDistance= {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE, Double.MAX_VALUE};
            Ray[] rays ={
                    Driver.camera.createRay(vectors[0]),
                    Driver.camera.createRay(vectors[1]),
                    Driver.camera.createRay(vectors[2]),
                    Driver.camera.createRay(vectors[3]),
                    Driver.camera.createRay(vectors[4])
            };
        checkSections(rays, lightintencities, sectionDistance);
            float r=0;
            float g=0;
            float b=0;
        for(int i =0;i<lightintencities.length-1;i++)
        {

               if (lightintencities[4].r-lightintencities[i].r>spartialRGB.r||lightintencities[4].g-lightintencities[i].g>spartialRGB.g||lightintencities[4].b-lightintencities[i].b>spartialRGB.b)
                {

                    recursionStep(step,lightintencities[4],lightintencities[i],r,g,b, vectors[4], vectors[i]);
                }
                else
                {
                r+=(lightintencities[4].r+lightintencities[i].r)/2;
                g+=(lightintencities[4].g+lightintencities[i].g)/2;
                b+=(lightintencities[4].b+lightintencities[i].b)/2;


                }
        }
        return new Lightintencity(r/4,g/4,b/4);
    }
    void recursionStep(int recursion,Lightintencity centerLightIntencity, Lightintencity bLightIntencity, float r, float g, float b, Vector2 centerVector, Vector2 bVector)
    {
        recursion ++;

        Vector2[] vectors = {new Vector2(centerVector.getX()+bVector.getX()/2,centerVector.getY()+bVector.getY()/2),
                new Vector2(centerVector.getX(), bVector.getY()), new Vector2( bVector.getX(), centerVector.getY()) };
        Ray[] rays = {
                Driver.camera.createRay(vectors[0]),
                Driver.camera.createRay(vectors[1]),
                Driver.camera.createRay(vectors[2])
        };

        Lightintencity[] lightintencities = {Driver.world.background,Driver.world.background,Driver.world.background};
        double[] sectionDistance= {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE};

        checkSections(rays, lightintencities, sectionDistance);

        for(int i =1;i<lightintencities.length-1;i++)
        {

            if((lightintencities[1].r -lightintencities[i].r)>0)
            System.out.println((lightintencities[1].r -lightintencities[i].r));
            if((lightintencities[1].g -lightintencities[i].g)>0)
                System.out.println((lightintencities[1].r -lightintencities[i].r));
            if((lightintencities[1].b -lightintencities[i].b)>0)
                System.out.println((lightintencities[1].r -lightintencities[i].r));
            if (recursion<=max&&(lightintencities[1].r-lightintencities[i].r>=spartialRGB.r||lightintencities[1].g-lightintencities[i].g>spartialRGB.g||lightintencities[1].b-lightintencities[i].b>spartialRGB.b))
            {

                recursionStep(recursion,lightintencities[1], lightintencities[i], r,g,b, vectors[1], vectors[i]);
            }
            else
            {
                r+=(lightintencities[1].r+lightintencities[i].r);
                g+=(lightintencities[1].g+lightintencities[i].g);
                b+=(lightintencities[1].b+lightintencities[i].b);
            }
        }
        }
    private void checkSections(Ray[] rays, Lightintencity[] lightintencities, double[] sectionDistance) {
        for (GraphicsObject object: Driver.world.objects
        ) {
            for(int i =0 ; i<rays.length;i++)
            {
                double tempSectionDistance = object.checkSection(rays[i]);

                if(tempSectionDistance!=0&&sectionDistance[i]>tempSectionDistance)
                {
                    sectionDistance[i] = tempSectionDistance;
                    lightintencities[i]=object.getColor();
                }

            }
        }
    }


}


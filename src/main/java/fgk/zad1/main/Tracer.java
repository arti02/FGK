package fgk.zad1.main;

import fgk.zad1.Lights.Source;
import fgk.zad1.antyaliasing.AdaptiveSampler;
import fgk.zad1.basics.*;
import fgk.zad1.material.Material;
import fgk.zad1.utilitis.Lightintencity;

import java.awt.*;
import java.util.List;

public class Tracer {
    public static final int maxReccuresion =4;


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
    //tracer
    public Lightintencity calculateLight(int x, int y,List<Source> sources) throws Exception {
        Lightintencity color = new Lightintencity(0f, 0f, 0f);
        /** Antyaliasing dzielimy nasz pixel na 8 wiersz i 8 kolumn
         *
         */
        for (int row = 0; row < Driver.sampler.samples; row++) {
            for (int col = 0; col < Driver.sampler.samples; col++) {
                Vector2 vector2 = Driver.sampler.sampleNormal(row,col, x, y);
                Ray ray = Driver.camera.createRay(vector2);

                GraphicsObject graphicsObject = ray.checkFirstIntersectObjectWithShadow(true);

                if(graphicsObject!=null) {

                        Vector3 intersetion = graphicsObject.checkSectionReturnVector(ray);

                        if (intersetion != null) {
                            for (Source source : sources
                            ) {

                                Vector3 lightDir = source.getSourcePoint().vecSub(intersetion).scalMulti(-1);
                                lightDir.normalize();
                                Ray shadowRay = new Ray(intersetion, lightDir);
                                GraphicsObject firstBumpedObject = shadowRay.checkFirstIntersectObjectWithShadow(true);
                                if (firstBumpedObject != null) {
                                    Vector3 vector = firstBumpedObject.checkSectionReturnVector(shadowRay);
                                    float distancev1=  source.getSourcePoint().vecSub(vector).lengthOfVector();
                                    float distance = source.getSourcePoint().vecSub(intersetion).lengthOfVector();

                                    if (distancev1>=distance){

                                        Material material = graphicsObject.getMaterial();
                                        Vector3 normal = graphicsObject.getNormal(intersetion);
                                        normal.normalize();
                                        Vector3 reflect = lightDir.reflect(normal);
                                        float diff = normal.scalProd(reflect);
                                        if (diff < 0) diff = 0;
                                        float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                                        float attenuation = (float) (source.shiness / (distance));
                                        Vector3 result = material.getAmbientKa().scalMulti(attenuation).vecAdd(
                                                material.getDiffuseKd().scalMulti(diff)).vecAdd(
                                                material.getSpecularKs().scalMulti(
                                                        spec * attenuation)).scalMulti(attenuation);
                                        color.add(checkMaterial(graphicsObject, ray,result, intersetion));


                                    } else {
                                        Material material = graphicsObject.getMaterial();
                                        Vector3 normal = graphicsObject.getNormal(intersetion);
                                        normal.normalize();
                                        Vector3 reflect = lightDir.reflect(normal);
                                        float diff = normal.scalProd(reflect);
                                        if (diff < 0) diff = 0;
                                        float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                                        float attenuation = (float) (source.shiness / ( distance ));
                                        Vector3 result = material.getAmbientKa().scalMulti(attenuation);
                                        color.add(checkMaterial(graphicsObject, ray,result, intersetion));

                                    }
                                } else {
                                    Material material = graphicsObject.getMaterial();
                                    Vector3 normal = graphicsObject.getNormal(intersetion);
                                    Vector3 reflect = lightDir.reflect(normal);
                                    float diff = normal.scalProd(lightDir);
                                    if (diff < 0) diff = 0;
                                    float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                                    float distance = source.getSourcePoint().vecSub(intersetion).lengthOfVector();
                                    float attenuation = (float) (source.shiness / (distance));
                                    Vector3 result = material.getAmbientKa().scalMulti(attenuation).vecAdd(
                                            material.getDiffuseKd().scalMulti(diff)).vecAdd(
                                            material.getSpecularKs().scalMulti(
                                                    spec * attenuation)).scalMulti(attenuation);

                                    color.add(checkMaterial(graphicsObject, ray,result, intersetion));
                                }
                            }
                    }
                }
            }
        }
        color.divideByK(Driver.sampler.samples*Driver.sampler.samples);
        return  color;
    }
    //Check Material before set the final color of graphic object
    public Lightintencity checkMaterial(GraphicsObject graphicsObject, Ray ray, Vector3 result, Vector3 intersection) throws Exception {
        if(graphicsObject.getMaterial().mirror)
        {
            Vector3 normal = graphicsObject.getNormal(intersection);
            normal.normalize();
            Vector3 reflected = ray.getDirection().reflect(normal);
            Ray reflectedRay = new Ray(intersection, reflected);
            GraphicsObject graphicsObject1 = reflectedRay.checkFirstIntersectObject(true);
            if(graphicsObject1!=null)
            {   Vector3 section =  graphicsObject1.checkSectionReturnVector(reflectedRay);

                   if(graphicsObject1.getMaterial().mirror)
                   {
                       int i =0;
                       return mirrorRecurresion(i, reflectedRay,graphicsObject1 , result ,section);
                   }
                   else if(graphicsObject1.getMaterial().transparent)
                   {
                       reflectedRay = new Ray(intersection, reflected);
                       int i =0;
                       return transparentRecuression(i, reflectedRay,graphicsObject1 , result,section );
                   }
                   else
                   {

                       Vector3 result1 = calculateResult(section,graphicsObject1);
                       return new Lightintencity(result1.getX()*graphicsObject1.getColor().getR(),
                               result1.getY()*graphicsObject1.getColor().getG(),
                               result1.getZ()*graphicsObject1.getColor().getB());
                   }


            }
            else
            {
                return new Lightintencity(0,0,0);
            }
        }
        else if(graphicsObject.getMaterial().transparent)
        {
            Vector3 vec1 =   ray.getDirection().normalizeProduct();
            float lambda1 = graphicsObject.getMaterial().getTransparent_d();
             //dziwne  Vector3 normal = graphicsObject.getNormal(intersection).scalMulti(-1);
                Vector3 normal = graphicsObject.getNormal(intersection);
                Vector3 directionInside = calculateTransparent(vec1, normal,1,lambda1);
                Ray insideRay = new Ray(intersection,directionInside);
                    GraphicsObject graphicsObject1 = insideRay.checkFirstIntersectObject(true);
                    if(graphicsObject1!=null)
                    { int i =0;
                        Vector3 intersectionGraphicObject1 = graphicsObject1.checkSectionReturnVectorTransparent(insideRay );
                            if (graphicsObject1.getMaterial().mirror) {
                                return mirrorRecurresion(i, insideRay, graphicsObject1, result, intersectionGraphicObject1);
                            } else if (graphicsObject1.getMaterial().transparent) {
                                return transparentRecuression(i, insideRay, graphicsObject1, result, intersectionGraphicObject1);
                            } else {
                                Vector3 result1 = calculateResult(intersectionGraphicObject1, graphicsObject1);
                                return new Lightintencity(result1.getX() * graphicsObject1.getColor().getR(),
                                        result1.getY() * graphicsObject1.getColor().getG(),
                                        result1.getZ() * graphicsObject1.getColor().getB());
                            }
                    }
                    else
                    {
                        return new Lightintencity(Driver.world.background.getR(),
                                Driver.world.background.getG(),
                                Driver.world.background.getB());
                    }
        }
        else
        {
                return new Lightintencity(result.getX()*graphicsObject.getColor().getR(),
                    result.getY()*graphicsObject.getColor().getG(),
                    result.getZ()*graphicsObject.getColor().getB());
        }
    }
    //Transparent Recuression
    public Lightintencity transparentRecuression(int i,Ray ray, GraphicsObject graphicsObject,  Vector3 result,Vector3 intersection) throws Exception {
            i++;
            Vector3 innerRayDirection = calculateTransparent(ray.getDirection(), graphicsObject.getNormal(intersection), 1, graphicsObject.getMaterial().getTransparent_d());
            Ray innerRay = new Ray(intersection, innerRayDirection);


                GraphicsObject graphicsObject1 = innerRay.checkFirstIntersectObject(true);
                if (graphicsObject1 != null) {
                    Vector3 section = graphicsObject1.checkSectionReturnVectorTransparent(innerRay);
                    if(i<maxReccuresion)
                    {
                        if (graphicsObject1.getMaterial().mirror) {


                            return mirrorRecurresion(i, innerRay, graphicsObject1, result, section);
                        } else if (graphicsObject1.getMaterial().transparent) {
                            return transparentRecuression(i, innerRay, graphicsObject1, result, section);
                        } else {

                            Vector3 result1 = calculateResult(section,graphicsObject1);
                            return new Lightintencity(result1.getX()*graphicsObject1.getColor().getR(),
                                    result1.getY()*graphicsObject1.getColor().getG(),
                                    result1.getZ()*graphicsObject1.getColor().getB());
                        }
                    } else {
                        Vector3 result1 = calculateResult(section,graphicsObject1);
                        return new Lightintencity(result1.getX()*graphicsObject1.getColor().getR(),
                                result1.getY()*graphicsObject1.getColor().getG(),
                                result1.getZ()*graphicsObject1.getColor().getB());
                    }
                    }
                else
                {
                    Vector3 result1 = calculateResult(intersection,graphicsObject);
                    return new Lightintencity(result1.getX()*graphicsObject.getColor().getR(),
                            result1.getY()*graphicsObject.getColor().getG(),
                            result1.getZ()*graphicsObject.getColor().getB());
                }
    }
    //Mirror reccuresion
    public Lightintencity mirrorRecurresion(int i,Ray ray, GraphicsObject graphicsObject,  Vector3 result,Vector3 intersection) throws Exception {

        if(i < maxReccuresion)
        {
            i++;
            Vector3 normal = graphicsObject.getNormal(intersection);
            Vector3 reflectedDirection = ray.getDirection().reflect(normal);
            Ray reflectedRay = new Ray(intersection,reflectedDirection);
            GraphicsObject graphicsObject1 = reflectedRay.checkFirstIntersectObject(true);
            if(graphicsObject1!=null)
            {
                Vector3 section = graphicsObject1.checkSectionReturnVector(reflectedRay);
                if(graphicsObject1.getMaterial().mirror)
                {
                    return mirrorRecurresion(i,reflectedRay,graphicsObject1,result,section);

                }
                else if(graphicsObject1.getMaterial().transparent)
                {

                    return transparentRecuression(i, reflectedRay,graphicsObject1 , result,section );
                }
                else
                {
                    Vector3 result1 = calculateResult(section,graphicsObject1);
                    return new Lightintencity(result1.getX()*graphicsObject1.getColor().getR(),
                            result1.getY()*graphicsObject1.getColor().getG(),
                            result1.getZ()*graphicsObject1.getColor().getB());


                }

            }
            else
            {
                return new Lightintencity(result.getX()*Driver.world.background.getR(),
                        result.getY()*Driver.world.background.getG(),
                        result.getZ()*Driver.world.background.getB());
            }

        }
        else
        {
            return new Lightintencity(result.getX()*graphicsObject.getColor().getR(),
                    result.getY()*graphicsObject.getColor().getG(),
                    result.getZ()*graphicsObject.getColor().getB());
        }

    }
    //Calculate transparent
    public Vector3 calculateTransparent(Vector3 direction, Vector3 normal, float diffuse1, float diffuse2) throws Exception {
        direction.normalize();
        normal.normalize();
        float cos01 = direction.scalProd(normal);

        float sin01 = (float)Math.sqrt((1 -  Math.pow(cos01,2)));
        sin01 = Math.abs(sin01);
        float cos02 = (float) Math.sqrt(1- (diffuse1*diffuse1*(1-cos01*cos01)/(diffuse2*diffuse2)));

        float sin02 = (float) Math.sqrt(1-Math.pow(cos02,2));

        Vector3 b = direction.vecAdd(normal.scalMulti(cos01)).scalDiv(sin01);
           return  b.scalMulti(sin02).vecSub(normal.scalMulti(cos02));

    }
    //CalculateLight at graphicObject
    public Vector3 calculateResult( Vector3 intersetion, GraphicsObject graphicsObject)
    {
        Vector3 color = new Vector3(0,0,0);
        if ( intersetion != null) {
            for (Source source : Driver.listOfSources
            ) {

                Vector3 lightDir = source.getSourcePoint().vecSub(intersetion).scalMulti(-1);
                lightDir.normalize();
                Ray shadowRay = new Ray(intersetion, lightDir);
                GraphicsObject firstBumpedObject = shadowRay.checkFirstIntersectObject(true);
                if (firstBumpedObject != null) {
                    Vector3 vector = firstBumpedObject.checkSectionReturnVector(shadowRay);
                    float distancev1=  source.getSourcePoint().vecSub(vector).lengthOfVector();
                    float distance = source.getSourcePoint().vecSub(intersetion).lengthOfVector();
                    if (distancev1>=distance){
                        Material material = graphicsObject.getMaterial();
                        Vector3 normal = graphicsObject.getNormal(intersetion);
                        normal.normalize();
                        Vector3 reflect = lightDir.reflect(normal);
                        float diff = normal.scalProd(reflect);
                        if (diff < 0) diff = 0;
                        float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                        float attenuation = (float) (source.shiness / (distance));
                        Vector3 result = material.getAmbientKa().scalMulti(attenuation).vecAdd(
                                material.getDiffuseKd().scalMulti(diff)).vecAdd(
                                material.getSpecularKs().scalMulti(
                                        spec * attenuation)).scalMulti(0.5f).scalMulti(attenuation);
                        color =color.vecAdd(result);


                    } else {
                        Material material = graphicsObject.getMaterial();
                        Vector3 normal = graphicsObject.getNormal(intersetion);
                        normal.normalize();
                        Vector3 reflect = lightDir.reflect(normal);
                        float diff = normal.scalProd(reflect);
                        if (diff < 0) diff = 0;
                        float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                        float attenuation = (float) (source.shiness / (distance));
                        Vector3 result = material.getAmbientKa().scalMulti(0.5f);
                        color = color.vecAdd(result);

                    }
                } else {
                    Material material = graphicsObject.getMaterial();
                    Vector3 normal = graphicsObject.getNormal(intersetion);
                    Vector3 reflect = lightDir.reflect(normal);
                    float diff = normal.scalProd(lightDir);
                    if (diff < 0) diff = 0;
                    float spec = (float) Math.pow(lightDir.scalProd(reflect), source.shiness);
                    float distance = source.getSourcePoint().vecSub(intersetion).lengthOfVector();
                    float attenuation = (float) (source.shiness / (distance));
                    Vector3 result = material.getAmbientKa().scalMulti(attenuation).vecAdd(
                            material.getDiffuseKd().scalMulti(diff)).vecAdd(
                            material.getSpecularKs().scalMulti(
                                    spec * attenuation)).scalMulti(0.5f).scalMulti(attenuation);
                    color =color.vecAdd(result);
                }
            }
        }
        return  color;
    }
}




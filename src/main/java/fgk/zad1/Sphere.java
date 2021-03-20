package fgk.zad1;

import java.lang.Math;
public class Sphere {
    /**
    Konstruktor sfery, argumenty pobierane to wektor określający środek sfery oraz promień sfery.
 */
  public Sphere(Vector3 center, float radius)
  {
      this.radius = radius;
      this.center = center;
  }

    /**Wektor określający centrum
     */
  private  Vector3 center;
    /**Promień sfery
     *
     */
  private float radius;
  /**
  Sprawdzanie czy promień o wektorze źródła równym
  origin oraz wektorze kierunkowym równym direction
  przecina  sferę przez którą jest wywoływana metoda.
   */
  public Vector3[] checkSection(Vector3 origin, Vector3 direction)
  {
      Vector3 oMinusC = center.vecSub(origin);
      float A = direction.scalProd(direction);
      float B = direction.scalProd(oMinusC)*2;
      float C = oMinusC.scalProd(oMinusC)- (radius*radius);
      float Delta = B*B-4*A*C;
      //t = (-B + sqrt)/A
      System.out.println(Delta);
      if(Delta<0)
      {
          return null;
      }
      else if(Delta==0)
      {
          Vector3[] vector = new Vector3[1];
          float t = (-1)*B/(2*A);
          vector[0]= new Vector3(origin.getX()+direction.getX()*t,origin.getY()+direction.getY()*t,origin.getZ()+direction.getZ()*t);
          return  vector;
      }
      else
      {
          Vector3[] vector = new Vector3[2];
          float[] result = new float[2];
          float res1 =  (float)(((-1)*B+Math.sqrt(Delta))/(2*A));
          float res2 = (float)(((-1)*B-Math.sqrt(Delta))/(2*A));
          vector[0]= new Vector3(origin.getX()+direction.getX()*res1,origin.getY()+direction.getY()*res1,origin.getZ()+direction.getZ()*res1);
          vector[1]= new Vector3(origin.getX()+direction.getX()*res2,origin.getY()+direction.getY()*res2,origin.getZ()+direction.getZ()*res2);
          return  vector;
      }
  }
    /**
    Sprawdzanie czy promień
    przecina  sferę przez którą jest wywoływana metoda.
     */
  public Vector3[] checkSection(Ray ray)
  {
      Vector3 origin =ray.getOrigin();
      Vector3 direction = ray.getDirection();
      Vector3 oMinusC = center.vecSub(origin);
      float A = direction.scalProd(direction);
      float B = direction.scalProd(oMinusC)*2;
      float C = oMinusC.scalProd(oMinusC)- (radius*radius);
      float Delta = B*B-4*A*C;
      //t = (-B + sqrt)/A
      System.out.println(Delta);
      if(Delta<0)
      {
          return null;
      }
      else if(Delta==0)
      {
          Vector3[] vector = new Vector3[1];
          float t = (-1)*B/(2*A);
          vector[0]= new Vector3(origin.getX()+direction.getX()*t,origin.getY()+direction.getY()*t,origin.getZ()+direction.getZ()*t);
          return  vector;
      }
      else
      {
          Vector3[] vector = new Vector3[2];
          float[] result = new float[2];
          float res1 =  (float)(((-1)*B+Math.sqrt(Delta))/(2*A));
          float res2 = (float)(((-1)*B-Math.sqrt(Delta))/(2*A));
          vector[0]= new Vector3(origin.getX()+direction.getX()*res1,origin.getY()+direction.getY()*res1,origin.getZ()+direction.getZ()*res1);
          vector[1]= new Vector3(origin.getX()+direction.getX()*res2,origin.getY()+direction.getY()*res2,origin.getZ()+direction.getZ()*res2);
          return  vector;
      }
  }
}

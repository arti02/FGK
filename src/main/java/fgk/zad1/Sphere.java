import java.lang.Math;
public class Sphere {
  private  Vector3 center;
  //(x-cx)^2+(y-cy)^2 +(z-cz)^2-R^2=0
  private float radius;
    //(p-c)*(p-c)-R^2=0;
    //p(t) = o+d*t - równanie promienia
    //(o+td-c)x(o+td-c)-R^2=0
  public float[] checkSection(Vector3 origin, Vector3 direction)
  {
      Vector3 oMinusC = origin.vecSub(center);
        float A = direction.scalProd(direction);
        float B = origin.scalProd(oMinusC)*2;
        float C = oMinusC.scalProd(oMinusC)- (radius*radius);
      float Delta = B*B-4*A*C;
      //t = (-B + sqrt)/A
      if(Delta<0)
      {
          float[] result = new float[0];
          return result;
      }
      else if(Delta==0)
      {
          float[] result = new float[1];
          result[0] = (-1)*B/(2*A);
          return result;
      }
      else
      {
          float[] result = new float[2];
          result[0] = (float) (((-1)*B+Math.sqrt(Delta))/(2*A));
          result[1] = (float) (((-1)*B-Math.sqrt(Delta))/(2*A));
          return result;
      }
  }
  public float[] checkSection(Ray ray)
  {
      Vector3 origin =ray.getOrigin();
      Vector3 direction = ray.getDirection();
      Vector3 oMinusC = origin.vecSub(center);
      float A = direction.scalProd(direction);
      float B = origin.scalProd(oMinusC)*2;
      float C = oMinusC.scalProd(oMinusC)- (radius*radius);
      float Delta = B*B-4*A*C;
      //t = (-B + sqrt)/A
      if(Delta<0)
      {
          float[] result = new float[0];
          return result;
      }
      else if(Delta==0)
      {
          float[] result = new float[1];
          result[0] = (-1)*B/(2*A);
          return result;
      }
      else
      {
          float[] result = new float[2];
          result[0] = (float) (((-1)*B+Math.sqrt(Delta))/(2*A));
          result[1] = (float) (((-1)*B-Math.sqrt(Delta))/(2*A));
          return result;
      }
  }
}

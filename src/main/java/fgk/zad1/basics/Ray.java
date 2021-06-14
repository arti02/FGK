package fgk.zad1.basics;

import fgk.zad1.main.Driver;

public  class  Ray {
    /**p(t)= origin + t*direction
     *
     */
  private Vector3 origin, direction, destination;
  private  float distance;

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getDestination() {
        return destination;
    }

    public void setDestination(Vector3 destination) {
        this.destination = destination;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    /**Konstruktor promienia, który przyjmuje jako atrybuty wektor który określa początek promienia oraz wektor kierunkowy
     *
     * @param origin
     * @param direction
     */
    public  Ray(Vector3 origin, Vector3 direction)
    {
        this.origin =origin;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }

    public GraphicsObject checkFirstIntersectObject(boolean xd)
    {
        GraphicsObject graphicsObject = null;
        double distance = Double.MAX_VALUE;
        for (GraphicsObject object:Driver.world.objects
             ) {



               float distancev2 = object.checkSection(this);

            if(distancev2>0)
            {
                if (distance>distancev2&&distancev2!=0)
                {

                    distance = distancev2;
                    graphicsObject = object;
                }
            }



        }

        return graphicsObject;
    }
    public GraphicsObject checkFirstIntersectObjectWithShadow(boolean xd)
    {
        GraphicsObject graphicsObject = null;
        double distance = Double.MAX_VALUE;
        for (GraphicsObject object:Driver.world.objects
        ) {

            {
                float distancev2 = object.checkSection(this);

                    if (distance>distancev2&&distancev2!=0)
                    {

                        distance = distancev2;
                        graphicsObject = object;
                    }




            }

        }

        return graphicsObject;
    }
}

package fgk.zad1.Lights;

import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;
import fgk.zad1.utilitis.Lightintencity;

public class PointSource extends  Source{

    Vector3 direction;
    Vector3 sourcePoint;


    Lightintencity sourceIntencity;
    float a1;
    float a2;
    float Op;




    @Override
    public Lightintencity getLightintencity() {
        return sourceIntencity;
    }

    public PointSource( Vector3 sourcePoint,Lightintencity sourceIntencity)
    {


        this.sourcePoint = sourcePoint;
        this.sourceIntencity=sourceIntencity;
    }
    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getSourcePoint() {
        return sourcePoint;
    }

    public void setSourcePoint(Vector3 sourcePoint) {
        this.sourcePoint = sourcePoint;
    }

    public Lightintencity getSourceIntencity() {
        return sourceIntencity;
    }

    public void setSourceIntencity(Lightintencity sourceIntencity) {
        this.sourceIntencity = sourceIntencity;
    }

}

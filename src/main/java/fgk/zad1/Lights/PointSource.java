package fgk.zad1.Lights;

import fgk.zad1.basics.Vector2;
import fgk.zad1.basics.Vector3;

public class PointSource extends  Source{

    Vector3 direction;
    Vector3 sourcePoint;

    @Override
    public void emiteLight() {

    }
    public PointSource(Vector3 direction, Vector3 sourcePoint)
    {
        this.direction = direction;
        this.sourcePoint = sourcePoint;
    }
}

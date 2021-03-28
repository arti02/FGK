package fgk.zad1;

import fgk.zad1.Utilitis.Reductor;

public class LightIntensity {
    final double bottomLightLimit =0;
    final double upperLightLimit = 1;
    private double R;
    private double G;
    private double B;

    public double getR() {
        return R;
    }

    public void setR(double r) {
        R = r;
    }

    public double getG() {
        return G;
    }

    public void setG(double g) {
        G = g;
    }

    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public LightIntensity(double r, double g, double b) {
        this.R = r;
        this.G = g;
        this.B = b;
    }
    public LightIntensity(double r, double g) {
        this.R = r;
        this.G = g;
        this.B =0;
    }
    public LightIntensity(double r ) {
        this.R = r;
        this.G =0;
        this.B =0;
    }
    public LightIntensity( ) {
        this.R =0;
        this.G =0;
        this.B =0;
    }
    public void plusLight(double r, double g, double b)
    {
        this.R =   Reductor.min(this.R+=r, upperLightLimit);
        this.G =   Reductor.min(this.G+=g, upperLightLimit);
        this.B =   Reductor.min(this.B+=b, upperLightLimit);
    }
    public void plusLight(double value)
    {
        this.R =   Reductor.min(this.R+=value, upperLightLimit);
        this.G =   Reductor.min(this.G+=value, upperLightLimit);
        this.B =   Reductor.min(this.B+=value, upperLightLimit);
    }
    public void plusLight(LightIntensity lightIntensity)
    {
        this.R =   Reductor.min(this.R+= lightIntensity.getR(), upperLightLimit);
        this.G =   Reductor.min(this.G+= lightIntensity.getG(), upperLightLimit);
        this.B =   Reductor.min(this.B+= lightIntensity.getB(), upperLightLimit);
    }
    public void minusLight( double r, double g, double b)
    {
       this.R =Reductor.max(bottomLightLimit, this.R-=r);
        this.G=Reductor.max(bottomLightLimit, this.G-=g);
        this.B=Reductor.max(bottomLightLimit, this.B-=b);
    }
    public void minusLight( double value)
    {
        this.R =Reductor.max(bottomLightLimit, this.R-=value);
        this.G=Reductor.max(bottomLightLimit, this.G-=value);
        this.B=Reductor.max(bottomLightLimit, this.B-=value);
    }
    public void minusLight(LightIntensity lightIntensity)
    {
     this.R =   Reductor.max(this.R-= lightIntensity.getR(), bottomLightLimit);
     this.G =   Reductor.max(this.G-= lightIntensity.getG(), bottomLightLimit);
     this.B =    Reductor.max(this.B-= lightIntensity.getB(), bottomLightLimit);
    }
    public void divideLight(double divider)
    {
        this.R =   Reductor.min(    this.R/divider, upperLightLimit);
        this.G =   Reductor.min(this.G/divider, upperLightLimit);
        this.B =    Reductor.min( this.B/divider, upperLightLimit);
    }
}

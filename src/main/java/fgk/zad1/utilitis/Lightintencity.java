package fgk.zad1.utilitis;

import fgk.zad1.basics.Vector3;

public class Lightintencity {
    public float r,g,b;

    public Lightintencity()
    {
        this.r = 0;
        this.g = 0;
        this.b = 0;
    }
    public Lightintencity(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public Lightintencity(Lightintencity lightintencity)
    {
        this.r = lightintencity.r;
        this.g = lightintencity.g;
        this.b = lightintencity.b;
    }

    public Lightintencity(Vector3 vector3) {
        this.setR(vector3.getX());
        this.setG(vector3.getY());
        this.setB(vector3.getZ());

    }

    public Lightintencity addVec(Lightintencity color){
        Lightintencity lightintencity=new Lightintencity();
        lightintencity.setR(this.getR() +color.getR());
        lightintencity.setG(this.getG() +color.getG());
        lightintencity.setB(this.getB() +color.getB());

        return lightintencity;
    }
    public void add(Lightintencity color){
        this.setR(this.getR() +color.getR());
        this.setG(this.getG() +color.getG());
        this.setB(this.getB() +color.getB());

    }
    public void add(float r, float g, float b)
    {
        this.r += r;
        this.g +=g;
        this.b +=b;
    }
    public Lightintencity multByK(float k)
    {
        Lightintencity lightintencity=new Lightintencity();
        lightintencity.setR(this.getR() *k);
        lightintencity.setG(this.getG() *k);
        lightintencity.setB(this.getB() *k);

        return lightintencity;
    }
    public Lightintencity multByLight(Lightintencity k)
    {
        Lightintencity lightintencity=new Lightintencity();
//        if (k.getR()<0){
//            lightintencity.setR(this.getR() *1);
//        }else {
            lightintencity.setR(this.getR() *k.getR());
//        }
//        if (k.getG()<0){
            lightintencity.setG(this.getG() *1);
//        }else {
            lightintencity.setG(this.getG() *k.getG());
//        }
//        if (k.getB()<0){
//            lightintencity.setB(this.getB() *1);
//        }else {
            lightintencity.setB(this.getB() *k.getB());
//        }

        return lightintencity;
    }
    public Lightintencity divByLight(Lightintencity k)
    {
        Lightintencity lightintencity=new Lightintencity();
        lightintencity.setR(this.getR() /k.getR());
        lightintencity.setG(this.getG() /k.getG());
        lightintencity.setB(this.getB() /k.getB());

        return lightintencity;
    }

    public void sub(Lightintencity color){
        r-= color.r;
        g-=color.g;
        b-=color.b;
    }
    public float getR()
    {
        if(this.r>1)
        {
            return 1;
        }
        if(this.r<0)
        {
            return  0;
        }else
        return this.r;
    }
    public float getG()
    {
        if(this.g>1)
        {
            return 1;
        }
        if(this.g<0)
        {
            return  0;
        }else {

            return this.g;
        }
    }
    public float getB()
    {
        if(this.b>1)
        {
            return 1;
        }
        if(this.b<0)
        {
            return  0;
        }
        return this.b;
    }

    public void setR(float r) {

        if(r>1.0f)
        {
            this.r= 1;
        }else
        if(r<0)
        {
            this.r=  0;
        }else {

            this.r=r;
        }

    }

    public void setG(float g) {
        if(g>1)
        {
            this.g= 1;
        }else
            if(g<0)
        {
            this.g=  0;
        }else {
            this.g = g;
        }
    }

    public void setB(float b) {
        if(b>1)
        {
            this.b= 1;
        }else
        if(b<0)
        {
            this.b=  0;
        }else {

            this.b=b;
        }
    }

    public Lightintencity divideByK(float scalar){

        this.r/=scalar;
        this.g/=scalar;
        this.b/=scalar;
        return this;
    }

    @Override
    public String toString() {
        return "LightIntencity{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    /**Konwertacja do koloru
     *
     * @return
     */
    public int toIntneger(){
        return (int)(r*255)<<16|(int)(g*255)<<8|(int)(b*255);
    }

}

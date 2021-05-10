package fgk.zad1.utilitis;

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
    public void add(Lightintencity color){
        r+= color.r;
        g+=color.g;
        b+=color.b;
    }
    public void add(float r, float g, float b)
    {
        this.r += r;
        this.g +=g;
        this.b +=b;
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
        }
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
        }
        return this.g;
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
    public void divide(int scalar){

        this.r/=scalar;
        this.g/=scalar;
        this.b/=scalar;
    }

    @Override
    public String toString() {
        return "ColorM{" +
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

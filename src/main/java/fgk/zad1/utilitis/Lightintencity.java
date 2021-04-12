package fgk.zad1.utilitis;

public class Lightintencity {
    public float r,g,b;

    public Lightintencity(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public void add(Lightintencity color){
        r+= color.r;
        g+=color.g;
        b+=color.b;
    }


    public void divide(int scalar){

        r/=scalar;
        g/=scalar;
        b/=scalar;
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

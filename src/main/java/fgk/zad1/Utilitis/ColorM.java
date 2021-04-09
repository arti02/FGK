package fgk.zad1.Utilitis;

public class ColorM {
    public float r,g,b;

    public ColorM(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    public void add(ColorM color){
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

    public int toIntneger(){
        return (int)(r*255)<<16|(int)(g*255)<<8|(int)(b*255);
    }

}

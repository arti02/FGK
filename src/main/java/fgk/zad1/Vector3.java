package fgk.zad1;

public class Vector3 {
    private float x;
    private float y;
    private float z;
    public Vector3(float x, float y, float z)
    {
        this.x=x;
        this.y=y;
        this.z=z;
    }
    public Vector3()
    {
        this.x=1;
        this.y=1;
        this.z=1;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public  Vector3 vecSub(Vector3 vector1)
    {
        float x = vector1.getX()-this.getX();
        float y = vector1.getY()-this.getY();
        float z = vector1.getZ()-this.getZ();
        return new Vector3(x,y,z);
    }
    public Vector3 scalSub(float substract)
    {
        float x = this.getX()-substract;
        float y = this.getY()-substract;
        float z = this.getZ()-substract;
        return new Vector3(x,y,z);
    }
    public float scalProd( Vector3 vector)
    {
        return this.getX()*vector.getX()+ this.getY()*vector.getY()+this.getZ()*vector.getZ();
    }
    public Vector3 vecProd(Vector3 vector)
    {
        float ay = this.getY();
        float bz = vector.getZ();
        float az = this.getZ();
        float by = vector.getY();
        float bx = vector.getX();
        float ax = this.getX();
        float cx = ax*bz-az*by;
        float cy = az*bx-ax*bz;
        float cz = ax*by-ay*bx;

        return new Vector3(cx,cy,cz);
    }
    public Vector3 vecDiv(Vector3 vector)
    {
        float x = this.getX()/vector.getX();
        float y = this.getY()/vector.getY();
        float z = this.getZ()/vector.getZ();

        return new Vector3(x,y,z);
    }
    public Vector3 scalMulti(float x) {
        return  new Vector3(this.getX()*x,this.getY()*x,this.getZ()*x);
    }
}

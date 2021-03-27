package fgk.zad1.Basics;

public class Vector3 {
    private float x;
    private float y;
    private float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3() {
        this.x = 1;
        this.y = 1;
        this.z = 1;
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

    @Override
    public java.lang.String toString() {
        return "Vector3{ x=" + getX() + " y=" + getY() + " z=" + getZ() + "}";
    }

    //Dlugosc wektora
    public float lengthOfVector() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    //Dlugosc wektora^2
    public float lengttSquared() {
        return (float) (Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    //Iloczyn skalarny
    public float scalProd(Vector3 vector) {
        return this.getX() * vector.getX() + this.getY() * vector.getY() + this.getZ() * vector.getZ();
    }

    //Iloczyn wektorowy
    public Vector3 vecProd(Vector3 vector) {
        float ax = this.getX();
        float ay = this.getY();
        float az = this.getZ();
        float bx = vector.getX();
        float by = vector.getY();
        float bz = vector.getZ();
        float cx = ay * bz - az * by;
        float cy = az * bx - ax * bz;
        float cz = ax * by - ay * bx;

        return new Vector3(cx, cy, cz);
    }

    // Znormalizowanie wektora
    public void normalize() {
        float n = this.lengthOfVector();
        if (n != 0) {
            try {
                this.divByK(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //Znormalizowanie wektora
    public Vector3 normalizeProduct() throws Exception {
        Vector3 newV = new Vector3(this.x, this.y, this.z);
        float n = this.lengthOfVector();
        if (n != 0) {
            throw new Exception("Couldn't normalize");
        }
        newV.divByK(n);
        return newV;
    }
    //Dzialania arefmetyczne /---------------------------------------------------------
    // a=Vector

    //-a
    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    // k*a
    public void multByK(float k) {
        this.x = this.getX() * k;
        this.y = this.getY() * k;
        this.z = this.getZ() * k;
    }

    // a/k
    public void divByK(float k) throws Exception {
        if (k != 0) {
            this.x = this.getX() / k;
            this.y = this.getY() / k;
            this.z = this.getZ() / k;
        } else
            throw new Exception("Cant divide by 0");
    }


    //a+=b
    public Vector3 scalAdd(float add) {
        float x = this.getX() + add;
        float y = this.getY() + add;
        float z = this.getZ() + add;
        return new Vector3(x, y, z);
    }

    //a-=b
    public void scalSub(float substract) {
        this.x = this.getX() - substract;
        this.y = this.getY() - substract;
        this.z = this.getZ() - substract;
    }

    //a*=b
    public Vector3 scalMulti(float x) {
        return new Vector3(this.getX() * x, this.getY() * x, this.getZ() * x);
    }

    // a/=b
    public Vector3 scalDiv(float x) {
        return new Vector3(this.getX() / x, this.getY() / x, this.getZ() / x);
    }
    ///dzialania na wektorach z tworzeniem nowego//--------------------------------------------------------------

    //a+b
    public void vecAdd(Vector3 vector) {
        this.x = vector.getX() + this.getX();
        this.y = vector.getY() + this.getY();
        this.z = vector.getZ() + this.getZ();
    }

    //a-b
    public Vector3 vecSub(Vector3 vector1) {
        float x = vector1.getX() - this.getX();
        float y = vector1.getY() - this.getY();
        float z = vector1.getZ() - this.getZ();
        return new Vector3(x, y, z);
    }

    // a*b
    public Vector3 vecMult(Vector3 vector) {
        float x = this.getX() * vector.getX();
        float y = this.getY() * vector.getY();
        float z = this.getZ() * vector.getZ();

        return new Vector3(x, y, z);
    }

    // a/b
    public Vector3 vecDiv(Vector3 vector) {
        float x = this.getX() / vector.getX();
        float y = this.getY() / vector.getY();
        float z = this.getZ() / vector.getZ();

        return new Vector3(x, y, z);
    }

    //Multiply by scalar left
    public Vector3 multByScalarLeft(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    // Stworzenie odwrotnego wektora
    public Vector3 vecOposit() {
        return new Vector3(-this.x, -this.y, -this.z);
    }

    //a==b
    public static boolean Equal(Vector3 left, Vector3 right) {
        return (left.x == right.x && left.y == right.y && left.z == right.z);
    }

    //a!=b
    public static boolean notEqual(Vector3 left, Vector3 right) {
        return (left.x != right.x || left.y != right.y || left.z != right.z);
    }

    //Inverse
    public static Vector3 ivnerse(Vector3 left, float scalar) {
        Vector3 vector = new Vector3();
        // get the inverse of the scalar up front to avoid doing multiple divides
        float inverse = 1.0f / scalar;
        vector.x = left.x * inverse;
        vector.y = left.y * inverse;
        vector.z = left.z * inverse;
        return vector;
    }
///????
    public static Vector3 reflect(Vector3 normal1, Vector3 normal2) {
        return normal1.vecSub(normal2.scalMulti(2 * normal1.scalProd(normal2)));
    }

    public Vector3 toPoint() {
        Vector3 p = new Vector3(this.x, this.y, this.z);
        return p;
    }

    public Vector3 lerp(Vector3 v, float t) {
        Vector3 vector = new Vector3();
        vector.x = this.x + t * (v.x - this.x);
        vector.y = this.y + t * (v.y - this.y);
        vector.z = this.z + t * (v.z - this.z);
        return vector;
    }


}

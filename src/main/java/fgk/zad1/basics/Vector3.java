package fgk.zad1.basics;

/**Stworzenie klasy wektor z 3 zmiennymi ktore okreslają koordynaty x,y,z
 */
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

    public Vector3 (Vector3 vector) {
        this.x=vector.x;
        this.y=vector.y;
        this.z=vector.z;
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

    /**Dlugosc wektora
     *
     *
     * @return float
     */
    public float lengthOfVector() {
        return (float) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    /**Dlugosc wektora^2
     *
     * @return float
     */
    public float lengttSquared() {
        return (float) (Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }

    /**Iloczyn skalarny
     *
     * @param vector
     * @return  float
     */
    public float scalProd(Vector3 vector) {
        return this.getX() * vector.getX() + this.getY() * vector.getY() + this.getZ() * vector.getZ();
    }

    /**Iloczyn wektorowy
     *
     * @param vector
     * @return
     */
    public Vector3 vecCross(Vector3 vector) {
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

    /** Znormalizowanie wektora
     *
     */
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

    /**Znormalizowanie wektora z utworzeniem nowego wektora
     *
     * @return  new Vector3
     * @throws Exception "Couldn't normalize"
     */
    public Vector3 normalizeProduct() throws Exception {
        Vector3 newV = new Vector3(this.x, this.y, this.z);
        float n = this.lengthOfVector();
        if (n == 0) {
            throw new Exception("Couldn't normalize");
        }
        newV.divByK(n);
        return newV;
    }

    /** negacja vektora
     *
     */
    public void negate() {
        setX(-this.x);
        setY( -this.y);
        setZ( -this.z);
    }

    /**  mnozenie koordynat wektora przez koficjent K
     *
     * @param k
     */
    public Vector3 multByK(float k) {
        this.x=this.getX() * k;
        this.y=this.getY() * k;
        this.z= this.getZ() * k;
        return this;
    }

    /** dzielenie koordynat wektora przez koficjent K
     *
     * @param k
     * @throws Exception "Cant divide by 0"
     */
    public void divByK(float k) throws Exception {
        if (k != 0) {
            this.x = this.getX() / k;
            this.y = this.getY() / k;
            this.z = this.getZ() / k;
        } else
            throw new Exception("Cant divide by 0");
    }


    /**dodowanie do koordynat wektora wartosc add
     *
     * @param add
     * @return new Vector3
     */
    public Vector3 scalAdd(float add) {
        float x = this.getX() + add;
        float y = this.getY() + add;
        float z = this.getZ() + add;
        return new Vector3(x, y, z);
    }

    /**odejmowanie od koordynat wektora wartosci substract
     *
     * @param substract
     */
    public Vector3 scalSub(float substract) {
        Vector3 vec=new Vector3();
        vec.x = this.getX() - substract;
        vec.y = this.getY() - substract;
        vec.z = this.getZ() - substract;
        return vec;
    }

    /** mnozenie koordynat wektora przez koficjent X z utworzeniem nowego vektora
     *
     * @param x
     * @return new Vector3
     */
    public Vector3 scalMulti(float x) {
        return new Vector3(this.getX() * x, this.getY() * x, this.getZ() * x);
    }

    /** dzielenie koordynat wektora przez koficjent X z utworzeniem nowego vektora
     *
     * @param x
     * @return New Vector3
     */
    public Vector3 scalDiv(float x) {
        return new Vector3(this.getX() / x, this.getY() / x, this.getZ() / x);
    }

    /** dodawanie dwoch wektorów z utworzeniem nowego vektora
     *
     * @param vector
     * @return new Vector3
     */
    public Vector3 vecAdd(Vector3 vector) {
        float x = vector.getX() + this.getX();
        float y = vector.getY() + this.getY();
        float z = vector.getZ() + this.getZ();
        return new Vector3(x, y, z);
    }

    /**odejmowanie dwoch wektorów z utworzeniem nowego vektora
     *
     * @param    vector1
     * @return new Vector 3
     */
    public Vector3 vecSub(Vector3 vector1) {
        float x = this.getX()-vector1.getX();
        float y = this.getY()-vector1.getY();
        float z = this.getZ()-vector1.getZ();
        return new Vector3(x, y, z);
    }

    /** mnozenie dwoch wektorów z utworzeniem nowego vektora
     *
     * @param vector
     * @return new Vector3
     */
    public Vector3 vecMult(Vector3 vector) {
        float x = this.getX() * vector.getX();
        float y = this.getY() * vector.getY();
        float z = this.getZ() * vector.getZ();

        return new Vector3(x, y, z);
    }

    /** dzielenie dwoch wektorów z utworzeniem nowego vektora
     *
     * @param vector
     * @return new Vector3
     */
    public Vector3 vecDiv(Vector3 vector) {
        float x = this.getX() / vector.getX();
        float y = this.getY() / vector.getY();
        float z = this.getZ() / vector.getZ();

        return new Vector3(x, y, z);
    }

    /**Multiply by scalar left
     *
     * @param scalar
     * @return new Vector3
     */
    public Vector3 multByScalarLeft(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /** Stworzenie odwrotnego wektora
     *
     * @return new Vector3
     */
    public Vector3 vecOposit() {
        return new Vector3(-this.x, -this.y, -this.z);
    }

    /** Sprawdzanie równosci dwoch wektorow
     *
     * @param left
     * @param right
     * @return boolean
     */
    public static boolean Equal(Vector3 left, Vector3 right) {
        return (left.x == right.x && left.y == right.y && left.z == right.z);
    }

    /**Sprawdzanie nierównosci dwoch wektorow
     *
     * @param left
     * @param right
     * @return boolean
     */
    public static boolean notEqual(Vector3 left, Vector3 right) {
        return (left.x != right.x || left.y != right.y || left.z != right.z);
    }

    /**Inverse
     *
     * @param left
     * @param scalar
     * @return new Vector3
     */
    public static Vector3 ivnerse(Vector3 left, float scalar) {
        Vector3 vector = new Vector3();
        // get the inverse of the scalar up front to avoid doing multiple divides
        float inverse = 1.0f / scalar;
        vector.x = left.x * inverse;
        vector.y = left.y * inverse;
        vector.z = left.z * inverse;
        return vector;
    }

    /** Reflekcja wektora
     *
     * @param normal2
     * @return Vector3
     */
    public  Vector3 reflect(Vector3 normal2) {
        return this.vecSub(normal2.scalMulti(2 * this.scalProd(normal2)));
    }

    /**Tworzenie punkta
     *
     * @return new Vector3
     */
    public Vector3 toPoint() {
        Vector3 p = new Vector3(this.x, this.y, this.z);
        return p;
    }

    /**znalezenie punktu miedzy wektorami
     * *
     * @param v
     * @param t
     * @return new Vector3
     */

    public Vector3 lerp(Vector3 v, float t) {
        Vector3 vector = new Vector3();
        vector.x = this.x + t * (v.x - this.x);
        vector.y = this.y + t * (v.y - this.y);
        vector.z = this.z + t * (v.z - this.z);
        return vector;
    }


}

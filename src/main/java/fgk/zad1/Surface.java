package fgk.zad1;

public class Surface {
    //Ax+By+Cz +D=0 <- równanie ogólne płaszczyzny
    //A, B i C nie mogą być jednocześnie równe 0 to są wartości wektora normalnego
    public Surface(Vector3 normalPlaneVector, float D)
    {
        this.normalPlaneVector = normalPlaneVector;
        this.D=D;
    }
    private Vector3 normalPlaneVector;
    private float D;

    public Vector3 getNormalPlaneVector() {
        return normalPlaneVector;

    }

    public float getD() {
        return D;
    }

    public void setD(float d) {
        D = d;
    }

    public void setNormalPlaneVector(Vector3 normalPlaneVector) {
        this.normalPlaneVector = normalPlaneVector;
    }

    //Równanie promienia X= Xr +t*v;

    public Vector3 checkSection(Ray ray)
    {

        float nDotV = this.normalPlaneVector.scalProd(ray.getDirection());
        if(nDotV==0)
        {
            return null;
        }
        else
        {
            float nDotXr = this.normalPlaneVector.scalProd(ray.getOrigin());
            float t = (this.getD()*(-1) -nDotXr)/nDotV;
            if(t>0)
            {
                float xo= ray.getOrigin().getX();
                float yo= ray.getOrigin().getY();
                float zo= ray.getOrigin().getZ();
                float xd= ray.getDirection().getX();
                float yd= ray.getDirection().getY();
                float zd = ray.getDirection().getZ();
                return new Vector3(xo+t*xd,yo+t*yd,zo+t*zd);
            }
            else
            {
                return null;
            }
        }
    }
    public Vector3 checkSection(Vector3 origin, Vector3 direction)
    {
        float nDotV = this.normalPlaneVector.scalProd(direction);
        if(nDotV==0)
        {
            return null;
        }
        else
        {
            float nDotXr = this.normalPlaneVector.scalProd(origin);
            float t = (this.getD()*(-1) -nDotXr)/nDotV;
            if(t>0)
            {
                float xo= origin.getX();
                float yo= origin.getY();
                float zo= origin.getZ();
                float xd= direction.getX();
                float yd= direction.getY();
                float zd = direction.getZ();
                return new Vector3(xo+t*xd,yo+t*yd,zo+t*zd);
            }
            else
            {
                return null;
            }
        }
    }
    }


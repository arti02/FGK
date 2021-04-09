package fgk.zad1.Cameras;
import fgk.zad1.Basics.Vector3;
import fgk.zad1.Image;


public abstract class Camera {
    Image image;

    public Camera(Image image) {
        this.image = image;
    }

    // pozycja  położenie obserwatora w układzie xyz;
    public Vector3 eye;
    //Kierunek patrzenia kamery
    public Vector3 lookat;
    //odleglosc do plaszyzny rzutowania
    public double distance;
    //koordynaty camery
    public Vector3 u,v,w;

    //Obliczenie i normalizacja koordynat camery
    public void compute_uvw(){
        w=eye.vecSub(lookat);
        w.normalize();

        //Vup - vup, wektor skierowany do góry kierunku obserwacji (jest to dowolny wektor
        //przyczepiony na wektorze kierunku patrzenia lookat, określa on orientację położenia
        Vector3 up=new Vector3(0.00424f,1,0.00764f);
        u=up.vecCross(w);
        u.normalize();

        v=w.vecCross(u);
        v.normalize();

    }


    //kamery, niejako czubek głowy obserwatora);
    protected float nearPlane;
    protected float farPlane;
    //Field of view
    protected float fov;

    public float getNearPlane() {
        return nearPlane;
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
    }

    public float getFarPlane() {
        return farPlane;
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
    }

    public float getFov() {
        return fov;
    }

    public void setFov(float fov) {
        this.fov = fov;
    }




}

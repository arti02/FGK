package fgk.zad1.Cameras;
import fgk.zad1.Basics.Vector3;
import fgk.zad1.Image;

public class Camera {
    Image image;

    public Camera(Image image) {
        this.image = image;
    }
}

/*
public abstract class Camera {

    // pozycja  położenie obserwatora w układzie xyz;
     protected Vector3 position;
     //Kierunek patrzenia kamery
     protected Vector3 target;
     //Vup - vup, wektor skierowany do góry kierunku obserwacji (jest to dowolny wektor
     //przyczepiony na wektorze kierunku patrzenia g, określa on orientację położenia
     //kamery, niejako czubek głowy obserwatora);
     protected Vector3 up;
     protected float nearPlane;
     protected float farPlane;
     //Field of view
     protected float fov;



     public Vector3 getPosition() {
        return position;
    }

    public Vector3 getTarget() {
        return target;
    }

    public void setTarget(Vector3 target) {
        this.target = target;
    }

    public Vector3 getUp() {
        return up;
    }

    public void setUp(Vector3 up) {
        this.up = up;
    }

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

    public void setPosition(Vector3 position) {
        this.position = position;
>>>>>>> Camera
    }

 */


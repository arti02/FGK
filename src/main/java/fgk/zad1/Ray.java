public class Ray {
    //p(t)= origin + t*direction
  private Vector3 origin, direction, destination;
  private  float distance;

    public Vector3 getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3 origin) {
        this.origin = origin;
    }

    public Vector3 getDirection() {
        return direction;
    }

    public void setDirection(Vector3 direction) {
        this.direction = direction;
    }

    public Vector3 getDestination() {
        return destination;
    }

    public void setDestination(Vector3 destination) {
        this.destination = destination;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public  Ray(Vector3 origin, Vector3 direction)
    {
        this.origin =origin;
        this.direction = direction;
    }

}

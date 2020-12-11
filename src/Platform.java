import acm.graphics.GRect;

public class Platform extends GRect {
    private double velocity;

    public Platform(double v, double v1) {
        super(v, v1);
    }

    public Platform(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}

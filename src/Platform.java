import acm.graphics.GImage;
import acm.graphics.GRect;

public class Platform extends GImage {

    public Platform(String s) {
        super(s);
    }

    public Platform(String s, double v, double v1) {
        super(s, v, v1);
    }

    public double getX1(){ return  getBounds().getX(); }

    public double getX2(){ return getBounds().getX() + getBounds().getWidth(); }

    public double getY1(){ return getBounds().getY(); }

    public double getY2(){
        return getBounds().getY() + getBounds().getHeight();
    }

    public double getCenterX(){
        return (getX1() + getX2()) / 2;
    }

    public double getCenterY(){
        return (getY1() + getY2()) / 2;
    }

    public void bounce(Ball ball){
        double deltaX = getCenterX() - ball.getCenterX();
        boolean isNeg = deltaX < 0;
        if(isNeg)deltaX = -deltaX;
        if(deltaX <= 0.05 * getWidth())deltaX = 0.05 * getWidth();
        if(deltaX >= 0.45 * getWidth())deltaX = 0.45 * getWidth();
        double sqVelocity = ball.getVelocity() * ball.getVelocity();
        double koef = 40 * (deltaX * deltaX) / ((getWidth() * getWidth()));
        double sqVelocityY = sqVelocity / (koef + 1);
        double sqVelocityX = sqVelocity - sqVelocityY;
        ball.setVelocityY(-Math.sqrt(sqVelocityY));
        ball.setVelocityX((isNeg ? 1 : -1) * Math.sqrt(sqVelocityX));

    }

}

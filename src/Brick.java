import acm.graphics.GImage;
import acm.graphics.GRect;

public class Brick extends GImage {

    /**Constructor by path*/
    public Brick(String s) {
        super(s);
    }

    /**Constructor by path and coordinates*/
    public Brick(String s, double v, double v1) {
        super(s, v, v1);
    }
}

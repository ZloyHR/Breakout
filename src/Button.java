import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GLabel;

public class Button extends GCompound {
    /**Button label*/
    GLabel text;
    /**Button image*/
    GImage img;

    /**Constructor
     * @param s Image path
     * @param text Button label
     */
    public Button(String s,String text) {
        img = new GImage(s);
        //img.scale(0.75);
        this.add(img);

        this.text = new GLabel(text);
        this.text.setFont(Main.font.deriveFont(24.0f));
        this.text.setLocation(getWidth() / 2 - this.text.getWidth() / 2,getHeight() / 2 + this.text.getHeight()/4);
        this.add(this.text);
    }
}

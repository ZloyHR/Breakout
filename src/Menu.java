import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.util.SoundClip;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Menu {

    /**Menu canvas*/
    GCanvas menuCanvas;

    /**Level buttons*/
    Button level1 = new Button("img/Button.png","Level 1");
    Button level2 = new Button("img/Button.png","Level 2");
    Button level3 = new Button("img/Button.png","Level 3");
    Button level4 = new Button("img/Button.png","Level 4");

    /**Constructor
     * @param canvas Drawing canvas
     */
    public Menu(GCanvas canvas) {
        canvas.removeAll();
        menuCanvas = canvas;
    }

    /**Draws level buttons*/
    public void run(){
        GImage logo = new GImage("img/BREAKOUT.png");
        GImage back = new GImage("img/BackMenu.gif");
        GLabel authors = new GLabel("© Made by Dmytro Hetman and Ivan Dobrovolsky");
        authors.setFont(Main.font.deriveFont(20.0f));
        authors.setColor(Color.gray);
        authors.setLocation(15,menuCanvas.getHeight() - 2.2 * authors.getHeight());
        menuCanvas.add(authors);
        double centerX = menuCanvas.getWidth() / 2 + 35;
        double centerY = menuCanvas.getHeight() / 2 - 10;
        menuCanvas.add(level1);
        menuCanvas.add(level2);
        menuCanvas.add(level3);
        menuCanvas.add(level4);
        menuCanvas.add(logo);
        menuCanvas.add(back);
        back.sendToBack();
        back.scale(menuCanvas.getWidth() / back.getWidth(),menuCanvas.getHeight() / back.getHeight());
        logo.scale(0.5);
        logo.setLocation(centerX - logo.getWidth() / 2,centerY - logo.getHeight() - 20);
        double yHeight = menuCanvas.getHeight() * 0.65;
        level1.setLocation(centerX - 2 * level1.getWidth() - 45,yHeight - 1.25 * level1.getHeight());
        level2.setLocation(centerX + 1 * level1.getWidth() + 45,yHeight - 1.25 * level1.getHeight());
        level3.setLocation(centerX - 2 * level1.getWidth() - 45,yHeight);
        level4.setLocation(centerX + 1 * level1.getWidth() + 45,yHeight);
    }

    /**Figure button clicks*/
    public int mouseClicked(MouseEvent mouseEvent) {
        GObject getElement = menuCanvas.getElementAt(mouseEvent.getX(),mouseEvent.getY());
        int ret = 0;
        if(getElement == level1)ret = 1;
        if(getElement == level2)ret = 2;
        if(getElement == level3)ret = 3;
        if(getElement == level4)ret = 4;
        if(ret != 0){
            SoundClip click = new SoundClip("fx/click.wav");
            click.setVolume(0.4);
            click.play();
        }
        return ret;
    }

}

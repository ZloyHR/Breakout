import acm.graphics.GCanvas;
import acm.graphics.GLabel;
import acm.graphics.GObject;

import java.awt.event.MouseEvent;

public class Menu {

    GCanvas menuCanvas;
    Button level1 = new Button("img/Blade1.png","Level 1");
    Button level2 = new Button("img/Blade1.png","Level 2");
    Button level3 = new Button("img/Blade1.png","Level 3");
    Button level4 = new Button("img/Blade1.png","Level 4");

    public Menu(GCanvas canvas) {
        canvas.removeAll();
        menuCanvas = canvas;
    }

    public void run(){
        GLabel authors = new GLabel("Made by Dmytro Hetman and Ivan Dobrovolsky");
        authors.setFont(Main.font.deriveFont(18.0f));
        authors.setLocation(15,menuCanvas.getHeight() - authors.getHeight());
        menuCanvas.add(authors);
        double centerX = menuCanvas.getWidth() / 2 - 10;
        double centerY = menuCanvas.getHeight() / 2 - 10;
        menuCanvas.add(level1);
        menuCanvas.add(level2);
        menuCanvas.add(level3);
        menuCanvas.add(level4);
        level1.setLocation(centerX - level1.getWidth() - 20,centerY + 10);
        level2.setLocation(centerX + level2.getWidth() + 20,centerY + 10);
        level3.setLocation(centerX - level3.getWidth() - 20,centerY + level1.getHeight() + 20);
        level4.setLocation(centerX + level4.getWidth() + 20,centerY + level2.getHeight() + 20);
    }

    public int mouseClicked(MouseEvent mouseEvent) {
        GObject getElement = menuCanvas.getElementAt(mouseEvent.getX(),mouseEvent.getY());
        if(getElement == level1)return 1;
        if(getElement == level2)return 2;
        if(getElement == level3)return 3;
        if(getElement == level4)return 4;
        return 0;
    }

}

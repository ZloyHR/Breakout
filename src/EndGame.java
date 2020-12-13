import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

import java.awt.event.MouseEvent;

public class EndGame {
    GCanvas endGameCanvas;
    Button con;
    Button end;

    public EndGame(GCanvas canvas) {
        canvas.removeAll();
        endGameCanvas = canvas;
    }

    public void run(){
        GImage back = new GImage("img/BackMenu.gif");
        GLabel score = new GLabel("Your score is " + Game.score.getValue());
        score.setFont(Main.font.deriveFont(24f));
        con = new Button("img/Button.png","Menu");
        end = new Button("img/Button.png","Exit");
        double centerX = endGameCanvas.getWidth() / 2 - 10;
        double centerY = endGameCanvas.getHeight() / 2 - 10;
        con.setLocation(centerX - con.getWidth() - 20,centerY + 10);
        end.setLocation(centerX,centerY + 10);
        score.setLocation(centerX - score.getWidth() / 2,centerY - score.getHeight() / 4);
        endGameCanvas.add(score);
        endGameCanvas.add(con);
        endGameCanvas.add(end);
        endGameCanvas.add(back);
        back.sendToBack();
        back.scale(endGameCanvas.getWidth() / back.getWidth(),endGameCanvas.getHeight() / back.getHeight());
    }

    public int mouseClicked(MouseEvent mouseEvent) {
        GObject getElement = endGameCanvas.getElementAt(mouseEvent.getX(),mouseEvent.getY());
        if(getElement == con)return 1;
        if(getElement == end)return 2;
        return 0;
    }
}

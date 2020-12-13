import acm.graphics.*;

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
        con = new Button("img/Button.png","Menu");
        end = new Button("img/Button.png","Exit");
        double centerX = endGameCanvas.getWidth() / 2 + 35;
        double centerY = endGameCanvas.getHeight() / 2 - 10;
        double yHeight = endGameCanvas.getHeight() * 0.65;
        con.setLocation(centerX - 2 * con.getWidth() - 45,yHeight - 0.95 * con.getHeight());
        end.setLocation(centerX + 1 * con.getWidth() + 45,yHeight - 0.95 * con.getHeight());
        endGameCanvas.add(con);
        endGameCanvas.add(end);

        endGameCanvas.add(back);
        GLabel scoreText = new GLabel("Your score is" );
        scoreText.setFont(Main.font.deriveFont(36f));
        GLabel scoreVal = new GLabel("" + (int)Game.score.getValue());
        scoreVal.setFont(Main.font.deriveFont(36f));
        scoreText.setLocation(centerX - 1.25 * scoreText.getWidth(),yHeight - 5 * scoreText.getHeight());
        scoreVal.setLocation(centerX - 0.75 * scoreText.getWidth(),yHeight - 4 * scoreVal.getHeight());
        endGameCanvas.add(scoreText);
        endGameCanvas.add(scoreVal);

        GLabel timeText = new GLabel("You played " );
        timeText.setFont(Main.font.deriveFont(36f));
        int sec = (int)(Game.time.getValue() / 1000);
        int min = sec / 60;
        sec -= min * 60;
        GLabel timeVal = new GLabel(Game.time.getTwoDigit(min) + ":" + Game.time.getTwoDigit(sec));
        timeVal.setFont(Main.font.deriveFont(36f));
        timeText.setLocation(centerX + 0.3 * timeText.getWidth(),yHeight - 5 * timeText.getHeight());
        timeVal.setLocation(centerX + 0.5 * timeText.getWidth(),yHeight - 4 * timeVal.getHeight());
        endGameCanvas.add(timeText);
        endGameCanvas.add(timeVal);

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

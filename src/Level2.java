import acm.graphics.GCanvas;

public class Level2 extends BrickGenerator{
    @Override
    public int generate(GCanvas canvas) {
        int cnt = 1;
        for(int i = 0;i <= 7; ++i){
            x = Main.GAME_X1 - width - Main.BRICK_SEP;
            for(int j = 0; j < cnt; ++j){
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
                x += width + Main.BRICK_SEP;
            }
            y += height + Main.BRICK_SEP;
            cnt += 2;
        }
        return brickCount;
    }
}

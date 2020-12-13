import acm.graphics.GCanvas;

public class Level3 extends BrickGenerator{
    @Override
    /**Level3 generator*/
    public int generate(GCanvas canvas) {
        super.generate(canvas);
        int cnt;
        for(int i = 0;i <= 7; ++i){
            cnt = 2 * (i + 1);
            if(i >= 4) cnt = 2 * ((7 - i) + 1);
            double rawWidth = (width + Game.BRICK_SEP) * (cnt - 1) + width;
            x = (Game.GAME_X1 + Game.GAME_X2) / 2 - rawWidth / 2;
            for(int j = 0; j < cnt; ++j){
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
                x += width + Game.BRICK_SEP;
            }
            y += height + Game.BRICK_SEP;
        }
        y = Game.GAME_Y1;
        for(int i = 0;i <= 7; ++i){
            cnt = i + 1;
            if(i >= 4) cnt = (7 - i) + 1;
            if(cnt >= 3)cnt = 3;
            double rawWidth = (width + Game.BRICK_SEP) * (cnt - 1) + width;
            x = 0;
            for(int j = 0; j < cnt; ++j){
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
                x += width + Game.BRICK_SEP;
            }
            x = Game.GAME_X2 - rawWidth;
            for(int j = 0; j < cnt; ++j){
                Brick brick = new Brick("img/WoodenBrick" + (i / 2 + 1) + ".png");
                brick.setLocation(x,y);
                brick.scale(width/150,height/30);
                canvas.add(brick);
                brickCount++;
                x += width + Game.BRICK_SEP;
            }
            y += height + Game.BRICK_SEP;
        }
        return brickCount;
    }
}

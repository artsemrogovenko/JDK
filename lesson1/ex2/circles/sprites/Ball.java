package circles.sprites;

import common.MainCanvas;
import common.Sprite;

import java.awt.*;

public class Ball extends Sprite {
    private static final MyRandom random = new MyRandom();
    private Color color;
    private float vX;
    private float vY;

    public Ball(int x, int y){
        super(x, y);
        halfHeight = 20 + random.nextFloat(50);
        halfWidth = halfHeight;
        color = new Color(random.nextInt(-2147483648,2147483647));
        vX = 100 + random.nextFloat(200);
        vY = 100 + random.nextFloat(200);
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        x += vX * deltaTime;
        y += vY * deltaTime;

        if (getLeft() < canvas.getLeft()){
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()){
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()){
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }
}

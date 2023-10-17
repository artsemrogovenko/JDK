package draw_pic.sprites;

import common.MainCanvas;
import common.Sprite;
import draw_pic.MyRandom;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Picture extends Sprite {
    private static final MyRandom random = new MyRandom();
    private Image image;
    private float vX;
    private float vY;

    public Picture(int x, int y){
        super(x, y);
        halfHeight = 64;
        halfWidth = 62;
        try {
            image = ImageIO.read(new File("ex2\\draw_pic\\Smile.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        g.drawImage(image, (int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight(), null);
    }
}

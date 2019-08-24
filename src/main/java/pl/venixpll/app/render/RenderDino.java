package pl.venixpll.app.render;

import lombok.Data;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.app.ChromeDinoAPP;

import java.awt.*;

@Data
public class RenderDino implements IRender{

    private final int posX;
    private int posY;

    private Rectangle boundbox;

    private int motionY = 0;
    private int gravity = 1;
    private boolean onGround = true;

    public RenderDino(int posX, int posY){
        this.posX = posX;
        this.posY = posY;

        boundbox = new Rectangle(posX,posY,40,60);
    }

    private int k = 0;

    @Override
    public void tick(GameContainer container, ChromeDinoAPP app) {
        if(!app.getFloor().getBoundbox().intersects(this.boundbox)){
            k++;
            int w = 1;
            if(Keyboard.isKeyDown(57)){
                w = 2;
            }
            if(k % w== 0) {
                k = 0;
                motionY -= gravity;
                onGround = false;
            }
        }else{
            if(motionY < 0) {
                onGround = true;
                posY = container.getHeight() - 209;
                motionY = 0;
            }
        }
        posY -= motionY;
        boundbox.setBounds(posX,posY,40,60);
    }

    @Override
    public void draw(Graphics graphics, GameContainer container, ChromeDinoAPP app) {
        graphics.setColor(Color.red);
        graphics.fillRect(posX,posY,40,60);
        graphics.setColor(Color.white);
    }

    public void keyPressed(int key){
        if(key == 57){ //Space;
            if(onGround) {
                motionY = 13;
            }
        }
    }
}

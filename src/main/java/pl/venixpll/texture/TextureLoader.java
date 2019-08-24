package pl.venixpll.texture;

import lombok.Data;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

@Data
public class TextureLoader {

    private Image cactus;
    private boolean ready = false;

    public void init() throws SlickException {
        //this.cactus = new Image("assets/cactus.png");
        ready = true;
    }
}

package pl.venixpll.app;

import com.sun.prism.shader.Texture_Color_Loader;
import lombok.Data;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.render.RenderCactus;
import pl.venixpll.app.render.RenderDino;
import pl.venixpll.app.render.RenderFloor;
import pl.venixpll.texture.TextureLoader;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

@Data
public class ChromeDinoAPP extends BasicGame {

    public ChromeDinoAPP(String title) {
        super(title);
    }

    private TextureLoader loader;

    private RenderDino dino;
    private RenderFloor floor;

    private int ticks = 0;

    private boolean paused = true;
    private boolean gameOver = true;

    private GameContainer container;

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.container = gameContainer;
        loader = new TextureLoader();
        dino = new RenderDino(100,gameContainer.getHeight() - 210);
        floor = new RenderFloor(0,gameContainer.getHeight() - 150,gameContainer.getWidth(),150);
        loader.init();
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(loader.isReady()){
            if(!paused) {
                ticks++;
                if (ticks % 75 == 0) {
                    final Random r = new Random();
                    final int amount = r.nextInt(4) + 1;
                    IntStream.range(0, amount).forEach(w -> {
                        cactusList.add(new RenderCactus(gameContainer.getWidth() + r.nextInt(135), gameContainer.getHeight() - 180));
                    });
                }

                cactusList.forEach(c -> {
                    c.tick(gameContainer, this);
                    if (c.getPosX() < -30) {
                        cactusList.remove(c);
                    }
                    if (c.getBoundbox().intersects(dino.getBoundbox())) {
                        this.setPaused(true);
                        this.setGameOver(true);
                    }
                });
                dino.tick(gameContainer, this);
            }
        }
    }

    private List<RenderCactus> cactusList = new CopyOnWriteArrayList<>();

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(loader.isReady()){
            cactusList.forEach(c -> c.draw(graphics,gameContainer,this));
            floor.draw(graphics,gameContainer,this);
            dino.draw(graphics,gameContainer,this);
            graphics.drawString("Score: " + ticks,10,22);
            if(paused && !gameOver){
                graphics.drawString("PAUSED",(gameContainer.getWidth() / 2) -graphics.getFont().getWidth("PAUSED") / 2,gameContainer.getHeight() / 2);
            }else if(gameOver && paused){
                graphics.drawString("PRESS SPACE TO PLAY",(gameContainer.getWidth() / 2) -graphics.getFont().getWidth("PRESS SPACE TO PLAY") / 2,gameContainer.getHeight() / 2);
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == 1 && this.gameOver) return;
        if(this.gameOver && key == 57){
            ticks = 0;
            cactusList.clear();
            dino = new RenderDino(100, container.getHeight() - 210);
            this.gameOver = false;
            this.paused = false;
        }
        if(key == 1){
            paused = !paused;
        }else {
            dino.keyPressed(key);
        }
    }
}

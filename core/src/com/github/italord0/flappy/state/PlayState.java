package com.github.italord0.flappy.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.italord0.flappy.FlappyGame;
import com.github.italord0.flappy.object.Bird;
import com.github.italord0.flappy.object.Tube;

public class PlayState extends State {
    private Bird bird;
    private Tube tube;
    private Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 200);
        tube = new Tube(100);
        background = new Texture("bg.png");
        camera.setToOrtho(false, FlappyGame.WIDTH / 2f, FlappyGame.HEIGHT / 2f);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bird.update(dt);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        spriteBatch.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
        spriteBatch.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

package com.github.italord0.flappy.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.github.italord0.flappy.FlappyGame;
import com.github.italord0.flappy.object.Bird;
import com.github.italord0.flappy.object.Tube;

public class PlayState extends State {

    private static final int CAMERA_OFFSET = 80;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private final Bird bird;
    private final Array<Tube> tubes;
    private final Texture background;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 200);
        tubes = new Array<>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

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

        camera.position.x = bird.getPosition().x + CAMERA_OFFSET;

        for (Tube tube : tubes) {
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.replace(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getCollider())) {
                gameStateManager.set(new PlayState(gameStateManager));
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - (camera.viewportWidth / 2), 0);
        spriteBatch.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

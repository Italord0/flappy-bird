package com.github.italord0.flappy.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.github.italord0.flappy.FlappyGame;
import com.github.italord0.flappy.object.Bird;
import com.github.italord0.flappy.object.Ground;
import com.github.italord0.flappy.object.Tube;

public class PlayState extends State {

    private static final int CAMERA_OFFSET = 80;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;
    private final Bird bird;
    private final Array<Tube> tubes;
    private final Texture background;
    private final Ground ground;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);
        bird = new Bird(50, 200);
        tubes = new Array<>();

        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        background = new Texture("bg.png");
        camera.setToOrtho(false, FlappyGame.WIDTH / 2f, FlappyGame.HEIGHT / 2f);
        ground = new Ground(camera);
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
        updateGround();
        bird.update(dt);

        camera.position.x = bird.getPosition().x + CAMERA_OFFSET;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.replace(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(bird.getCollider())) {
                gameStateManager.set(new PlayState(gameStateManager));
                System.out.println("Colliding Tube");
            }
        }
        if (ground.collides(bird.getCollider())) {
            gameStateManager.set(new PlayState(gameStateManager));
            System.out.println("Colliding ground");
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
        spriteBatch.draw(ground.getGroundTexture(), ground.getGroundPos1().x, ground.getGroundPos1().y);
        spriteBatch.draw(ground.getGroundTexture(), ground.getGroundPos2().x, ground.getGroundPos2().y);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Play State Disposed");
    }

    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > ground.getGroundPos1().x + ground.getGroundTexture().getWidth()) {
            ground.getGroundPos1().add(ground.getGroundTexture().getWidth() * 2, 0);
        }
        if (camera.position.x - (camera.viewportWidth / 2) > ground.getGroundPos2().x + ground.getGroundTexture().getWidth()) {
            ground.getGroundPos2().add(ground.getGroundTexture().getWidth() * 2, 0);
        }
    }
}

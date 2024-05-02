package com.github.italord0.flappy.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.github.italord0.flappy.animation.Animation;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int SPEED = 100;
    private static final int JUMP_FORCE = 300;
    private final Vector3 position;
    private final Vector3 velocity;
    private final Rectangle collider;

    private final Animation birdAnimation;
    private final Texture texture;
    private Sound flapSound;

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        collider = new Rectangle(x, y, texture.getWidth() / 3f, texture.getHeight());
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt) {
        birdAnimation.update(dt);
        if (position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(SPEED * dt, velocity.y, 0);
        if (position.y < 0) {
            position.y = 0;
        }
        velocity.scl(1 / dt);
        collider.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = JUMP_FORCE;
        flapSound.play(0.5f);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void dispose() {
        texture.dispose();
        flapSound.dispose();
    }
}

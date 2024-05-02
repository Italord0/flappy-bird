package com.github.italord0.flappy.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -20;
    private static final int SPEED = 100;
    private static final int JUMP_FORCE = 400;
    private final Vector3 position;
    private final Vector3 velocity;
    private final Rectangle collider;
    private final Texture birdTexture;

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return birdTexture;
    }

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        birdTexture = new Texture("bird.png");
        collider = new Rectangle(x, y, birdTexture.getWidth(), birdTexture.getHeight());
    }

    public void update(float dt) {
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
    }

    public Rectangle getCollider(){
        return collider;
    }
}

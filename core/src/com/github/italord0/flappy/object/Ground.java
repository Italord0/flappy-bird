package com.github.italord0.flappy.object;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground {

    private static final int GROUND_Y_OFFSET = -40;
    private final Texture groundTexture;

    private final Vector2 groundPos1, groundPos2;

    private final Rectangle groundCollider1, groundCollider2;

    public Ground(OrthographicCamera camera) {
        groundTexture = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + groundTexture.getWidth(), GROUND_Y_OFFSET);

        groundCollider1 = new Rectangle(groundPos1.x, groundPos1.y, groundTexture.getWidth(), groundTexture.getHeight());
        groundCollider2 = new Rectangle(groundPos2.x, groundPos2.y, groundTexture.getWidth(), groundTexture.getHeight());
    }

    public Texture getGroundTexture() {
        return groundTexture;
    }

    public Vector2 getGroundPos1() {
        return groundPos1;
    }

    public Vector2 getGroundPos2() {
        return groundPos2;
    }

    public boolean collides(Rectangle birdCollider) {
        return birdCollider.overlaps(groundCollider1) || birdCollider.overlaps(groundCollider2);
    }
}

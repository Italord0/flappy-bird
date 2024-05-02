package com.github.italord0.flappy.object;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int TUBEGAP = 100;
    private static final int LOWEST_POSITION = 120;
    private final Texture topTube;
    private final Texture bottomTube;
    private final Vector2 posTopTube;
    private final Vector2 posBotTube;
    private final Rectangle colliderTop;
    private final Rectangle colliderBot;
    private final Random random;

    public Tube(float x) {
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        random = new Random();

        posTopTube = new Vector2(x, random.nextInt(FLUCTUATION) + TUBEGAP + LOWEST_POSITION);
        posBotTube = new Vector2(x, posTopTube.y - TUBEGAP - bottomTube.getHeight());

        colliderTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        colliderBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void replace(float x) {
        posTopTube.set(x, random.nextInt(FLUCTUATION) + TUBEGAP + LOWEST_POSITION);
        posBotTube.set(x, posTopTube.y - TUBEGAP - bottomTube.getHeight());
        colliderTop.setPosition(posTopTube.x, posTopTube.y);
        colliderBot.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle birdCollider) {
        return birdCollider.overlaps(colliderTop) || birdCollider.overlaps(colliderBot);
    }
}

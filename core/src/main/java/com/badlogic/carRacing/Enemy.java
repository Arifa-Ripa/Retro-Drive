package com.badlogic.carRacing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;


public class Enemy {
    private Texture texture;
    private Rectangle bounds;
    private Random random;
    private float speed;

    public Enemy(Texture texture){
        this.texture = texture;
        this.random = new Random();
        resetPosition();

    }

    public void draw(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y);
    }

    public Rectangle getBounds(){
        return bounds;
    }

    public void update(){
        bounds.y -= speed;
        if(bounds.y < - 200){
            resetPosition();
        }
    }

    private void resetPosition(){
        bounds = new Rectangle(70 + random.nextInt(3) * 220, 800 + (random.nextInt(5) + 1) * 200, 95, 181);
        speed = 5;
    }
}

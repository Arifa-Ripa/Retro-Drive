package com.badlogic.carRacing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road {
    private Texture texture;
    private float road1, road2;
    private float RoadScrollSpeed;

    public Road(Texture texture, float ScrollSpeed){

        this.texture = texture;
        RoadScrollSpeed = ScrollSpeed;
        road1 = 0;
        road2 = texture.getHeight();
    }


    public void update(float Speed){
        this.RoadScrollSpeed = Speed;
        float delta = Gdx.graphics.getDeltaTime();
        road1 -= RoadScrollSpeed * delta;
        road2 -= RoadScrollSpeed * delta;

        if (road1 + texture.getHeight() <= 0) {
            road1 = road2 + texture.getHeight();
        }
        if (road2 + texture.getHeight() <= 0) {
            road2 = road1 + texture.getHeight();
        }
    }



    public void draw(SpriteBatch batch){
        batch.draw(texture, 0, road1, 700, 800);
        batch.draw(texture, 0, road2, 700, 800);
    }
}

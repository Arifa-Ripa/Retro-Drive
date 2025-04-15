package com.badlogic.carRacing;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Road {
    private Texture texture;

    public Road(Texture texture){

        this.texture = texture;
    }



    public void draw(SpriteBatch batch){
        batch.draw(texture, 0, 0, 700, 800);
    }
}

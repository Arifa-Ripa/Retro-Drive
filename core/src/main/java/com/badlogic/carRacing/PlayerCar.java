package com.badlogic.carRacing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerCar{
    private Texture texture;
    private Rectangle bounds;

    public PlayerCar(Texture texture){
        this.texture = texture;
        bounds = new Rectangle(70, 0, 100, 180);
    }

    public  void draw(SpriteBatch batch){
        batch.draw(texture, bounds.x, bounds.y);
    }

    public void update(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && bounds.x > 70){
             bounds.x -= 220;
        }


        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && bounds.x < 500) {

            bounds.x += 220;
        }


    }




    public boolean collision(Enemy car){
        return bounds.overlaps(car.getBounds());
    }
}

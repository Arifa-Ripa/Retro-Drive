package com.badlogic.carRacing;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerCar extends InputAdapter {
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
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && bounds.x > 70)
            keyDown(Input.Keys.LEFT);
        else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && bounds.x < 500)
            keyDown(Input.Keys.RIGHT);
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode){
            case Input.Keys.LEFT:
                bounds.x -= 220;
                break;

            case Input.Keys.RIGHT:
                bounds.x += 220;
                break;
        }

        return true;
    }

    public boolean collision(Enemy car){
        return bounds.overlaps(car.getBounds());
    }
}

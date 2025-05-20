package com.badlogic.carRacing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;


public class Racing extends ApplicationAdapter {
    private Texture playerTexture, enemyTexture, roadTexture;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private BitmapFont font;
    private long StartTime;
    private int Score ;
    private boolean isGameOver;
    private float ScrollSpeed;
    private float EnemySpeed;


    //objects
    private Road road;
    private PlayerCar playerCar;
    private Array<Enemy> enemyCar;

    @Override
    public void create() {
        roadTexture = new Texture("road.jpg");
        playerTexture = new Texture("Player_Car.png");
        enemyTexture = new Texture("Enemy_Car.png");
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,700, 800);
        font = new BitmapFont();
        Score = 0;
        ScrollSpeed = 200;
        EnemySpeed = 5;


        playerCar = new PlayerCar(playerTexture);
        enemyCar = new Array<>();

        road = new Road(roadTexture, ScrollSpeed);

        isGameOver = true;   //START screen appears first


        for(int i = 0; i < 3; i++){
            enemyCar.add(new Enemy(enemyTexture));
        }


    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);



        batch.begin();


        //start button
        if(isGameOver && Score == 0){

            road.draw(batch);
            font.getData().setScale(2, 2);
            font.setColor(1f, 1f, 1f, 1f);
            font.draw(batch, "START GAME", 258, 400);
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                isGameOver = false;
                StartTime = TimeUtils.millis();
            }
            batch.end();
            return;
        }


        if(!isGameOver) {

            long currentTime = TimeUtils.millis();
            if((currentTime - StartTime) / 100 > 2) {
                if (Score < 50) {
                    Score++;

                } else if (Score < 200 ) {
                    Score += 3;
                    ScrollSpeed = 220;
                    EnemySpeed = 7;

                } else{
                    Score += 5;
                    ScrollSpeed = 250;
                    EnemySpeed = 8;
                }

                StartTime = currentTime;
            }

            road.update(ScrollSpeed);



            playerCar.update();


            for (Enemy enemy : enemyCar) {
                enemy.update(EnemySpeed);

                if (playerCar.collision(enemy)) {
                    System.out.println("GAME OVER!");
                    isGameOver = true;
                }
            }
        }

        road.draw(batch);



        for (Enemy enemy: enemyCar){
            enemy.draw(batch);
        }


        playerCar.draw(batch);

        font.getData().setScale(2, 2);
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, "Score : " + Score, 40, 740);


        //GameOver Screen
        if(isGameOver){

            font.getData().setScale(2, 2);
            font.setColor(1f, 1f, 1f, 1f);
            font.draw(batch, "GAME OVER!", 260, 400);
            font.draw(batch, "PRESS ENTER TO RESTART",160, 360);

            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))  restart();
        }




        batch.end();
    }

    public void restart(){

        isGameOver = false;
        StartTime = TimeUtils.millis();
        Score = 0;
        ScrollSpeed = 200;
        EnemySpeed = 5;

        playerCar = new PlayerCar(playerTexture);

        enemyCar.clear();
        for(int i = 0; i < 3; i++){
            enemyCar.add(new Enemy(enemyTexture));
        }

        road = new Road(roadTexture, ScrollSpeed);


    }

    @Override
    public void dispose() {

        roadTexture.dispose();
    }
}

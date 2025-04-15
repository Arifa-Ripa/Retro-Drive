package com.badlogic.carRacing;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
    final float ScrollSpeed = 200;
    private float road1, road2;


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
        StartTime = TimeUtils.millis();



        playerCar = new PlayerCar(playerTexture);
        enemyCar = new Array<>();

        road1 = 0;
        road2 = roadTexture.getHeight();

        isGameOver = false;

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


        if(!isGameOver) {

            long currentTime = TimeUtils.millis();
            if((currentTime - StartTime) / 100 > 2) {
                Score++;
                StartTime = currentTime;
            }


            float delta = Gdx.graphics.getDeltaTime();
            road1 -= ScrollSpeed * delta;
            road2 -= ScrollSpeed * delta;

            if (road1 + roadTexture.getHeight() <= 0) {
                road1 = road2 + roadTexture.getHeight();
            }
            if (road2 + roadTexture.getHeight() <= 0) {
                road2 = road1 + roadTexture.getHeight();
            }



            playerCar.update();

            for (Enemy enemy : enemyCar) {
                enemy.update();

                if (playerCar.collision(enemy)) {
                    System.out.println("GAME OVER!");
                    isGameOver = true;
                }
            }
        }

        batch.draw(roadTexture, 0, road1, 700, 800);
        batch.draw(roadTexture, 0, road2, 700, 800);

        playerCar.draw(batch);

        for (Enemy enemy: enemyCar){
            enemy.draw(batch);
        }

        font.getData().setScale(2, 2);
        font.setColor(1f, 1f, 1f, 1f);
        font.draw(batch, "Score : " + Score, 40, 740);

        if(isGameOver){

            font.getData().setScale(2, 2);
            font.setColor(1f, 1f, 1f, 1f);
            font.draw(batch, "GAME OVER!", 260, 400);
        }








        batch.end();
    }

    @Override
    public void dispose() {
        roadTexture.dispose();
    }
}

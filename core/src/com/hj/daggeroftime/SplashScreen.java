package com.hj.daggeroftime;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hj.daggeroftime.Screens.MenuScreen;
import com.hj.daggeroftime.Screens.PlayScreen;

public class SplashScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Texture texture;
    private DaggerOfTIme game;
    public static int timeElapsed ;

    public SplashScreen(DaggerOfTIme game){

        this.game = game;
        spriteBatch = new SpriteBatch();
        texture = new Texture("splash.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        timeElapsed +=1;
        System.out.println("Deltime  " + delta + "Time Elapsed " + timeElapsed);
        Gdx.gl.glClearColor(23,100,200,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin(); // open batch file
        game.batch.draw(texture,0,0); // source , x, y
        game.batch.end(); // close the batch

        if(timeElapsed > 200){

            System.out.println("Hello");
            game.setScreen(new MenuScreen(game));
            this.dispose();
        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        texture.dispose();
    }
}

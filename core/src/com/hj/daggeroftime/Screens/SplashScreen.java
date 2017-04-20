package com.hj.daggeroftime.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.hj.daggeroftime.DaggerOfTime;


public class SplashScreen implements Screen {

    //private SpriteBatch spriteBatch;
    private Texture texture;
    private DaggerOfTime game;
    public static int timeElapsed ;
    private Sprite sprite;
    private float userScreenWidth;
    private float userScreenHeight;

    public SplashScreen(DaggerOfTime game) {

        this.game = game;
        texture = new Texture("story/logo.png");
        sprite = new Sprite(texture);
        setScreenWidth();
        setUserScreenHeight();
        sprite.setSize(getScreenWidth(),getHeight());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        timeElapsed +=1;
        Gdx.gl.glClearColor(23,100,200,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin(); // open batch file
        sprite.draw(game.batch);
        game.batch.end(); // close the batch

        if(timeElapsed > 200){

           // game.setScreen(new LevelPicker(game));
           game.setScreen(new MainMenuScreen(game));
            this.dispose();
        } //end if
    } //End render

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
        texture.dispose();
    }

    public void setScreenWidth() {

        userScreenWidth = Gdx.graphics.getWidth();
    }

    public float getScreenWidth(){
        return userScreenWidth;
    }

    public void setUserScreenHeight(){
        userScreenHeight = Gdx.graphics.getHeight();
    }

    public float getHeight(){
        return userScreenHeight;
    }
} //End SplashScreen

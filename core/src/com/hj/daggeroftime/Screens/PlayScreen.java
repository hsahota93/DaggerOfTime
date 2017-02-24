package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;

public class PlayScreen implements Screen{

    private DaggerOfTime game;
    Texture texture;
    private Viewport gameCam;

    public PlayScreen(DaggerOfTime game) {

        this.game = game;
        texture = new Texture("Pictures/Acid.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 0 , 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(texture, 0, 0);
        game.batch.end();
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

    }
}

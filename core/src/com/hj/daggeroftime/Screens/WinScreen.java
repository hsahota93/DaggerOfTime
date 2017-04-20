package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by Harman on 4/17/17.
 */

public class WinScreen implements Screen {

    private Viewport viewport;
    private Stage stage;
    private Game game;

    public WinScreen(Game game) {

        this.game = game;
        viewport = new FitViewport(DaggerOfTime.screenWidth, DaggerOfTime.screenHeight, new OrthographicCamera());
        stage = new Stage(viewport, ((DaggerOfTime) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        //Creating table to keep items inside the stage organized
        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("You beat the game!!!", font);

        table.add(gameOverLabel).expandX();
        table.row();

        stage.addActor(table);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.justTouched()) {

            game.setScreen(new LevelPicker((DaggerOfTime) game));
            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
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

        stage.dispose();
    }
}
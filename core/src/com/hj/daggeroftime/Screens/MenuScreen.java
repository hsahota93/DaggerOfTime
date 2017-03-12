package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hj.daggeroftime.DaggerOfTime;


/**
 * Created by jacob on 2/24/2017.
 */
public class MenuScreen implements Screen {

    private DaggerOfTime game;
    private Stage stage;
    private TextButton levelOneButton;
    private TextButton levelTwoButton;
    private  static final int buttonWidth = 500;
    private  static final int buttonHeight = 100;

    public MenuScreen(DaggerOfTime game) {

        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // to pass input to the stage
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

         levelOneButton = new TextButton("Level One",skin);
         levelTwoButton = new TextButton("Level Two", skin);

        // positioning the buttons on the screen
        levelOneButton.setPosition( (Gdx.graphics.getWidth()/2) - (buttonWidth/2) ,(Gdx.graphics.getHeight()/2) );
        levelTwoButton.setPosition((Gdx.graphics.getWidth()/2) - (buttonWidth/2) ,(Gdx.graphics.getHeight()/2) - (buttonHeight+10));

        // setting the size of the buttons
        levelOneButton.setSize(buttonWidth,buttonHeight);
        levelTwoButton.setSize(buttonWidth,buttonHeight);

        stage.addActor(levelOneButton); // adding to the stage
        stage.addActor(levelTwoButton);

        //click event listener for levelone button
        levelOneButton.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                changeScreen("Levels/level1.tmx");
            }
        });

        // click event listeber for leveltwo button
        levelTwoButton.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                changeScreen("Levels/level2.tmx");
            }
        });
        }


    /* @param level: name of the sprite sheet
    *   Set the screen to the 'PlayScreen'
    *   and load the proper sprite sheet.
    * */
    public void changeScreen(String level) {

        game.setScreen(new PlayScreen(game, level));
        this.dispose();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height);
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

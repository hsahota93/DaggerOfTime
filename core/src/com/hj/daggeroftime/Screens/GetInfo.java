package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by jacob on 4/19/2017.
 */
public class GetInfo implements Screen {

    private DaggerOfTime game;
    private TextField nameField;
    private Stage stage;
    private TextButton button;
    private ImageButton goButton;
    private Texture texture;
    private Texture texture2;
    public static String name = "TEST";

    //Testing
    public GetInfo() {
    }

    public GetInfo(DaggerOfTime game) {

        int padding = 10;
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // to pass input to the stage

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        button = new TextButton("Enter your name", skin);
        button.setTouchable(Touchable.disabled);
        nameField = new TextField("", skin);

        texture = new Texture("Button/playButton.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        button.setColor(Color.BLUE);

        goButton = new ImageButton(drawable);

        goButton.setWidth(100);
        goButton.setHeight(100);
        button.setPosition((Gdx.graphics.getWidth() / 2) - nameField.getWidth() / 2, (Gdx.graphics.getHeight() / 2) + nameField.getHeight() / 2);

        nameField.setPosition((Gdx.graphics.getWidth() / 2) - nameField.getWidth() / 2, (Gdx.graphics.getHeight() / 2) - (button.getHeight() + padding) / 2);
        goButton.setPosition(nameField.getX() + nameField.getWidth(), nameField.getY());

        stage.addActor(button);
        stage.addActor(nameField);
        stage.addActor(goButton);


        goButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                DaggerOfTime.assetManager.get("Audio/Sounds/Click.wav", Sound.class).play();
                name = nameField.getText().toString();
                setScreen();
            }


        });


    }

    public void setScreen() {

        game.setScreen(new LevelPicker(game));
        this.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
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

        texture.dispose();
        stage.dispose();

    }
}

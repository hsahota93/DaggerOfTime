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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.hj.daggeroftime.DaggerOfTime;


/**
 * Created by jacob on 2/24/2017.
 */
public class MainMenuScreen implements Screen {

    private DaggerOfTime game;
    private Stage stage;
    private TextButton playButton;
    private TextButton highScoreListButton;
    private TextButton quitButton;
    private ImageButton playBtn;
    private static final int buttonWidth = 500;

    private static final int buttonHeight = 100;

    public MainMenuScreen(DaggerOfTime game) {
        int top, right, bottom;
        top = bottom = right = 10;
        int left = 50;
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage); // to pass input to the stage
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Texture texture2 = new Texture("Button/playB4.png");
        final Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(texture2));


        playBtn = new ImageButton(drawable2);

        playButton = new TextButton("Play", skin);
        playButton.setColor(Color.DARK_GRAY);
        highScoreListButton = new TextButton("High Scores", skin);
        highScoreListButton.setColor(Color.DARK_GRAY);
        quitButton = new TextButton("Quit", skin);
        quitButton.setColor(Color.DARK_GRAY);

        Table table = new Table();
        table.center();
        Texture texture = new Texture("mainMenu.PNG");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        table.setBackground(drawable);
        table.setFillParent(true);

        table.row().pad(top, left, bottom, right);
        table.add(playBtn).expandX().left().width(200);
        table.row();

        table.row().pad(top, left, bottom, right);
        table.add(playButton).width(120).expandX().left();
        table.row().pad(10, 50, 10, 10);

        table.add(highScoreListButton).width(120).expandX().left();
        table.row().padLeft(50);
        table.add(quitButton).width(120).expandX().left();


        stage.addActor(table);


        //click event listener for play button
        playButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                DaggerOfTime.assetManager.get("Audio/Sounds/Click.wav", Sound.class).play();
                changeToPlayMode();

            }
        });

        // click event listener ti view high scores
        highScoreListButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                DaggerOfTime.assetManager.get("Audio/Sounds/Click.wav", Sound.class).play();
                changeToViewHighScores();
            }
        });

        //Click listener to quit the game
        quitButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

                DaggerOfTime.assetManager.get("Audio/Sounds/Click.wav", Sound.class).play();
                Gdx.app.exit();
            }
        });
    }

    /* @param level: name of the sprite sheet
    *   Set the screen to the 'PlayScreen'
    *   and load the proper sprite sheet.
    * */
    public void changeToPlayMode() {

        // game.setScreen(new LevelPicker(game));
        game.setScreen(new GetInfo(game));
        this.dispose();
    }

    public void changeToViewHighScores() {

        game.setScreen(new DisplayHighScore(game));
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
        stage.dispose();
    }
}

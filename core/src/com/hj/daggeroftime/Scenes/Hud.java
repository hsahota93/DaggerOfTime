package com.hj.daggeroftime.Scenes;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTIme;
import com.hj.daggeroftime.Screens.PlayScreen;

import java.util.ArrayList;

/**
 * Created by jacob on 2/23/2017.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdoenLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label DaggerofTimeLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(DaggerOfTIme.screenWidth, DaggerOfTIme.screenHeight, new OrthographicCamera());

        stage = new Stage(viewport,sb);
        Table table = new Table(); // creating table to keep items inside the stage organized
        table.top();
        table.setFillParent(true);

        countdoenLabel = new Label(String.format("%3d",worldTimer ), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        scoreLabel = new Label(String.format("%6d",score ), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        levelLabel = new Label("2-2", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        worldLabel = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        DaggerofTimeLabel  = new Label("Dagger Of Time", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));

        //adding labels to the table
        table.add(DaggerofTimeLabel).expandX().padTop(20);
        table.add(worldLabel).expandX().padTop(20);
        table.add(timeLabel).expandX().padTop(20);

        table.row();

        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdoenLabel).expandX();


        stage.addActor(table); // adding the tabel to the stage
    }



}

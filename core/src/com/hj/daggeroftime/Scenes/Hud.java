package com.hj.daggeroftime.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.hj.daggeroftime.DaggerOfTime;

/**
 * Created by jacob on 2/23/2017.
 */
public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldTimer;
    private float timeCount;
    public static Integer score;

    private Label countDownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label DaggerofTimeLabel;

    public Hud(SpriteBatch sb, String level) {
        
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(DaggerOfTime.screenWidth, DaggerOfTime.screenHeight, new OrthographicCamera());

        stage = new Stage(viewport,sb);
        Table table = new Table(); // creating table to keep items inside the stage organized
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%3d",worldTimer), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        scoreLabel = new Label(String.format("%6d",score ), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        worldLabel = new Label("World", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        DaggerofTimeLabel  = new Label("Dagger Of Time", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));

        //adding labels to the table
        table.add(DaggerofTimeLabel).expandX().padTop(20);
        table.add(worldLabel).expandX().padTop(20);
        table.add(timeLabel).expandX().padTop(20);

        table.row();

        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();


        stage.addActor(table); // adding the table to the stage
    }

    public void update(float dt) {

        timeCount += dt;

        if(timeCount >= 1) {

            worldTimer--;
            countDownLabel.setText(String.format("%3d",worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int points) {

        score += points;
        scoreLabel.setText(String.format("%6d",score ));
    }

    @Override
    public void dispose() {

        stage.dispose();
    }
}

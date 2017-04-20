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
    private Integer timeLimit = 120;
    private Label countDownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label DaggerOfTimeLabel;
    private Label healthLabel;
    private Label currentHealthLabel;
    private Integer princeHealth;

    public Hud(SpriteBatch sb, String level) {

        initiateVariables();
        viewport = new FitViewport(DaggerOfTime.screenWidth, DaggerOfTime.screenHeight, new OrthographicCamera());

        stage = new Stage(viewport, sb);
        Table table = new Table(); // creating table to keep items inside the stage organized
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("%3d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        scoreLabel = new Label(String.format("%6d", score), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        timeLabel = new Label("Time", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        levelLabel = new Label("" + DaggerOfTime.maxLives, new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        worldLabel = new Label("Lives", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        DaggerOfTimeLabel = new Label("Score", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        healthLabel = new Label("Health", new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        currentHealthLabel = new Label(String.format("%3d", princeHealth), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));

        //adding labels to the table
        table.add(DaggerOfTimeLabel).expandX().padTop(20);
        table.add(worldLabel).expandX().padTop(20);
        table.add(timeLabel).expandX().padTop(20);
        table.add(healthLabel).expandX().padTop(20);

        table.row();

        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();
        table.add(currentHealthLabel).expandX();

        stage.addActor(table); // adding the table to the stage
    }

    public static void addScore(int points) {
        score += points;
    }

    public static void setScoreLabel() {
        scoreLabel.setText(String.format("%6d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    public void initiateVariables() {

        princeHealth = 100;
        worldTimer = timeLimit;
        timeCount = 0;
        score = 0;
    }

    public void update(float dt) {

        timeCount += dt;

        currentHealthLabel.setText(String.format("%3d", princeHealth));

        if (timeCount >= 1) {

            if (worldTimer > 0) {

                worldTimerDecrementer();
            }

            countDownLabel.setText(String.format("%3d", worldTimer));
            timeCount = 0;
        }
    }

    // To decrement the world timer
    private void worldTimerDecrementer() {
        worldTimer--;
    }

    //Returns the worldTimer. Used to see if prince should die.
    public int getTimer() {
        return worldTimer;
    }

    public void updateHealth(int newHealth) {
        princeHealth = newHealth;
    }

}

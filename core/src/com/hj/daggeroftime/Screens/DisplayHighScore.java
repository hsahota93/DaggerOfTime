package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.hj.daggeroftime.DaggerOfTime;

import java.util.Iterator;



/**
 * Created by jacob on 4/16/2017.
 */
public class DisplayHighScore implements Screen {
    private Firebase playerScore;
    private Firebase playerName;

    private Array<String> scoreList;
    private Array<String> nameList;
    Array<String> tempscoreList, tempNameList;

    Button backBatton;

    public Label scoreLabel;
    private Stage stage;

    Table table;
    private  DaggerOfTime game;
    public DisplayHighScore(DaggerOfTime game){
        this.game = game;
        tempscoreList =  new Array<String>();
        tempNameList  =  new Array<String>();

        playerScore = new Firebase("https://daggeroftime-ddc38.firebaseio.com/scoreStore/playerScore");
        playerName = new Firebase("https://daggeroftime-ddc38.firebaseio.com/scoreStore/playerName");

        scoreList = displayScore();
        nameList = displayPlayer();
        stage = new Stage();
        //TODO make the list to wait untill it get the data
        table = new Table(); // creating table to keep items inside the stage organized
        table.top();
        Texture texture = new Texture("table.jfif");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        table.setBackground(drawable);
        table.row().pad(10);
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage); // to pass input to the stage
        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        backBatton = new TextButton("Back",skin);
        backBatton.setColor(Color.BROWN);
        table.add(backBatton).expandX().left();
        table.row();
        scoreLabel = new Label(String.format("Top Scores"), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        table.add(scoreLabel).expandX().right();
        table.row();

        scoreLabel = new Label(String.format("Name"), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        table.add(scoreLabel).expandX();
        scoreLabel = new Label(String.format("Score"), new Label.LabelStyle(new BitmapFont(), Color.VIOLET));
        table.add(scoreLabel).expandX();
        table.row();
        stage.addActor(table);

        backBatton.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("going back");
                setScreen();
            }
        });
    }


    public void  setScreen(){
        game.setScreen(new MainMenuScreen(game));
        this.dispose();

    }
     String value;

    public Array<String> displayScore(){

        playerScore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()){
                    value =  iterator.next().getValue().toString();
                    tempscoreList.add(value);
                    System.out.println(value);
                }
                System.out.println("Size frm gameOver" + tempscoreList.size);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Error");
            }
        });
        System.out.println("Size frm gameOver" + tempscoreList.size);
        return tempscoreList;
    }

    public Array<String> displayPlayer(){

        playerName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()){
                    value =  iterator.next().getValue().toString();
                    tempNameList.add(value);
                    System.out.println(value);
                }
                System.out.println("Size from gameOver" + tempNameList.size);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("Error");
            }
        });
        System.out.println("Size frm gameOver" + tempNameList.size);
        return tempNameList;
    }

    @Override
    public void show() {

    }
boolean status = false;

    @Override
    public void render(float delta) {
        int length;
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(scoreList.size > 0 &&  nameList.size > 0 && status == false) {
            status = true;
            length = scoreList.size > 10? 10 : scoreList.size;
            for (int i = 0; i < length; i++) {
                table.row().pad(10);
                System.out.println("Size of list " + scoreList.size);
                scoreLabel = new Label(String.format( nameList.get(i)), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
                table.add(scoreLabel).expandX();
                scoreLabel = new Label(String.format( scoreList.get(i)), new Label.LabelStyle(new BitmapFont(), Color.GOLD));
                table.add(scoreLabel).expandX();
                table.row();

            }
        }

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

    }
}

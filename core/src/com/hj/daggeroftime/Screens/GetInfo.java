package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
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
    private TextField nameFeild;
    private Stage stage;
    private TextButton button;
    private ImageButton goButton;
    private Texture texture;
    private Texture texture2;
    protected static String name;

    //Testing
    public GetInfo(){}

    public GetInfo(DaggerOfTime game){
        int padding =10;
        this.game = game;
        stage =  new Stage();
        Gdx.input.setInputProcessor(stage); // to pass input to the stage

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        button = new TextButton("Enter your name",skin);
        button.setTouchable(Touchable.disabled);
        nameFeild = new TextField("",skin );

        texture = new Texture("Button/playButton.png");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        button.setColor(Color.BLUE);


        goButton = new ImageButton(drawable);

        goButton.setWidth(100);
        goButton.setHeight(100);
        button.setPosition((Gdx.graphics.getWidth()/2)-nameFeild.getWidth()/2, (Gdx.graphics.getHeight()/2)+ nameFeild.getHeight()/2);

        nameFeild.setPosition((Gdx.graphics.getWidth()/2)-nameFeild.getWidth()/2, (Gdx.graphics.getHeight()/2) - (button.getHeight()+padding)/2);
        goButton.setPosition(nameFeild.getX()+nameFeild.getWidth(), nameFeild.getY());

        stage.addActor(button);
        stage.addActor(nameFeild);
        stage.addActor(goButton);


        goButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {

                System.out.println(nameFeild.getText().toString());
                name = nameFeild.getText().toString();
                setScreen();

            }


        });


    }

    public void setScreen(){
        game.setScreen(new LevelPicker(game));
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

        texture.dispose();
        stage.dispose();

    }
}

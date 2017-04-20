package com.hj.daggeroftime.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import com.hj.daggeroftime.DaggerOfTime;


/**
 * Created by jacob on 4/19/2017.
 */
public class Story implements Screen {

    private DaggerOfTime game;
    private Table picTable;
    private Stage stage;
    Cell p;
    Texture texture1;
    Drawable drawable1;
     Array<Image> imageList;
    Image image;
    Array<Texture> textureArray;
    TextButton skipButton;
    public Story(DaggerOfTime game){

        //Adding the pictures to the list for the slideshow
        textureArray = new Array<Texture>();
        textureArray.add( new Texture("story/pic1.png"));
        textureArray.add( new Texture("story/pic2.png"));
        textureArray.add( new Texture("story/pic3.png"));
        textureArray.add( new Texture("story/pic4.png"));
        textureArray.add( new Texture("story/pic5.png"));
        textureArray.add( new Texture("story/pic6.png"));
        textureArray.add( new Texture("story/pic7.png"));
        textureArray.add( new Texture("story/pic8.png"));
        textureArray.add( new Texture("story/pic9.png"));
        textureArray.add( new Texture("story/pic10.png"));

        imageList = new Array<Image>();
        texture1 = new Texture("story/pic2.png");
        drawable1= new TextureRegionDrawable(new TextureRegion(texture1));
        image = new Image();
        image.setDrawable(drawable1);
        texture1 = new Texture("story/pic3.png");

        for(int i =0; i < textureArray.size; i++) {
            drawable1 = new TextureRegionDrawable(new TextureRegion(textureArray.get(i)));
            image = new Image();
            image.setDrawable(drawable1);
            imageList.add(image);
        }

        this.game = game;
        stage = new Stage();

        picTable = new Table(); // creating table to keep items inside the stage organized
        picTable.top().center();
        Texture texture = new Texture("table.jfif");
        Drawable drawable = new TextureRegionDrawable(new TextureRegion(texture));
        picTable.setBackground(drawable);
        picTable.row().pad(10);
        picTable.setFillParent(true);
        Gdx.input.setInputProcessor(stage); // to pass input to the stage

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        texture1 = new Texture("story/pic1.png");
        drawable1 = new TextureRegionDrawable(new TextureRegion(texture1));
        image = new Image();
        image.setDrawable(drawable1);
        skipButton = new TextButton("Skip", skin);

        p = picTable.add(image).width(stage.getWidth()).height(stage.getHeight()-skipButton.getHeight()*2);

        picTable.row();

        picTable.add(skipButton);
        stage.addActor(picTable);

        //CLick listener to skip the story
        skipButton.addListener(new ClickListener() {
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                setScreen();
            }
        });

    }
    //to change the screen
    private void setScreen(){
        game.setScreen(new PlayScreen(game,"Levels/level1.tmx"));
    }

    @Override
    public void show() {

    }
    float timer = 0;
    int index = 1;
    @Override
    public void render(float delta) {
        timer+=delta;
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            // displaying the pics
            while (index < imageList.size && timer > 8) {

                System.out.println("Size:" + imageList.size + "Index: " + index);
                p.setActor(imageList.get(index)).width(stage.getWidth()).height(stage.getHeight()-skipButton.getHeight()*2);
                timer = 0;
                index++;

                    if(index >= imageList.size){
                        index = 0;
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

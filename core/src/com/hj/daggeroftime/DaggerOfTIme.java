package com.hj.daggeroftime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hj.daggeroftime.Screens.PlayScreen;
import com.hj.daggeroftime.Screens.SplashScreen;

public class DaggerOfTime extends Game {

    public SpriteBatch batch;
    public static final int screenWidth = 400;
    public static final int screenHeight = 300;
    public static final float PPM = 100;

    public static final short DEAD_BIT = 0;
    public static final short OBJECT_BIT = 1;
    public static final short PRINCE_BIT = 2;
    public static final short COIN_BIT = 4;
    public static final short ENEMY_BIT = 8;
    public static final short DESTROYED_BIT = 16;
    public static final short HAZARD_BIT = 32;
    public static final short FIRE_BREATH = 64;

    public static AssetManager assetManager;

    //Creates splash screen
    @Override
    public void create() {
        batch = new SpriteBatch();

        //Creating an AssetManager and loading assets
        assetManager = new AssetManager();
        assetManager.load("Audio/Music/LevelOneMusic.mp3", Music.class);
        assetManager.load("Audio/Sounds/Jump.mp3", Sound.class);
        assetManager.load("Audio/Sounds/Damage.mp3", Sound.class);

        //Blocking call. Waits for all assets to finish loading
        assetManager.finishLoading();

        //Creates a PlayScreen
        new PlayScreen(this);
    }

    /*Game logic updates are usually performed here*/
    @Override
    public void render() {
        super.render();
    }

    //Dispose of resources
    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        assetManager.dispose();
    }
}

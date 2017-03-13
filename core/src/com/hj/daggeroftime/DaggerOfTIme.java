package com.hj.daggeroftime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hj.daggeroftime.Screens.SplashScreen;

public class DaggerOfTime extends Game {

	public SpriteBatch batch;
	public static final int screenWidth = 400;
	public static final int screenHeight = 300;
	public static final float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short PRINCE_BIT = 2;
	public static final short COIN_BIT = 4;
	public static final short SPIKE_BIT = 8;
	public static final short DESTROYED_BIT = 16;

	//Creates splash screen
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new SplashScreen(this));
	}

	/*Game logic updates are usually performed here*/
	@Override
	public void render () {
		super.render();
	}

	//Dispose of resources
	@Override
	public void dispose () {
		batch.dispose();
	}
}

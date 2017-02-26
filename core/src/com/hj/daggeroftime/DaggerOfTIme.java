package com.hj.daggeroftime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DaggerOfTime extends Game {

	public SpriteBatch batch;
	public static final int screenWidth = 400;
	public static final int screenHeight = 300;
	public static final float PPM = 100;

	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new SplashScreen(this));
		//setScreen(new PlayScreen(this)); // passing class to the playScreen. That class set will handle with screen
	}

	/*Game logic updates are usually performed here*/
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	//	batch.dispose();
		//img.dispose();
	}
}

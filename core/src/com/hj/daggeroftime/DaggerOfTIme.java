package com.hj.daggeroftime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DaggerOfTime extends Game {

	public SpriteBatch batch;
	public static final int screenWidth = 800;
	public static final int screenHeight = 480;


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

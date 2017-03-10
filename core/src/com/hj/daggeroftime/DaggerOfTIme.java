package com.hj.daggeroftime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hj.daggeroftime.Screens.SplashScreen;

public class DaggerOfTime extends Game {

	public SpriteBatch batch;
	public static final int screenWidth = 400;
	public static final int screenHeight = 300;
	public static final float PPM = 100;


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

	public int add(){
		return 7;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}

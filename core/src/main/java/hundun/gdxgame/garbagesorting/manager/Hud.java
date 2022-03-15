package hundun.gdxgame.garbagesorting.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Hud {

	private BitmapFont bitmapFont;
	private int currentScore = 0;


	public Hud() {
		bitmapFont = new BitmapFont();
	}

	public void draw(Batch batch, float delta) {
		
		//String strFps = "FPS: " + String.valueOf(Gdx.graphics.getFramesPerSecond());
		String strCurrentScore = "Current score: " + String.valueOf((long) currentScore);


		String text =  strCurrentScore;
		bitmapFont.draw(batch, text, 0f, Gdx.graphics.getHeight());
	}
	
	public void addScore() {
	    currentScore ++;
	}

	public void reset() {

		currentScore = 0;
	}
	
	public int getCurrentScore() {
        return currentScore;
    }


	public void dispose() {
		bitmapFont.dispose();
	}
}

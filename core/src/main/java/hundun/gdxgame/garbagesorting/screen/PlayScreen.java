package hundun.gdxgame.garbagesorting.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hundun.gdxgame.garbagesorting.GarbageSortingGame;
import hundun.gdxgame.garbagesorting.entity.GarbageBin;
import hundun.gdxgame.garbagesorting.entity.GarbageItem;
import hundun.gdxgame.garbagesorting.manager.Hud;

public class PlayScreen extends ScreenAdapter {

	private Batch batch;
	private GarbageBin garbageBin;
	private Hud hud;
	private GarbageItem garbageItem;
	GarbageSortingGame game;
	
	public PlayScreen(GarbageSortingGame game) {
	    this.game = game;
		batch = new SpriteBatch();
		garbageBin = new GarbageBin();
		hud = new Hud();
		garbageItem = new GarbageItem();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		
		batch.begin();
		garbageBin.draw(batch, delta);
		hud.draw(batch, delta);
		garbageItem.draw(batch, delta);
		batch.end();

		if (garbageItem.isAtBottom()) {
		    gameOver();
		} else if (garbageBin.getRectangle().overlaps(garbageItem.getRectangle())) {
			hitLogic();
		}
	}
	
	private void hitLogic() {
        if (garbageBin.type == garbageItem.type) {
            hud.addScore();
            garbageItem.reset(false);
            garbageBin.speedUp();
        } else {
            gameOver();
        }
    }
	
	private void gameOver() {
	    int score = hud.getCurrentScore();
        game.toMenuScreen(score);
    }
	
	public void resetAll() {
	    garbageBin.reset();
        hud.reset();
        garbageItem.reset(true);
	}

	@Override
	public void dispose() {
		batch.dispose();
		hud.dispose();
	}
}

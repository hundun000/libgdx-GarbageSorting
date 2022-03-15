package hundun.gdxgame.garbagesorting;

import java.awt.Menu;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import hundun.gdxgame.garbagesorting.manager.AssetHandler;
import hundun.gdxgame.garbagesorting.screen.MenuScreen;
import hundun.gdxgame.garbagesorting.screen.PlayScreen;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all
 * platforms.
 */
public class GarbageSortingGame extends Game {

    public enum GameState {
        MENU,
        PLAY
    }
    
    public GameState gameState = GameState.MENU;
    MenuScreen menuScreen;
    PlayScreen playScreen;
    
	@Override
	public void create() {
	    menuScreen = new MenuScreen(this);
	    playScreen = new PlayScreen(this);
	    
	    menuScreen.reset(0);
		setScreen(menuScreen);
		
	}
	
	
	public void toMenuScreen(int score) {
	    gameState = GameState.MENU;
	    menuScreen.reset(score);
	    setScreen(menuScreen);
    }
	
	public void toPlayScreen() {
	    gameState = GameState.PLAY;
	    playScreen.resetAll();
	    setScreen(playScreen);
    }

	@Override
	public void render() {
		super.render();

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		screen.dispose();
		AssetHandler.dispose();
	}
}
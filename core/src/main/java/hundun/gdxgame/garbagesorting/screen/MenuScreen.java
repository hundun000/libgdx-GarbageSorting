package hundun.gdxgame.garbagesorting.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import hundun.gdxgame.garbagesorting.GarbageSortingGame;
import hundun.gdxgame.garbagesorting.entity.GarbageBin;
import hundun.gdxgame.garbagesorting.entity.GarbageItem;
import hundun.gdxgame.garbagesorting.manager.Hud;

public class MenuScreen extends ScreenAdapter {
    private static final String HOW_TO_PLAY =
            "Rule:" + "\r\n"
            + "Catch garbage in corret sorting." + "\r\n" + "\r\n"
            + "Control:" + "\r\n"
            + "key [A]/[D]: move bin to left/right." + "\r\n"
            + "key [space]: switch bin between recycle/unrecycle." + "\r\n";
	private Batch batch;
	GarbageSortingGame game;
	
	private BitmapFont bitmapFont;
	GlyphLayout layout;
    private float lastScore = 0f;
    private float bestScore = 0f;
    String currentText;
    
	public MenuScreen(GarbageSortingGame game) {
	    this.game = game;
		batch = new SpriteBatch();
		bitmapFont = new BitmapFont();
		layout = new GlyphLayout();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		this.draw(batch, delta);
		batch.end();
		
		if (Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
		    game.toPlayScreen();
		}

	}
	
	public void draw(Batch batch, float delta) {
	    
	    layout.setText(bitmapFont, currentText);
	    final float fontX = (Gdx.graphics.getWidth() - layout.width) / 2;
	    final float fontY = (Gdx.graphics.getHeight() + layout.height) / 2;
	    
        bitmapFont.draw(batch, layout, fontX, fontY);
    }
	
	public void reset(float currentScore) {
        lastScore = currentScore;

        if (lastScore > bestScore) {
            bestScore = lastScore;
        }
        
        String strLastScore = "Last score: " + String.valueOf((long) lastScore) + "";
        String strBestScore = "Best score: " + String.valueOf((long) bestScore) + "";

        currentText = strLastScore + "\r\n" 
        + strBestScore + "\r\n" + "\r\n"
        + "Press any key to start a new game." + "\r\n" + "\r\n" 
        + HOW_TO_PLAY
        ;
    }


	@Override
	public void dispose() {
		batch.dispose();
	}
}

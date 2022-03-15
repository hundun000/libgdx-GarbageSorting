package hundun.gdxgame.garbagesorting.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class AssetHandler {
	public static final TextureAtlas TEXTURE_ATLAS = new TextureAtlas("images.atlas");
	public static int SCALE = 4;

	public static final void dispose() {
		TEXTURE_ATLAS.dispose();
	}
}
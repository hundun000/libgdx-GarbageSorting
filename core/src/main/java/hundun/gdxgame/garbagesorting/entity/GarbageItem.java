package hundun.gdxgame.garbagesorting.entity;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import hundun.gdxgame.garbagesorting.manager.AssetHandler;

public class GarbageItem {
    private static final AtlasRegion RECYCLE_CAN_REGION = AssetHandler.TEXTURE_ATLAS.findRegion("recycle-can");
    private static final AtlasRegion UNRECYCLE_FISHBONE_REGION = AssetHandler.TEXTURE_ATLAS.findRegion("unrecycle-fishbone");
    
	private static final Vector2 SIZE = new Vector2(RECYCLE_CAN_REGION.getRegionWidth() * AssetHandler.SCALE,
	        RECYCLE_CAN_REGION.getRegionHeight() * AssetHandler.SCALE);
	private static final float DEFAULT_SPEED = 200f;
	private static final float SPEED_UP_STEP = 5f;

	
	private float currentSpeed;
	private Random random;
	private Sprite sprite;
	private Vector2 pos;
	private Rectangle rectangle;

	public GarbageType type;
	
	public GarbageItem() {
		random = new Random();
	}
 

	public void draw(Batch batch, float delta) {

		pos.mulAdd(new Vector2(0f, -currentSpeed), delta);


		batch.draw(sprite, pos.x, pos.y, SIZE.x, SIZE.y);
		rectangle.setPosition(pos);

	}
	
	public boolean isAtBottom() {
        return pos.y + SIZE.y <= 0;
    }

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void reset(boolean resetSpeed) {
		if (resetSpeed) {
			currentSpeed = DEFAULT_SPEED;
		} else {
		    currentSpeed += SPEED_UP_STEP;
		}
		type = GarbageType.values()[random.nextInt(GarbageType.values().length)];
		switch (type) {
            case RECYCLE:
                sprite = new Sprite(RECYCLE_CAN_REGION);
                break;
            case UNRECYCLE:
                sprite = new Sprite(UNRECYCLE_FISHBONE_REGION);
                break;
            default:
                break;
        }
		
		sprite.flip(random.nextBoolean(), random.nextBoolean());
		pos = new Vector2(random.nextInt(Gdx.graphics.getWidth() - (int) SIZE.x + 1), Gdx.graphics.getHeight());
		rectangle = new Rectangle(pos.x, pos.y, SIZE.x, SIZE.y);
	}
}

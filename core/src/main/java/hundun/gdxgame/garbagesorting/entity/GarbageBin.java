package hundun.gdxgame.garbagesorting.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import hundun.gdxgame.garbagesorting.manager.AssetHandler;

public class GarbageBin {

	private static final AtlasRegion RECYCLE_BIN_REGION = AssetHandler.TEXTURE_ATLAS.findRegion("recycle-bin");
	private static final AtlasRegion UNRECYCLE_BIN_REGION = AssetHandler.TEXTURE_ATLAS.findRegion("unrecycle-bin");
	private static final Vector2 SIZE = new Vector2(RECYCLE_BIN_REGION.getRegionWidth() * AssetHandler.SCALE,
	        RECYCLE_BIN_REGION.getRegionHeight() * AssetHandler.SCALE);
	private static final float SPEED = 250f;
	private static final float SPEED_UP_STEP = 10f;
	private static final Vector2 NORMAL = new Vector2(1f, 1f).nor();
	private static final float BIN_MIN_Y = 0f;
	
	
	private Vector2 pos;
	private boolean up = false;
	private boolean left = false;
	private boolean right = false;
	private Rectangle rectangle;
	
	private static float currentSpeed;
	public GarbageType type;
	private AtlasRegion currentAtlasRegion;
	
	private void toggleType(GarbageType forceTarget) {
	    if (forceTarget != null) {
	        type = forceTarget;
	    } else {
	        switch (type) {
                case RECYCLE:
                    type = GarbageType.UNRECYCLE;
                    break;
                case UNRECYCLE:
                    type = GarbageType.RECYCLE;
                    break;
                default:
                    break;
            }
	    }
        
        
        switch (type) {
            case RECYCLE:
                currentAtlasRegion = RECYCLE_BIN_REGION;
                break;
            case UNRECYCLE:
                currentAtlasRegion = UNRECYCLE_BIN_REGION;
                break;
            default:
                break;
        }
    }
	
	public GarbageBin() {
		reset();
		rectangle = new Rectangle(pos.x, pos.y, SIZE.x, SIZE.y);
	}
	
	private void logic(float delta) {

	    checkInput();

        if (left) {
            pos.mulAdd(new Vector2(-currentSpeed, 0f).scl(NORMAL), delta);
            pos = clampPos(pos);
        } else if (right) {
            pos.mulAdd(new Vector2(currentSpeed, 0f).scl(NORMAL), delta);
            pos = clampPos(pos);
        } 
        
        rectangle.setPosition(pos);
	}

	public void draw(Batch batch, float delta) {
	    this.logic(delta);
	    
		batch.draw(currentAtlasRegion, pos.x, pos.y, SIZE.x, SIZE.y);

	}
	

	private void checkInput() {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)
		        || Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)
		        || Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)
		        ) {
		    toggleType(null);
		}
		
//		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
//            up = true;
//        } else {
//            up = false;
//        }
		

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			left = true;
		} else {
			left = false;
		}

		if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			right = true;
		} else {
			right = false;
		}
	}

	private Vector2 clampPos(Vector2 pos) {
		float x = pos.x;
		float y = pos.y;

		x = MathUtils.clamp(x, -SIZE.x / 2, Gdx.graphics.getWidth() - SIZE.x / 2);
		y = MathUtils.clamp(y, BIN_MIN_Y, Gdx.graphics.getHeight() - SIZE.y);
		pos.set(x, y);
		return pos;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}

	public void reset() {
	    currentSpeed = SPEED;
		pos = new Vector2((Gdx.graphics.getWidth() - SIZE.x) / 2, BIN_MIN_Y);
		toggleType(GarbageType.UNRECYCLE);
	}

    public void speedUp() {
        currentSpeed += SPEED_UP_STEP;
    }
}

package hundun.gdxgame.garbagesorting.gwt;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import hundun.gdxgame.garbagesorting.GarbageSortingGame;

/** Launches the GWT application. */
public class GwtLauncher extends GwtApplication {
		@Override
		public GwtApplicationConfiguration getConfig () {
			return new GwtApplicationConfiguration(360, 640);
		}

		@Override
		public ApplicationListener createApplicationListener () { 
			return new GarbageSortingGame();
		}
}

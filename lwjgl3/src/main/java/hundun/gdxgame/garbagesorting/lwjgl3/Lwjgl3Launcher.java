package hundun.gdxgame.garbagesorting.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import hundun.gdxgame.garbagesorting.GarbageSortingGame;

/** Launches the desktop (LWJGL3) application. */
public class Lwjgl3Launcher {

	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("GarbageSortingGame");
		config.setWindowedMode(360, 640);
		config.setResizable(false);
//		config.useVsync(false);
//		config.setForegroundFPS(135);
//		config.setFullscreenMode(Lwjgl3ApplicationConfiguration.getDisplayMode());
//		config.useOpenGL3(true, 3, 2); 
		config.disableAudio(true);
		//config.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
		new Lwjgl3Application(new GarbageSortingGame(), config);
	}
}

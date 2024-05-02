package com.github.italord0.flappy;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle(FlappyGame.title);
		config.setWindowedMode(FlappyGame.WIDTH,FlappyGame.HEIGHT);
		new Lwjgl3Application(new FlappyGame(), config);
	}
}

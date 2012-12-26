package com.cengek.thesamething.manager;


import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.screens.Screen;
import com.cengek.thesamething.screens.ScreenGame;
import com.cengek.thesamething.screens.ScreenHelp;
import com.cengek.thesamething.screens.ScreenMain;
import com.cengek.thesamething.screens.ScreenSettings;
import com.cengek.thesamething.screens.ScreenSplash;
import com.cengek.thesamething.util.CharFonts;
import com.cengek.thesamething.util.GameSound;


public class SameThingGame extends GameCanvas implements Runnable{

	
	Graphics g = getGraphics();
	LayerManager lg = new LayerManager();
	boolean isPlaying = true;
	
	private GameSettings gs = new GameSettings();
	private GameSound gsd = new GameSound();
	
	ScreenGame screenGame;
	ScreenMain screenMain;
	ScreenHelp screenHelp;
	ScreenSettings screenSettings;
	ScreenSplash screenSplash;
	
	Screen currentScreen;
	Vector screenVector = new Vector();
	public SameThingGame(MIDlet m) {
		super(true);
		setFullScreenMode(true);

		
		//setting font yang dipakai, ada di folder res apa font nya
		try {
			CharFonts.initialise("euphemia");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//penampung sprites dan semua objek
		//Layer Manager menentukan bagaimana objek dapat ditumpuk
		lg.setViewWindow(0, 0, getWidth(), getHeight());

		//load untuk screen yang akan dipakai
		screenGame = new ScreenGame(g, m, lg, this);
		screenMain = new ScreenMain(g, m, lg, this);
		screenHelp = new ScreenHelp(g, m, lg, this);
		screenSplash = new ScreenSplash(g, m, lg, this);
		screenSettings = new ScreenSettings(g, m, lg, this);
		 
		//taruh di dalam vector screen dipakai
		screenVector.addElement(screenMain);			//0
		screenVector.addElement(screenGame);			//1
		screenVector.addElement(screenSplash);			//2
		screenVector.addElement(screenSettings);		//3
		screenVector.addElement(screenHelp);			//4
		
		System.out.println("Tinggi dari game ini adalah " + getHeight());
		
		//tentukan mana yang pertama kali jadi screen
		((Screen)getScreenVector().elementAt(2)).init();
		setCurrentScreen((Screen)screenVector.elementAt(2));
		
		
		//gsd.playBGM();
	}
	
	public GameSettings getGameSettings(){
		return gs;
	}
	
	public GameSound getGameSound(){
		return gsd;
	}
	
	public void setLg(LayerManager lg){
		this.lg = lg;
	}
	
	public void setCurrentScreen(Screen current){
		currentScreen = current;
	}
	
	public Screen getCurrentScreen(){
		return currentScreen;
	}
	
	public Vector getScreenVector(){
		return screenVector;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run() {

		while (isPlaying) {
			
			//buat sleep untuk waktu game kita
			try {
				Thread.sleep(1000/30);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//TODO: cek aksi yang telah diberikan user
			
			//gambar objek yang kita buat
			draw();
			
		
			//menjalankan apa yang ada di screen
			((Screen)getCurrentScreen()).run();
			
			
			
			
			
			
		}
	}
	
	public void draw(){
		g.setColor(0xffffff);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		//tambahkan disini jika ada yang ingin di draw
		getCurrentScreen().draw();
				
		//gambar semua objek yang ada pada graphics dan taruh di layer manager kita
		lg.paint(g, 0, 0);
		
		
		//bersihkan layarnya
		flushGraphics();
		
	}
	
	protected void pointerPressed(int x, int y) {
		// TODO Auto-generated method stub
		getCurrentScreen().pointerPressed(x, y);
		/*
		if ((Screen)getCurrentScreen() == screenGame){
			screenGame.pointerPressed(x, y);
		}else if ((Screen)getCurrentScreen() == screenMain){
			screenMain.pointerPressed(x, y);
		}*/
		
	}
	
	protected void pointerReleased(int x, int y) {
		// TODO Auto-generated method stub
		getCurrentScreen().pointerReleased(x, y);
	}

}

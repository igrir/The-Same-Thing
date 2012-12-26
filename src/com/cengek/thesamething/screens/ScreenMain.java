package com.cengek.thesamething.screens;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.main.GameMidlet;
import com.cengek.thesamething.manager.SameThingGame;
import com.cengek.thesamething.util.CharFonts;
import com.cengek.thesamething.util.RMS;

public class ScreenMain extends Screen{
	
	private final String GAME_BG = "/game_bg.png";
	private final String LOGO = "/logo_tst.png";
	private final String PLAY_BTN = "/play_btn.png";
	private final String HELP_BTN = "/help_btn.png";
	private final String SETTINGS_BTN = "/settings_btn.png";
	private final String EXIT_BTN = "/exit_btn.png";
	
	Image img_bg, img_logo, img_playBtn, img_helpBtn, img_settingsBtn, img_exitBtn;
	

    private Sprite btn_play, btn_help, btn_settings, btn_exit;
	
	Sprite thingsSprite, highlightSprite;
	

	

	public ScreenMain(Graphics g, MIDlet m, LayerManager lg, SameThingGame host) {
		// TODO Auto-generated constructor stub
		super(g,m, lg, host);
		//setting font yang dipakai, ada di folder res apa font nya
		try {
			CharFonts.initialise("euphemia");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		init();
	}

	public void draw() {

		drawBackground();
		drawLogo(30, 26);
		drawHighestScore();
		
		host.setLg(selflg);
		
	}
	
	public void drawHighestScore(){
		RMS record = ((GameMidlet)getMidlet()).getRMS();
		int highestScore = record.getScore();
		CharFonts.drawString(g, "Highest Score:" + highestScore, 26, 170);
	}
	
	public void drawBackground(){
		g.drawImage(img_bg, 0, 0, 0);
	}

	public void drawLogo(int x, int y){
		g.drawImage(img_logo, x, y, 0);
	}

	public void createBtnPlay(){
		btn_play = new Sprite(img_playBtn);
	}
	
	public void putBtnPlay(){
		selflg.append(btn_play);
	}
	
	public void createBtnHelp(){
		btn_help = new Sprite(img_helpBtn);
	}
	
	public void putBtnHelp(){
		selflg.append(btn_help);
	}
	
	public void createBtnSettings(){
		btn_settings = new Sprite(img_settingsBtn);
	}
	
	public void putBtnSettings(){
		selflg.append(btn_settings);
	}
	
	public void createBtnExit(){
		btn_exit = new Sprite(img_exitBtn);
	}
	
	public void putBtnExit(){
		selflg.append(btn_exit);
	}
	
	/**
	 * Ketika berpindah screen maka sprite yang ditampilkan
	 * pada layer manager harus dihilangkan terlebih dahulu.
	 * Untuk itu setiap sprite yang telah dibuat harus dimasukkan pada prosedur ini
	 * 
	 */
	public void removeSprites(){
		selflg.remove(btn_play);
	}
	
	/**
	 * Prosedur ini membuat dan load semua images yang dipakai
	 */
	public void createImages(){
		try {
			img_bg = Image.createImage(GAME_BG);
			img_logo = Image.createImage(LOGO);
			img_playBtn = Image.createImage(PLAY_BTN);
			img_exitBtn = Image.createImage(EXIT_BTN);
			img_helpBtn = Image.createImage(HELP_BTN);
			img_settingsBtn = Image.createImage(SETTINGS_BTN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		
		createImages();
		
		//tombol main
		createBtnPlay();
		putBtnPlay();
		
		//tombol settings
		createBtnSettings();
		putBtnSettings();
		
		//tombol help
		createBtnHelp();
		putBtnHelp();
		
		//tombol exit
		createBtnExit();
		putBtnExit();
		
		//dan setting-setting custom lainnya
		btn_play.setPosition((host.getWidth()/2)-(btn_play.getWidth()/2), 190);
		btn_exit.setPosition(host.getWidth()-btn_exit.getWidth()-10, host.getHeight()-btn_exit.getHeight()-20);
		btn_settings.setPosition(10, host.getHeight()-btn_settings.getHeight()-20);
		btn_help.setPosition((host.getWidth()/2)-(btn_help.getWidth()/2), host.getHeight()-btn_help.getHeight()-20);
	}
	

	public void pointerPressed(int x, int y){
		System.out.println("xy = " + x + ":" + y);
		
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
		
		if (host.getGameSettings().isMusicOn()) {
			host.getGameSound().repeatBGM();
		}

		
	}

	public void pointerReleased(int x, int y) {
		//cek tekan tombol play
		if (x > btn_play.getX() && x < btn_play.getX()+btn_play.getWidth() &&
		    y > btn_play.getY() && y < btn_play.getY()+btn_play.getHeight()) {
			
			//tuju ke elemen yang kita inginkan
			//element at 1 untuk vector screen adalah gamenya
			
			host.setCurrentScreen((Screen)host.getScreenVector().elementAt(1));
			//mulai sesi baru untuk gamenya
			((ScreenGame)host.getCurrentScreen()).start();
		
		//cek tombol exit
		}else if(x > btn_exit.getX() && x < btn_exit.getX()+btn_exit.getWidth() &&
			    y > btn_exit.getY() && y < btn_exit.getY()+btn_exit.getHeight()){
			getMidlet().notifyDestroyed();
			
		//cek tombol settings
		}else if(x > btn_settings.getX() && x < btn_settings.getX()+btn_settings.getWidth() &&
			    y > btn_settings.getY() && y < btn_settings.getY()+btn_settings.getHeight()){
			//tuju ke elemen yang kita inginkan
			//element at 3 untuk vector screen adalah settings
			
			host.setCurrentScreen((Screen)host.getScreenVector().elementAt(3));
		
		//cek tombol help
		}else if(x > btn_help.getX() && x < btn_help.getX()+btn_help.getWidth() &&
			    y > btn_help.getY() && y < btn_help.getY()+btn_help.getHeight()){
			//tuju ke elemen yang kita inginkan
			//element at 4 untuk vector screen adalah help
			
			host.setCurrentScreen((Screen)host.getScreenVector().elementAt(4));
			//supaya yang gerak-gerak helpnya itu jadi 0 lagi
			((ScreenHelp)host.getCurrentScreen()).start();
		}
	}
	
	public void start(){
		
		if (host.getGameSettings().isMusicOn()) {
			host.getGameSound().playBGM();
		}
		
	}
}

package com.cengek.thesamething.screens;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.main.GameMidlet;
import com.cengek.thesamething.manager.SameThingGame;
import com.cengek.thesamething.util.RMS;

public class ScreenSettings extends Screen {

	

	private final String GAME_BG = "/game_bg.png";
	private final String BACK_BTN = "/back_btn.png";
	private final String MUTE_BTN = "/mute_btn.png";
	private final String RESET_BTN = "/reset_score.png";
	
	Image img_bg, img_backBtn, img_muteBtn, img_resetBtn;
	

    private Sprite spr_btnBack, spr_btnMute, spr_btnReset;
	
	

	public ScreenSettings(Graphics graphics, MIDlet midlet, LayerManager lg,
			SameThingGame host) {
		super(graphics, midlet, lg, host);
		init();
		// TODO Auto-generated constructor stub
	}

	public void draw() {

		drawBackground();
		host.setLg(selflg);
		
	}
	
	public void drawBackground(){
		g.drawImage(img_bg, 0, 0, 0);
	}

	
	/**
	 * Ketika berpindah screen maka sprite yang ditampilkan
	 * pada layer manager harus dihilangkan terlebih dahulu.
	 * Untuk itu setiap sprite yang telah dibuat harus dimasukkan pada prosedur ini
	 * 
	 */
	public void removeSprites(){

	}
	
	/**
	 * Prosedur ini membuat dan load semua images yang dipakai
	 */
	public void createImages(){
		try {
			img_bg = Image.createImage(GAME_BG);
			img_backBtn = Image.createImage(BACK_BTN);
			img_muteBtn = Image.createImage(MUTE_BTN);
			img_resetBtn = Image.createImage(RESET_BTN);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		
		createImages();
		
		createBtnReset();
		putBtnReset();
		
		createBtnMute();
		putBtnMute();
		
		createBtnBack();
		putBtnBack();
		
		//dan setting-setting custom lainnya
		spr_btnBack.setPosition((host.getWidth() / 2) - (spr_btnBack.getWidth() / 2),
				(host.getHeight() - spr_btnBack.getHeight())+15);
		
		spr_btnMute.setPosition((host.getWidth() / 2) - (spr_btnMute.getWidth() / 2),
				100);
		
		spr_btnReset.setPosition((host.getWidth() / 2) - (spr_btnMute.getWidth() / 2),
				177);
	}
	
	public void createBtnMute(){
		spr_btnMute = new Sprite(img_muteBtn, 49, 47);
	}
	
	public void putBtnMute(){
		selflg.append(spr_btnMute);
	}
	
	public void createBtnBack() {
		spr_btnBack = new Sprite(img_backBtn);

	}
	
	public void putBtnBack(){
		selflg.append(spr_btnBack);
	}

	public void createBtnReset() {
		spr_btnReset = new Sprite(img_resetBtn);

	}
	
	public void putBtnReset(){
		selflg.append(spr_btnReset);
	}

	public void pointerPressed(int x, int y){
		System.out.println("xy = " + x + ":" + y);
		
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}

	public void pointerReleased(int x, int y) {
		// jika memencet tombol back

		if (x >= spr_btnBack.getX() && x <= spr_btnBack.getX() + spr_btnBack.getWidth()
				&& y >= spr_btnBack.getY()
				&& y <= spr_btnBack.getY() + spr_btnBack.getHeight()) {
			//removeSprites();

			// tuju ke elemen yang kita inginkan
			// element at 0 untuk vector screen adalah menunya
			host.setCurrentScreen((Screen) host.getScreenVector().elementAt(0));
			
		//jika memencet tombol mute
		}else if (x >= spr_btnMute.getX() && x <= spr_btnMute.getX() + spr_btnMute.getWidth()
				&& y >= spr_btnMute.getY()
				&& y <= spr_btnMute.getY() + spr_btnMute.getHeight()){
			
			if (host.getGameSettings().isMusicOn()) {
				host.getGameSettings().setMusicOn(false);
				spr_btnMute.setFrame(1);
				host.getGameSound().stopBGM();
			}else{
				host.getGameSettings().setMusicOn(true);
				spr_btnMute.setFrame(0);
				
				host.getGameSound().playBGM();
			}
		
		//memencet tombol reset
		}else if (x >= spr_btnReset.getX() && x <= spr_btnReset.getX() + spr_btnReset.getWidth()
				&& y >= spr_btnReset.getY()
				&& y <= spr_btnReset.getY() + spr_btnReset.getHeight()){
			
			RMS record = ((GameMidlet)getMidlet()).getRMS();
			
			//reset skor tertinggi menjadi 0
			record.saveScore(0);
		}
	}
	
	public void start(){
		
		//menentukan gambar tombol mute
		if (host.getGameSettings().isMusicOn()) {
			spr_btnMute.setFrame(0);
		}else{
			spr_btnMute.setFrame(1);
		}
	}
}

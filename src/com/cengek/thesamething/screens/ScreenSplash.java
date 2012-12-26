package com.cengek.thesamething.screens;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.manager.SameThingGame;

public class ScreenSplash extends Screen {

	private final String LOGO_COVER = "/logo_cover.png";
	private final String LOGO_CENGEK = "/logo_cengek.png";
	private final String MUTE_BTN = "/mute_btn.png";
	private final String MGDW_LOGO = "/150mgdw.png";
	
	private Image img_logoCover, img_logoCengek, img_muteBtn, img_soundBtn, img_logoMGDW;
	
	private int animation_state =-1;
	
	private Sprite spr_logoCengek, spr_logoCover, spr_btnMute, spr_btnSound, spr_logoMGDW;
	
	private int time = 0;
	
	public ScreenSplash(Graphics graphics, MIDlet midlet, LayerManager lg,
			SameThingGame host) {
		super(graphics, midlet, lg, host);
	}

	public void createLogoMGDW(){
		spr_logoMGDW = new Sprite(img_logoMGDW);
	}
	
	public void putLogoMGDW(){
		selflg.append(spr_logoMGDW);
	}
	
	public void createLogoCover(){
		spr_logoCover = new Sprite(img_logoCover);
	}
	
	public void putLogoCover(){
		selflg.append(spr_logoCover);
	}
	
	public void createLogoCengek(){
		spr_logoCengek = new Sprite(img_logoCengek);
	}
	
	public void putLogoCengek(){
		selflg.append(spr_logoCengek);
	}
	
	public void createSoundBtn(){
		spr_btnSound = new Sprite(img_soundBtn, 49, 47);
	}
	
	public void putSoundBtn(){
		selflg.append(spr_btnSound);
	}
	
	public void createMuteBtn(){
		spr_btnMute = new Sprite(img_muteBtn, 49, 47);
	}
	
	public void putMuteBtn(){
		selflg.append(spr_btnMute);
	}
	
	public void draw() {
		
		host.setLg(selflg);
		
	}

	public void init() {

		createImages();
		
		createMuteBtn();
		putMuteBtn();
		
		createSoundBtn();
		putSoundBtn();
		
		//cover logo
		createLogoCover();
		putLogoCover();
		
		//logo
		createLogoCengek();
		putLogoCengek();
		
		//logo mgdw
		createLogoMGDW();
		putLogoMGDW();
		
		//dan setting-setting custom lainnya
		spr_logoCengek.setVisible(false);
		spr_logoCover.setPosition(-spr_logoCover.getWidth(), 150);
		spr_logoCengek.setPosition((host.getWidth()/2)-(spr_logoCengek.getWidth()/2), 150);

		spr_logoMGDW.setVisible(false);
		spr_logoMGDW.setPosition((host.getWidth()/2)-(spr_logoMGDW.getWidth()/2), 210);
		
		spr_btnSound.setPosition((host.getWidth()/2) - (spr_btnSound.getWidth())-10, 150);
		spr_btnMute.setPosition((host.getWidth()/2)+10, 150);
		
		spr_btnSound.setFrame(0);
		spr_btnMute.setFrame(1);
	}

	public void pointerPressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void pointerReleased(int x, int y) {
		//cek tekan tombol mute
		if (x >= spr_btnMute.getX() && x <= spr_btnMute.getX() + spr_btnMute.getWidth()
				&& y >= spr_btnMute.getY()
				&& y <= spr_btnMute.getY() + spr_btnMute.getHeight()){
			
			//musiik nggak dinyalakan
			host.getGameSettings().setMusicOn(false);
			
			//sembunyikan tombol
			spr_btnMute.setVisible(false);
			spr_btnSound.setVisible(false);
			
			//gerakkan animasi untuk splash screen
			animation_state = 0;
		//cek tekan tombol play
		}else if (x >= spr_btnSound.getX() && x <= spr_btnSound.getX() + spr_btnSound.getWidth()
				&& y >= spr_btnSound.getY()
				&& y <= spr_btnSound.getY() + spr_btnSound.getHeight()){
			
			//musik dinyalakan
			host.getGameSettings().setMusicOn(true);
			
			//sembunyikan tombol
			spr_btnMute.setVisible(false);
			spr_btnSound.setVisible(false);
			
			//gerakkan animasi untuk splash screen
			animation_state = 0;
		}
		
	}

	public void removeSprites() {
		// TODO Auto-generated method stub
		
	}

	public void run() {
		// TODO Auto-generated method stub
		
		if (animation_state == 0) {
			spr_logoCover.move(30, 0);
			
			//tampilkan logo
			if (spr_logoCover.getX() >= -80) {
				spr_logoCengek.setVisible(true);
			}
			
			if (spr_logoCover.getX() >= host.getWidth() + 250) {
				animation_state = 1;
			}
		}else if (animation_state == 1){
			if (time >= 35) {
				animation_state = 2;
				time = 0;
			}else{
				spr_logoMGDW.setVisible(true);
				time++;
			}
		}else if (animation_state == 2) {
			//element 0 adalah main
			host.setCurrentScreen((Screen)(host.getScreenVector().elementAt(0)));
			((ScreenMain)host.getCurrentScreen()).start();
		}
		
	}

	public void createImages() {

		try {
			img_logoCover = Image.createImage(LOGO_COVER);
			img_logoCengek = Image.createImage(LOGO_CENGEK);
			img_muteBtn = Image.createImage(MUTE_BTN);
			img_soundBtn = Image.createImage(MUTE_BTN);
			img_logoMGDW = Image.createImage(MGDW_LOGO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

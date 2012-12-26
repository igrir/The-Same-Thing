package com.cengek.thesamething.screens;

import java.io.IOException;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.lcdui.game.Sprite;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.manager.SameThingGame;
import com.cengek.thesamething.util.CharFonts;

public class ScreenHelp extends Screen {

	

	private final String GAME_BG = "/game_bg.png";
	private final String BACK_BTN = "/back_btn.png";
	private final String HELP_SPR = "/help_spr.png";
	
	Image img_bg, img_backBtn, img_helpSpr;
	

    private Sprite spr_btnBack, spr_help;
	
    private int timeHelpChange = 25;
    private int timeNow = timeHelpChange;

	public ScreenHelp(Graphics graphics, MIDlet midlet, LayerManager lg,
			SameThingGame host) {
		super(graphics, midlet, lg, host);
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
		drawText();
		host.setLg(selflg);
		
	}
	
	public void drawBackground(){
		g.drawImage(img_bg, 0, 0, 0);
	}

	public void drawText(){
		
		String[] tulisan = {"            -The Same Thing-",
							"              Version 1.0",
							"      Copyright Cengek Game Studio",
							"              www.cengek.com",
							"  Game Designer : Giri Prahasta Putra",
							" Programmer : Renisa Suryahadikusumah",
							"  Sound Composer : Anshar Abdullah",
							"                Thanks to:",
							"                 -MGDW4-",
							"          -UPI Game Developer-",
							"       -Program Studi Ilmu Komputer-",
							"     -Universitas Pendidikan Indonesia-"};
		
		
		int jarak = 16;

		int margin = 170;
		for (int i = 0; i < tulisan.length; i++) {
			CharFonts.drawString(g, tulisan[i], 0, jarak*i+margin);	
		}
		

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
			img_helpSpr = Image.createImage(HELP_SPR);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {

		createImages();
		
		createBtnBack();
		putBtnBack();
		
		createHelp();
		putHelp();
		
		//dan setting-setting custom lainnya
		spr_btnBack.setPosition((host.getWidth() / 2) - (spr_btnBack.getWidth() / 2),
				(host.getHeight() - spr_btnBack.getHeight())+15);
		
		spr_help.setPosition((host.getWidth() / 2) - (spr_help.getWidth() / 2),5);
	}
	
	public void createBtnBack() {
		spr_btnBack = new Sprite(img_backBtn);

	}
	
	public void putBtnBack(){
		selflg.append(spr_btnBack);
	}
	

	public void pointerPressed(int x, int y){
		System.out.println("xy = " + x + ":" + y);
	}
	
	public void start(){
		timeNow = timeHelpChange;
		spr_help.setFrame(0);
	}

	public void run() {
		
		timeNow--;
		if (timeNow <= 0) {
			spr_help.nextFrame();
			timeNow = timeHelpChange; 
		}
		
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
			((ScreenMain)host.getCurrentScreen()).start();
		}
	}
	
	public void createHelp(){
		spr_help = new Sprite(img_helpSpr, 211, 154);
	}
	
	public void putHelp(){
		selflg.append(spr_help);
	}
}

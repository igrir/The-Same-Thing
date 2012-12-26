package com.cengek.thesamething.screens;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;
import javax.microedition.midlet.MIDlet;

import com.cengek.thesamething.manager.SameThingGame;

public abstract class Screen {
	protected Graphics g;
	MIDlet m;
	protected LayerManager lg;
	protected LayerManager selflg = new LayerManager();
	protected SameThingGame host; 
	
	public Screen(Graphics graphics, MIDlet midlet, LayerManager lg, SameThingGame host) {
		// TODO Auto-generated constructor stub
		this.g = graphics;
		this.m = midlet;
		this.lg = lg;
		this.host = host;
		
		selflg.setViewWindow(0, 0, host.getWidth(), host.getHeight());
	}
	
	public Graphics getGraphics(){
		return g;
	}
	
	public MIDlet getMidlet(){
		return m;
	}
	
	public GameCanvas getHost(){
		return host;
	}
	
	public abstract void draw();
	
	public abstract void init();
	
	public abstract void pointerPressed(int x, int y);
	public abstract void pointerReleased(int x, int y);
	public abstract void createImages();
	
	/**
	 * Ketika berpindah screen maka sprite yang ditampilkan
	 * pada layer manager harus dihilangkan terlebih dahulu.
	 * Untuk itu setiap sprite yang telah dibuat harus dimasukkan pada prosedur ini
	 * 
	 */
	public abstract void removeSprites();
	
	public abstract void run();
}

package com.cengek.thesamething.util;

import java.io.IOException;
import java.util.Hashtable;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class CharFonts {

	private static String sequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+1234567890-={}|[]\\:\";\'<>?,./";
	private static Hashtable fontChars;
	
	public static void initialise(String fontName) throws IOException{
		fontChars = new Hashtable();
		for (int i = 0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			int code = c;
			fontChars.put(new Integer(code),
					Image.createImage("/"+fontName+"/"+code+".png"));
		}
	}
	
	public static Image drawCharImage(char ch){
		if (ch != ' ') {
			int i = sequence.indexOf(ch);
			if (i == -1) {
				throw new IllegalArgumentException("unsupported character");
			}
		}else{
			Image img = null; 
			try {
				img = Image.createImage("/euphemia/space.png");
				
				return img;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int code = ch;
		Integer key = new Integer(code);
		Image img = (Image)fontChars.get(key);
		//int wChar = img.getWidth();
		
		return img;
	}
	
	public static int drawChar(Graphics g, char ch, int x, int y){
		if (ch != ' ') {
			int i = sequence.indexOf(ch);
			if (i == -1) {
				throw new IllegalArgumentException("unsupported character");
			}
		}else{
			return 5;	//untuk spasi
		}
		
		int code = ch;
		Integer key = new Integer(code);
		Image img = (Image)fontChars.get(key);
		int wChar = img.getWidth();
		g.drawImage(img, x, y, Graphics.TOP | Graphics.LEFT);
		return wChar;
	}
	
	public static void drawString(Graphics g, String s, int x, int y){
		char[] chs = s.toCharArray();
		for (int i = 0; i < chs.length; i++){
			x += drawChar(g, chs[i], x, y);
		}
	}
	
	
}

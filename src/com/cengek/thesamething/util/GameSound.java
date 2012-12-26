package com.cengek.thesamething.util;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

public class GameSound {
	public Player playerBGM, playerTake, playerGameOver;
	private InputStream isMenu, isTake, isGameOver;
	
	public GameSound() {
		isMenu = getClass().getResourceAsStream("/sound/Hasilnya5.mp3");
		isTake = getClass().getResourceAsStream("/sound/take.mp3");
		isGameOver = getClass().getResourceAsStream("/sound/gameover.mp3");
		
		try{
			playerBGM = Manager.createPlayer(isMenu, "audio/mp3");
			playerTake = Manager.createPlayer(isTake, "audio/mp3");
			playerGameOver = Manager.createPlayer(isGameOver, "audio/mp3");
			playerTake.realize();
			
			playerTake.setLoopCount(1);
		}catch (IOException e) {
			e.printStackTrace();
		}catch (MediaException e){
			e.printStackTrace();
		}
		
	}
	
	public void playBGM(){
		try{
			playerBGM.realize();
			playerBGM.prefetch();
			playerBGM.start();
		}catch (MediaException e) {
			e.printStackTrace();
		}
	}
	
	
	public void stopGameOver(){
		try {
			playerGameOver.realize();
			playerGameOver.prefetch();
			playerGameOver.stop();
			playerGameOver.deallocate();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playGameOver(){	
		try{
			playerGameOver.realize();
			playerGameOver.prefetch();
			playerGameOver.start();
		}catch (MediaException e) {
			e.printStackTrace();
		}
	}
	
	public void playTake(){
		try{
			
			playerTake.realize();
			playerTake.prefetch();
			playerTake.start();
			playerTake.stop();
			playerTake.deallocate();
			
		}catch (MediaException e) {
			e.printStackTrace();
		}
	}
	
	public void stopTake(){
		try{
			playerTake.stop();
		}catch (MediaException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	
	public void simpleStopBGM(){
		try {
			playerBGM.stop();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopBGM(){
		try {
			playerBGM.realize();
			playerBGM.prefetch();
			playerBGM.stop();
			playerBGM.deallocate();
		} catch (MediaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void repeatBGM(){
		if (playerBGM.getState() == 300) {
			try {
				playerBGM.stop();
				playerBGM.deallocate();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			playBGM();
		}
	}


}
